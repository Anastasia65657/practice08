package pr1;

import java.util.Scanner;

public class Hello {
    private static int argIndex = 0;

    public static void main(String[] args) {
        String fio = "Хафизова Анастасия Андреевна";
        String group = "БОМ31ПРИ";
        int variant = 18;

        System.out.println("========================================");
        System.out.println("ФИО: " + fio);
        System.out.println("Группа: " + group);
        System.out.println("Номер варианта: " + variant);
        System.out.println("========================================\n");

        // Проверка аргументов командной строки
        if (args.length > 0) {
            System.out.print("Программа запущена с аргументами: ");
            for (String arg : args) {
                System.out.print("[" + arg + "] ");
            }
            System.out.println("\n");
        } else {
            System.out.println("Аргументы командной строки не переданы. Переключение на ввод с клавиатуры.\n");
        }

        Scanner inputScanner = new Scanner(System.in);

        System.out.println("=== ВЫПОЛНЕНИЕ ПРАКТИКИ 1 (ВАРИАНТ 18) ===\n");

        task5(inputScanner, args);
        System.out.println("----------------------------------------");
        task15(inputScanner, args);
        System.out.println("----------------------------------------");
        task45(inputScanner, args);
        System.out.println("----------------------------------------");
        task64(inputScanner, args);

        inputScanner.close();
    }

    private static double getDoubleInput(Scanner scanner, String[] args, String prompt) {
        if (args != null && argIndex < args.length) {
            try {
                double val = Double.parseDouble(args[argIndex++]);
                System.out.println(prompt + " (из аргументов): " + val);
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка парсинга аргумента '" + args[argIndex - 1] + "'. Переход на ввод с клавиатуры.");
            }
        }
        System.out.print(prompt + ": ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Ошибка ввода! Введите вещественное число: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    private static int getIntInput(Scanner scanner, String[] args, String prompt) {
        if (args != null && argIndex < args.length) {
            try {
                int val = Integer.parseInt(args[argIndex++]);
                System.out.println(prompt + " (из аргументов): " + val);
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка парсинга аргумента '" + args[argIndex - 1] + "'. Переход на ввод с клавиатуры.");
            }
        }
        System.out.print(prompt + ": ");
        while (!scanner.hasNextInt()) {
            System.out.print("Ошибка ввода! Введите целое число: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    // Задача A (5): Дан диаметр окружности d. Найти ее длину L = π·d.
    public static void task5(Scanner scanner, String[] args) {
        System.out.println("--- Задача 5 ---");
        // Используем универсальный метод ввода getDoubleInput
        double d = getDoubleInput(scanner, args, "Введите диаметр d");

        // Проверка условий «диаметр > 0»
        if (d <= 0) {
            System.out.println("Ошибка: диаметр окружности должен быть строго больше 0.");
            return;
        }

        double length = Math.PI * d;
        System.out.printf("Результат длины окружности L: %.4f\n", length);
    }

    // Задача B (15): Известна длина L окружности. Найти ее радиус R и площадь S круга.
    public static void task15(Scanner scanner, String[] args) {
        System.out.println("--- Задача 15 ---");
        // Используем универсальный метод ввода getDoubleInput
        double l = getDoubleInput(scanner, args, "Введите длину окружности L");

        // Проверка условий «длина > 0»
        if (l <= 0) {
            System.out.println("Ошибка: длина окружности должна быть строго больше 0.");
            return;
        }

        double r = l / (2 * Math.PI);
        double s = Math.PI * Math.pow(r, 2);

        System.out.printf("Результат радиуса R: %.4f\n", r);
        System.out.printf("Результат площади круга S: %.4f\n", s);
    }

    // Задача C (45): Известны три целых числа. Найти количество положительных чисел.
    public static void task45(Scanner scanner, String[] args) {
        System.out.println("--- Задача 45 ---");
        // Используем метод getIntInput последовательно для трех чисел
        int a = getIntInput(scanner, args, "Введите 1-е целое число");
        int b = getIntInput(scanner, args, "Введите 2-е целое число");
        int c = getIntInput(scanner, args, "Введите 3-е целое число");

        int count = 0;
        if (a > 0) count++;
        if (b > 0) count++;
        if (c > 0) count++;

        System.out.println("Количество положительных чисел: " + count);
    }

    // Задача D (64): Известны целочисленные координаты трех вершин прямоугольника...
    public static void task64(Scanner scanner, String[] args) {
        System.out.println("--- Задача 64 ---");
        int x1 = getIntInput(scanner, args, "Введите координату x1");
        int y1 = getIntInput(scanner, args, "Введите координату y1");
        int x2 = getIntInput(scanner, args, "Введите координату x2");
        int y2 = getIntInput(scanner, args, "Введите координату y2");
        int x3 = getIntInput(scanner, args, "Введите координату x3");
        int y3 = getIntInput(scanner, args, "Введите координату y3");

        // Геометрическая проверка на параллельность осям координат
        if ((x1 != x2 && x1 != x3 && x2 != x3) || (y1 != y2 && y1 != y3 && y2 != y3)) {
            System.out.println("Ошибка: введенные точки не образуют прямоугольник, стороны которого параллельны осям.");
            return;
        }

        int x4 = (x1 == x2) ? x3 : ((x1 == x3) ? x2 : x1);
        int y4 = (y1 == y2) ? y3 : ((y1 == y3) ? y2 : y1);

        System.out.printf("Координаты четвертой вершины: (%d, %d)\n", x4, y4);
    }
}
