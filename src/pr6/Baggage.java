package pr6;

public class Baggage {
    private final double limit; //лимит
    private double currentWeight; //вес багажа текущий

    public Baggage(double limit) {
        if (limit <= 0) {
            throw new IllegalArgumentException("Лимит веса должен быть больше 0");
        }
        this.limit = limit;
    }

    //Метод бросает checked-исключение при нарушении инварианта
    public void checkIn(double weight) throws ExcessBaggageException {
        if (weight <= 0) {
            throw new IllegalArgumentException("Вес багажа должен быть больше 0");
        }
        if (weight > limit) {
            throw new ExcessBaggageException("Превышение лимита! Допустимо: " + limit + " кг, предъявлено: " + weight + " кг.");
        }
        this.currentWeight = weight;
    }

    // Метод разбора числа/кода с явной обработкой NumberFormatException
    public static double parseTag(String text) {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный формат числового кода/веса: '" + text + "'", e);
        }
    }

    public double getLimit() { return limit; }
    public double getCurrentWeight() { return currentWeight; }
}