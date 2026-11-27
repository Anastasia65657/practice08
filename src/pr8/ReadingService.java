package pr8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ReadingService {

    // Чтение файла в List<Reading> с пропуском битых строк
    public List<Reading> readFromFile(Path path) throws IOException {
        List<Reading> result = new ArrayList<>();
        List<String> lines = Files.readAllLines(path);

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isEmpty()) {
                continue;
            }
            try {
                Reading reading = Reading.parse(line);
                result.add(reading);
            } catch (Exception e) {
                System.out.printf("[Пропуск битой строки #%d] '%s' -> Причина: %s%n",
                        (i + 1), line, e.getMessage());
            }
        }
        return result;
    }

    // Группировка в Map<Станция, List<Reading>>
    public Map<String, List<Reading>> groupByStation(List<Reading> readings) {
        Map<String, List<Reading>> grouped = new HashMap<>();
        for (Reading r : readings) {
            // Если ключа еще нет, создаем новый список и добавляем элемент
            grouped.computeIfAbsent(r.getStation(), k -> new ArrayList<>()).add(r);
        }
        return grouped;
    }

    // Набор уникальных дат Set<String>
    public Set<String> getUniqueDates(List<Reading> readings) {
        Set<String> uniqueDates = new HashSet<>();
        for (Reading r : readings) {
            uniqueDates.add(r.getDate());
        }
        return uniqueDates;
    }

    // Вывод Топ-3 по переданному Comparator
    public void printTop3(List<Reading> readings, Comparator<Reading> comparator) {
        List<Reading> copy = new ArrayList<>(readings);
        copy.sort(comparator);

        int limit = Math.min(3, copy.size());
        for (int i = 0; i < limit; i++) {
            System.out.println((i + 1) + ". " + copy.get(i));
        }
    }

    // Обобщенный метод подсчета суммы через Calculation<T>
    public <T> double sum(List<T> values, Calculation<T> calculation) {
        if (values == null || values.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        for (T item : values) {
            total += calculation.calculate(item);
        }
        return total;
    }
}
