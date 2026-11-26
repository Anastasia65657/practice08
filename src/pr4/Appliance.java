package pr4;

public class Appliance {
    protected String brand;
    protected String model;
    protected int powerWatts;

    public Appliance(String brand, String model, int powerWatts) {
        if (brand == null || brand.strip().isEmpty()) {
            throw new IllegalArgumentException("Бренд не может быть пустым.");
        }
        if (model == null || model.strip().isEmpty()) {
            throw new IllegalArgumentException("Модель не может быть пустой.");
        }
        if (powerWatts <= 0 || powerWatts > 10000) {
            throw new IllegalArgumentException("Мощность должна быть в диапазоне от 1 до 10000 Вт.");
        }
        this.brand = brand.strip();
        this.model = model.strip();
        this.powerWatts = powerWatts;
    }

    public final void info() {
        System.out.print("Прибор: " + brand + " " + model + " (" + powerWatts + " Вт)");
    }

    public void powerOn() {
        System.out.println("Прибор включается в сеть.");
    }
}