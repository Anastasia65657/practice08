package pr6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Main {
    private static final Path LOG_PATH = Path.of("log.txt");
    private static boolean debugMode = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Baggage baggage = new Baggage(23.0);

        System.out.println("--- Система регистрации багажа (Вариант 18) ---");
        System.out.println("Текущий лимит веса: " + baggage.getLimit() + " кг.");

        while (true) {
            System.out.println("\nДоступные команды: checkIn, parseTag, debug, exit");
            System.out.print("> ");
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("exit")) {
                System.out.println("Завершение работы программы.");
                break;
            }

            try {
                switch (command) {
                    case "checkIn":
                        System.out.print("Введите вес для регистрации: ");
                        // Используем метод разбора числового значения для ввода
                        double weight = Baggage.parseTag(scanner.nextLine().trim());
                        baggage.checkIn(weight);

                        String successMsg = "Багаж весом " + weight + " кг успешно зарегистрирован.";
                        System.out.println(successMsg);
                        logOperation(successMsg);
                        break;

                    case "parseTag":
                        System.out.print("Введите строку-тег багажа (число) для парсинга: ");
                        String input = scanner.nextLine().trim();
                        double parsedWeight = Baggage.parseTag(input);

                        String parseMsg = "Команда parseTag выполнена. Вес из тега: " + parsedWeight + " кг.";
                        System.out.println(parseMsg);
                        logOperation(parseMsg);
                        break;

                    case "debug":
                        debugMode = !debugMode;
                        System.out.println("Режим debug: " + (debugMode ? "ВКЛЮЧЕН" : "ВЫКЛЮЧЕН"));
                        break;

                    default:
                        System.out.println("Неизвестная команда. Попробуйте еще раз.");
                }
            } catch (ExcessBaggageException | IllegalArgumentException e) {
                // Ошибки формата и инвариантов выводятся пользователю
                System.out.println("Ошибка: " + e.getMessage());
                if (debugMode) {
                    e.printStackTrace(); // В режиме debug печатается весь stack trace
                }
            } catch (Exception e) {
                System.out.println("Непредвиденная ошибка: " + e.getMessage());
                if (debugMode) {
                    e.printStackTrace();
                }
            }
        }
        scanner.close();
    }

    // Запись успешных операций в файл
    private static void logOperation(String message) {
        try {
            String logEntry = String.format("%s%n", message);
            // Флаг CREATE создаст файл при отсутствии, APPEND допишет в конец
            Files.writeString(LOG_PATH, logEntry, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Не удалось записать лог в файл: " + e.getMessage());
        }
    }
}