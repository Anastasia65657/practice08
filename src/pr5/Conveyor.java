package pr5;

public class Conveyor extends Machine implements Maintainable, Startable {
    private final double speedMetersPerMinute; //скорость ленты

    public Conveyor(String name, double baseFactor, double speedMetersPerMinute) {
        super(name, baseFactor);
        this.speedMetersPerMinute = speedMetersPerMinute;
    }

    @Override
    public double throughput() {
        // Формула 2: Базовый фактор умноженный на скорость ленты
        return baseFactor * speedMetersPerMinute;
    }

    @Override
    public void maintain() {
        System.out.println("Обслуживание конвейера " + name + ": проверка натяжения ленты.");
    }

    @Override
    public void start() {
        System.out.println("Конвейер " + name + " запущен: движение ленты.");
    }

    @Override
    public void stop() {
        System.out.println("Конвейер " + name + " остановлен.");
    }
}