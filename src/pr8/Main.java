package pr8;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        ReadingService service = new ReadingService();
        Path filePath = Path.of("src/pr8/readings.txt");

        System.out.println("=== 1. ЧТЕНИЕ ФАЙЛА И ОБРАБОТКА БИТЫХ СТРОК ===");
        List<Reading> readings;
        try {
            readings = service.readFromFile(filePath);
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
            return;
        }
        System.out.println("Успешно прочитано записей: " + readings.size() + "\n");

        System.out.println("=== 2. ГРУППИРОВКА ПО СТАНЦИЯМ (Map) ===");
        Map<String, List<Reading>> byStation = service.groupByStation(readings);
        for (Map.Entry<String, List<Reading>> entry : byStation.entrySet()) {
            System.out.println("Станция '" + entry.getKey() + "' -> записей: " + entry.getValue().size());
        }

        System.out.println("\n=== 3. УНИКАЛЬНЫЕ ДАТЫ (Set) ===");
        Set<String> uniqueDates = service.getUniqueDates(readings);
        System.out.println("Уникальные даты: " + uniqueDates);
        System.out.println("Размер списка: " + readings.size() + " | Уникальных дат: " + uniqueDates.size());

        //Comparator (температура по убыванию)
        Comparator<Reading> tempDescComparator = Comparator.comparingDouble(Reading::getTemp).reversed();

        System.out.println("\n=== 4. ТОП-3 ПОКАЗАНИЯ ПО ТЕМПЕРАТУРЕ (Убывание ↓) ===");
        service.printTop3(readings, tempDescComparator);

        System.out.println("\n=== 5. ОТБОР ЧЕРЕЗ Predicate<Reading> ===");
        double normalThreshold = 16.0;
        double highThreshold = 50.0;

        Predicate<Reading> isHot = r -> r.getTemp() > normalThreshold;
        Predicate<Reading> isExtreme = r -> r.getTemp() > highThreshold;

        // Отбор по первому порогу
        List<Reading> filteredReadings = new ArrayList<>();
        for (Reading r : readings) {
            if (isHot.test(r)) {
                filteredReadings.add(r);
            }
        }
        System.out.println("Найдено записей с температурой > " + normalThreshold + "°C: " + filteredReadings.size());

        // Отбор по второму порогу
        List<Reading> emptyFiltered = new ArrayList<>();
        for (Reading r : readings) {
            if (isExtreme.test(r)) {
                emptyFiltered.add(r);
            }
        }
        System.out.println("Найдено записей с температурой > " + highThreshold + "°C: " + emptyFiltered.size());

        System.out.println("\n=== 6. ПРЕОБРАЗОВАНИЕ И ВЫВОД ЧЕРЕЗ Function И Consumer ===");
        // Function<Reading, String> формирует строку отчета
        Function<Reading, String> formatReport = r ->
                "Отчёт -> [" + r.getStation() + "] Температура: " + r.getTemp() + "°C";

        // Consumer<String> через ссылку на метод System.out::println
        Consumer<String> printer = System.out::println;

        for (Reading r : filteredReadings) {
            String reportLine = formatReport.apply(r);
            printer.accept(reportLine);
        }

        System.out.println("\n=== 7. ПОДСЧЕТ СУММЫ ЧЕРЕЗ Calculation<Reading> ===");
        Calculation<Reading> tempExtractor = r -> r.getTemp();

        double totalSum = service.sum(readings, tempExtractor);
        double filteredSum = service.sum(filteredReadings, tempExtractor);
        double emptySum = service.sum(emptyFiltered, tempExtractor);

        System.out.printf("Сумма температур исходного списка: %.2f°C%n", totalSum);
        System.out.printf("Сумма температур отобранного списка: %.2f°C%n", filteredSum);
        System.out.printf("Сумма температур пустого списка: %.2f°C%n", emptySum);
    }
}
