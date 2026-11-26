package pr4;

public class Washer extends Appliance {
    private final int rpm;

    public Washer(String brand, String model, int powerWatts, int rpm) {
        super(brand, model, powerWatts);
        if (rpm <= 0 || rpm > 3000) {
            throw new IllegalArgumentException("Скорость отжима должна быть в диапазоне от 1 до 3000");
        }
        this.rpm = rpm;
    }

    @Override
    public void powerOn() {
        System.out.println("Стиральная машина заблокировала люк. Скорость отжима: " + rpm + " об/мин.");
    }
}