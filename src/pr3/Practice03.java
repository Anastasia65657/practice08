package pr3;

import java.util.List;

public class Practice03 {
    public static void main(String[] args) {
        System.out.println("=== ЛАБОРАТОРНАЯ РАБОТА №3 ===\n");

        BaggageStorage storage = new BaggageStorage();

        // Создание и добавление 4 объектов в хранилище
        try {
            Baggage b1 = new Baggage("SU-10245", 23.5, "SU-100", true);
            Baggage b2 = new Baggage("SU-99431", 12.0, "SU-502", false);
            Baggage b3 = new Baggage("SU-00432", 32.1, "SU-401", true);
            Baggage b4 = new Baggage("SU-55120", 9.8, "SU-205", false);

            storage.add(b1);
            storage.add(b2);
            storage.add(b3);
            storage.add(b4);

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка валидации: " + e.getMessage());
        }

        storage.printAll();

        // Демонстрация поиска хрупкого багажа
        System.out.println("\n Результаты поиска (только хрупкий багаж):");
        List<Baggage> fragileList = storage.findFragile();
        if (fragileList.isEmpty()) {
            System.out.println("Хрупкий багаж не найден.");
        } else {
            for (Baggage fb : fragileList) {
                System.out.println(fb);
            }
        }

        // Демонстрация удаления объекта
        System.out.println("\n--- Демонстрация удаления объекта ---");
        // Внимание: в вашем исходном тесте здесь "S7-502" вместо "SU-502", поэтому удаление не произойдет
        Baggage toRemove = new Baggage("SU-99431", 12.0, "SU-502", false);
        storage.remove(toRemove);

        storage.printAll();
    }
}
