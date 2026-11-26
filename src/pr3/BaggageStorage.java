package pr3;

import java.util.ArrayList;
import java.util.List;

public class BaggageStorage {
    private final List<Baggage> storage;

    public BaggageStorage() {
        this.storage = new ArrayList<>();
    }

    // Метод добавления объекта в хранилище
    public void add(Baggage baggage) {
        if (baggage == null) {
            System.out.println("Ошибка: Нельзя добавить пустой объект.");
            return;
        }
        storage.add(baggage);
        System.out.println("Багаж с биркой " + baggage.getTag() + " успешно добавлен.");
    }

    // Метод удаления объекта из хранилища
    public boolean remove(Baggage baggage) {
        if (storage.remove(baggage)) {
            System.out.println("➖ Объект успешно удален из хранилища: " + baggage);
            return true;
        }
        System.out.println("Объект для удаления не найден в хранилище.");
        return false;
    }

    // Вывод всех элементов на экран
    public void printAll() {
        System.out.println("\n--- Текущее содержимое хранилища ---");
        if (storage.isEmpty()) {
            System.out.println("[Хранилище пусто]");
            return;
        }
        for (Baggage b : storage) {
            System.out.println(b);
        }
    }

    // Поиск элементов Хрупкий багаж
    public List<Baggage> findFragile() {
        List<Baggage> result = new ArrayList<>();
        for (Baggage b : storage) {
            if (b.isFragile()) {
                result.add(b);
            }
        }
        return result;
    }
}
