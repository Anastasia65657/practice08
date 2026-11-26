package pr3;

import java.util.Objects;

public class Baggage {
    private final String tag;      // Номер багажной бирки
    private final double weight;   // Вес багажа
    private final String flight;   // Номер рейса
    private final boolean fragile; // Является ли багаж хрупким

    // Конструктор с полной валидацией входных данных
    public Baggage(String tag, double weight, String flight, boolean fragile) {
        if (tag == null || tag.strip().isEmpty()) {
            throw new IllegalArgumentException("Номер бирки не может быть пустым.");
        }
        if (weight <= 0.0 || weight > 100.0) {
            throw new IllegalArgumentException("Вес багажа должен быть строго больше 0 и не превышать 100 кг.");
        }
        if (flight == null || flight.strip().isEmpty()) {
            throw new IllegalArgumentException("Номер рейса не может быть пустым.");
        }

        this.tag = tag.strip();
        this.weight = weight;
        this.flight = flight.strip();
        this.fragile = fragile;
    }

    // Геттеры
    public String getTag() {
        return tag;
    }

    public double getWeight() {
        return weight;
    }

    public String getFlight() {
        return flight;
    }

    public boolean isFragile() {
        return fragile;
    }

    // Переопределение метода toString() для читаемого вывода
    @Override
    public String toString() {
        return String.format("Багаж [Бирка: %s, Рейс: %s, Вес: %.2f кг, Хрупкий: %s]",
                tag, flight, weight, fragile ? "Да" : "Нет");
    }

    // Переопределение equals() для корректного сравнения объектов
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Baggage baggage = (Baggage) o;
        return Double.compare(baggage.weight, weight) == 0 &&
                fragile == baggage.fragile &&
                tag.equals(baggage.tag) &&
                flight.equals(baggage.flight);
    }

    // Переопределение hashCode() совместно с equals()
    @Override
    public int hashCode() {
        return Objects.hash(tag, weight, flight, fragile);
    }
}
