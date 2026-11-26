package pr4;

import java.util.ArrayList;
import java.util.List;

public class Practice04 {

    public static void processOven(Appliance appliance) {
        if (appliance instanceof Oven oven) {
            System.out.print("Обнаружена духовка в списке: ");
            oven.info();
            System.out.println();
            oven.selfClean();
        }
    }

    public static void main(String[] args) {
        List<Appliance> appliances = new ArrayList<>();

        try {
            appliances.add(new Fridge("Bosch", "KGN39", 200, 366));
            appliances.add(new Washer("LG", "F2J3", 1500, 1200));
            appliances.add(new Oven("Samsung", "NV70", 3000, 200));
            appliances.add(new Fridge("Siemens", "KG39", 180, 400));
            appliances.add(new Oven("Gorenje", "BOS67", 3300, 275));
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка при инициализации начальных данных: " + e.getMessage());
        }

        System.out.println("=== Цикл с полиморфными вызовами методов ===");
        for (Appliance app : appliances) {
            app.info();
            System.out.print(" -> ");
            app.powerOn();
        }

        System.out.println("\n=== Проверка метода со специальной обработкой ===");
        for (Appliance app : appliances) {
            processOven(app);
        }

    }
}