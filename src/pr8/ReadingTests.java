package pr8;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class ReadingTests {

    @Test // Тест 1: Проверка корректного разбора валидной строки
    public void testParseValidLine() {
        Reading r = Reading.parse("Северная;2026-11-10;12.5;65");

        assertEquals("Северная", r.getStation());
        assertEquals("2026-11-10", r.getDate());
        assertEquals(12.5, r.getTemp(), 0.001);
        assertEquals(65, r.getHumidity());
    }

    @Test // Тест 2: Проверка выброса исключения при битой строке (неверное число полей или текст вместо числа)
    public void testParseInvalidLine() {
        assertThrows(IllegalArgumentException.class, () -> Reading.parse("Битая_строка"));
        assertThrows(NumberFormatException.class, () -> Reading.parse("Северная;2026-11-10;ошибка;65"));
    }

    @Test // Тест 3: Чтение из файла с пропуском битых строк
    public void testReadFromFileWithCorruptedLines(@TempDir Path tempDir) throws IOException {
        Path testFile = tempDir.resolve("test_readings.txt");
        List<String> lines = List.of(
                "Северная;2026-11-10;12.5;65",
                "Некорректная_строка",
                "Южная;2026-11-10;18.2;50",
                "Восточная;2026-11-11;не_число;61"
        );
        Files.write(testFile, lines);

        ReadingService service = new ReadingService();
        List<Reading> readings = service.readFromFile(testFile);

        // Должно прочитаться только 2 корректные записи из 4
        assertEquals(2, readings.size());
        assertEquals("Северная", readings.get(0).getStation());
        assertEquals("Южная", readings.get(1).getStation());
    }

    @Test // Тест 4: Проверка группировки по станциям в Map
    public void testGroupByStation() {
        List<Reading> list = List.of(
                new Reading("Северная", "2026-11-10", 10.0, 50),
                new Reading("Южная", "2026-11-10", 20.0, 40),
                new Reading("Северная", "2026-11-11", 12.0, 55)
        );

        ReadingService service = new ReadingService();
        Map<String, List<Reading>> grouped = service.groupByStation(list);

        assertEquals(2, grouped.size()); // Всего 2 уникальные станции
        assertEquals(2, grouped.get("Северная").size()); // У Северной 2 записи
        assertEquals(1, grouped.get("Южная").size());    // У Южной 1 запись
    }

    @Test // Тест 5: Проверка сбора уникальных дат в Set
    public void testGetUniqueDates() {
        List<Reading> list = List.of(
                new Reading("Северная", "2026-11-10", 10.0, 50),
                new Reading("Южная", "2026-11-10", 20.0, 40),
                new Reading("Северная", "2026-11-11", 12.0, 55)
        );

        ReadingService service = new ReadingService();
        Set<String> uniqueDates = service.getUniqueDates(list);

        // В списке 3 записи, но уникальных дат должно быть 2
        assertEquals(2, uniqueDates.size());
        assertTrue(uniqueDates.contains("2026-11-10"));
        assertTrue(uniqueDates.contains("2026-11-11"));
    }

    @Test // Тест 6: Проверка сортировки Comparator по убыванию температуры
    public void testComparatorOrder() {
        Reading r1 = new Reading("А", "2026-11-10", 10.0, 50);
        Reading r2 = new Reading("Б", "2026-11-10", 25.5, 50);
        Reading r3 = new Reading("В", "2026-11-10", 15.0, 50);

        List<Reading> list = new ArrayList<>(List.of(r1, r2, r3));
        Comparator<Reading> tempDesc = Comparator.comparingDouble(Reading::getTemp).reversed();
        list.sort(tempDesc);

        // Проверяем порядок убывания: 25.5 -> 15.0 -> 10.0
        assertEquals(25.5, list.get(0).getTemp(), 0.001);
        assertEquals(15.0, list.get(1).getTemp(), 0.001);
        assertEquals(10.0, list.get(2).getTemp(), 0.001);
    }
}
