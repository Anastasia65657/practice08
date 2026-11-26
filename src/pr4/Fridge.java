package pr4;

public class Fridge extends Appliance {
    private final int volume;

    public Fridge(String brand, String model, int powerWatts, int volume) {
        super(brand, model, powerWatts);
        if (volume <= 0 || volume > 100) {
            throw new IllegalArgumentException("Объем камеры должен быть в диапазоне от 1 до 100 литров.");
        }
        this.volume = volume;
    }

    @Override
    public void powerOn() {
        System.out.println("Холодильник загудел компрессором. Объем камеры: " + volume + " л.");
    }
}