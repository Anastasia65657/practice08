package pr2;

import java.util.Scanner;

public class Practice02 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== ПОСЛЕДОВАТЕЛЬНОЕ ВЫПОЛНЕНИЕ ЗАДАЧ (ВАРИАНТ 18) ===\n");

        // 1. Задача E (58)
        System.out.println("--- Задача E (58) ---");
        System.out.print("Введите три целых числа (два из которых равны): ");
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();

        int uniqueIndex = Task58.findUniqueIndex(a, b, c);
        System.out.println("Порядковый номер отличающегося числа: " + uniqueIndex);
        System.out.println("\n-----------------------------------\n");

        // 2. Задача F (75)
        System.out.println("--- Задача F (75) ---");
        System.out.print("Введите цену 1 кг конфет (вещественное число): ");
        double price = scanner.nextDouble();

        Task75.printCandyPrices(price);
        System.out.println("\n-----------------------------------\n");

        // 3. Задача G (115)
        System.out.println("--- Задача G (115) ---");
        System.out.print("Введите число N (которое является степенью двойки): ");
        int nG = scanner.nextInt();

        int k = Task115.findPowerOfTwo(nG);
        System.out.println("Показатель степени K (где N = 2^K): " + k);
        System.out.println("\n-----------------------------------\n");

        // 4. Задание H (145)
        System.out.println("--- Задание H (145) ---");
        System.out.print("Введите количество деталей N: ");
        int nH = scanner.nextInt();
        double[] masses = new double[nH];
        double[] volumes = new double[nH];

        for (int i = 0; i < nH; i++) {
            System.out.print("Деталь " + (i + 1) + " (введите массу и объем): ");
            masses[i] = scanner.nextDouble();
            volumes[i] = scanner.nextDouble();
        }

        // Базовый метод
        int maxDensityIdx = Task145.findMaxDensityIndex(masses, volumes);
        double maxDensity = masses[maxDensityIdx] / volumes[maxDensityIdx];

        // Индекс в Java 0-based, отображаем пользователю в формате 1-based как idx + 1
        System.out.println("Деталь с максимальной плотностью (номер): " + (maxDensityIdx + 1));
        System.out.printf("Максимальная плотность: %.4f\n", maxDensity);

        // Перегруженный метод на отрезке от 0 до N-1
        int maxDensityIdxOverloaded = Task145.findMaxDensityIndex(masses, volumes, 0, nH - 1);
        System.out.println("[Тест перегрузки from/to] Номер детали: " + (maxDensityIdxOverloaded + 1));
        System.out.println("\n-----------------------------------\n");

        // 5. Задача I (175)
        System.out.println("--- Задача I (175) ---");
        System.out.print("Введите размер массива N (> 2) для чисел Фибоначчи: ");
        int nI = scanner.nextInt();

        int[] fibArray = Task175.generateFibonacci(nI);
        System.out.print("Сформированный массив Фибоначчи: ");
        for (int val : fibArray) {
            System.out.print(val + " ");
        }
        System.out.println("\n-----------------------------------\n");

        // 6. Задача J (215)
        System.out.println("--- Задача J (215) ---");
        System.out.print("Введите размер массива N: ");
        int nJ = scanner.nextInt();
        double[] arrayJ = new double[nJ];
        System.out.print("Введите элементы массива: ");
        for (int i = 0; i < nJ; i++) {
            arrayJ[i] = scanner.nextDouble();
        }

        int[] closestIndices = Task215.findClosestElements(arrayJ);
        // Корректируем 0-based индексы Java обратно в 1-based
        System.out.println("Номера двух ближайших элементов: " + (closestIndices[0] + 1) + " и " + (closestIndices[1] + 1));

        System.out.println("\n=================================================");
        System.out.println("Все задачи успешно выполнены. Программа завершена.");

        scanner.close();
    }
}

// 1. Задача E (58)
class Task58 {
    // Метод выполняет только чистый алгоритм
    public static int findUniqueIndex(int a, int b, int c) {
        if (a == b) {
            return 3;
        } else if (a == c) {
            return 2;
        } else {
            return 1;
        }
    }
}

// 2. Задача F (75)
class Task75 {
    public static void printCandyPrices(double pricePerKg) {
        for (int i = 1; i <= 10; i++) {
            double weight = i * 0.1;
            double cost = weight * pricePerKg;
            System.out.printf("Вес: %.1f кг -> Стоимость: %.2f\n", weight, cost);
        }
    }
}

// 3. Задача G (115)
class Task115 {
    public static int findPowerOfTwo(int n) {
        int k = 0;
        while (n > 1) {
            n /= 2;
            k++;
        }
        return k;
    }
}

// 4. Задание H (145)
class Task145 {
    // Базовый метод
    public static int findMaxDensityIndex(double[] masses, double[] volumes) {
        return findMaxDensityIndex(masses, volumes, 0, masses.length - 1);
    }

    //перегрузка метода (принимает границы интервала from / to)
    public static int findMaxDensityIndex(double[] masses, double[] volumes, int from, int to) {
        int maxIdx = from;
        double maxDensity = masses[from] / volumes[from];

        for (int i = from + 1; i <= to; i++) {
            double currentDensity = masses[i] / volumes[i];
            if (currentDensity > maxDensity) {
                maxDensity = currentDensity;
                maxIdx = i;
            }
        }
        // ВНИМАНИЕ: В условии индекс 1-based.
        // Возвращаем 0-based индекс
        return maxIdx;
    }
}

// 5. Задача I (175)
class Task175 {
    public static int[] generateFibonacci(int n) {
        int[] fib = new int[n];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < n; i++) {
            fib[i] = fib[i - 2] + fib[i - 1];
        }
        return fib;
    }
}

// 6. Задача J (215)
class Task215 {
    public static int[] findClosestElements(double[] array) {
        int index1 = 0;
        int index2 = 1;
        double minDiff = Math.abs(array[0] - array[1]);

        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                double diff = Math.abs(array[i] - array[j]);
                if (diff < minDiff) {
                    minDiff = diff;
                    index1 = i;
                    index2 = j;
                }
            }
        }
        // В условии задачи индексы 1-based (от 1 до N).
        // В Java сохраняем в формате 0-based
        return new int[]{index1, index2};
    }
}
