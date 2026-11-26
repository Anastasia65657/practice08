package pr5;

public class Cnc extends Machine implements Maintainable, Startable {
    private final int axesCount; //количество осей геометрии

    public Cnc(String name, double baseFactor, int axesCount) {
        super(name, baseFactor);
        this.axesCount = axesCount;
    }

    @Override
    public double throughput() {
        // Формула 3: Базовый фактор деленный на количество осей
        return (baseFactor * 100) / axesCount;
    }

    @Override
    public void maintain() {
        System.out.println("Обслуживание ЧПУ станка " + name + ": калибровка лазерных датчиков.");
    }

    @Override
    public void start() {
        System.out.println("ЧПУ станок " + name + " запущен: загрузка управляющей программы.");
    }

    @Override
    public void stop() {
        System.out.println("ЧПУ станок " + name + " остановлен.");
    }
}
