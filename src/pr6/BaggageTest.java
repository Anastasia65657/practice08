package pr6;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BaggageTest {

    @Test // Тест 1: Проверяем успешную регистрацию нормального веса
    public void testCheckInSuccess() {
        Baggage baggage = new Baggage(23.0);

        // Проверяем, что метод выполняется без выброса исключений
        assertDoesNotThrow(() -> {
            baggage.checkIn(15.0);
        });

        assertEquals(15.0, baggage.getCurrentWeight());
    }

    @Test // Тест 2: Проверяем выброс корректной ошибки при перевесе (Checked)
    public void testCheckInExcessBaggage() {
        Baggage baggage = new Baggage(23.0);

        // Утверждаем, что вес 25 кг вызовет наше проверяемое исключение ExcessBaggageException
        ExcessBaggageException exception = assertThrows(ExcessBaggageException.class, () -> {
            baggage.checkIn(25.0);
        });

        // Проверяем, что внутри исключения лежит правильный текст
        assertTrue(exception.getMessage().contains("Превышение лимита!"));
    }

    @Test // Тест 3: Проверяем выброс технической ошибки при отрицательном весе
    public void testCheckInNegativeWeight() {
        Baggage baggage = new Baggage(23.0);

        // Отрицательный вес — должна вылететь IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            baggage.checkIn(-5.0);
        });
    }

    @Test // Тест 4: Проверяем успешный парсинг правильной строки-числа
    public void testParseTagSuccess() {
        double result = Baggage.parseTag("18.4");
        assertEquals(18.4, result, 0.001);
    }

    @Test // Тест 5: Проверяем обработку NumberFormatException внутри parseTag
    public void testParseTagInvalidFormat() {
        // Если передали буквы, переводчик должен упаковать это в IllegalArgumentException
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Baggage.parseTag("abc");
        });

        // Проверяем, что внутри сохранилась первопричина — NumberFormatException
        assertNotNull(exception.getCause());
        assertTrue(exception.getCause() instanceof NumberFormatException);
    }

    @Test // Тест 6: Проверяем валидацию самого лимита в конструкторе
    public void testInvalidConstructorLimit() {
        // Лимит 0 или минус создавать нельзя
        assertThrows(IllegalArgumentException.class, () -> {
            new Baggage(0);
        });
    }
}