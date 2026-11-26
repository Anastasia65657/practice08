package pr5;

public abstract class Machine {
    protected final String name;
    protected final double baseFactor;

    public Machine(String name, double baseFactor) {
        this.name = name;
        this.baseFactor = baseFactor;
    }

    // Абстрактный метод расчёта
    public abstract double throughput();

    @Override
    public String toString() {
        return String.format("Станок: %s (Базовый фактор: %.1f, Расчётная производительность: %.2f)",
                name, baseFactor, throughput());
    }

    public int sss() {
        return 6;
    }
}
