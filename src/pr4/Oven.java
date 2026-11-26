package pr4;

public class Oven extends Appliance {
    private final int maxTemp;

    public Oven(String brand, String model, int powerWatts, int maxTemp) {
        super(brand, model, powerWatts);
        if (maxTemp < 30 || maxTemp > 350) {
            throw new IllegalArgumentException("Максимальная температура должна быть в диапазоне от 30 до 350°C.");
        }
        this.maxTemp = maxTemp;
    }

    @Override
    public void powerOn() {
        System.out.println("Духовка включает нагревательные элементы. Макс. температура: " + maxTemp + "°C.");
    }

    public void selfClean() {
        System.out.println(" -> Запущена функция самоочистки духовки!");
    }
}