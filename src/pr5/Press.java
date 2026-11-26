package pr5;

public class Press extends Machine implements Maintainable, Startable {
    private final double pressureTons; //давление в тоннах

    public Press(String name, double baseFactor, double pressureTons) {
        super(name, baseFactor);
        this.pressureTons = pressureTons;
    }

    @Override //Переопределение метода абстрактного класса
    public double throughput() {
        return baseFactor * pressureTons;
    }

    @Override
    public void maintain() {
        System.out.println("Обслуживание пресса " + name + ": замена гидравлического масла.");
    }

    @Override
    public void start() {
        System.out.println("Пресс " + name + " запущен: нагнетание давления.");
    }

    @Override
    public void stop() {
        System.out.println("Пресс " + name + " остановлен.");
    }
}