package pr8;

public class Reading {
    private final String station;
    private final String date;
    private final double temp;
    private final int humidity;

    public Reading(String station, String date, double temp, int humidity) {
        if (station == null || station.isBlank() || date == null || date.isBlank()) {
            throw new IllegalArgumentException("Станция и дата не могут быть пустыми");
        }
        this.station = station.trim();
        this.date = date.trim();
        this.temp = temp;
        this.humidity = humidity;
    }

    // Метод разбора строки формата: station;date;temp;humidity
    public static Reading parse(String line) {
        String[] parts = line.split(";");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Ожидалось 4 поля, найдено: " + parts.length);
        }
        String station = parts[0].trim();
        String date = parts[1].trim();
        double temp = Double.parseDouble(parts[2].trim());
        int humidity = Integer.parseInt(parts[3].trim());

        return new Reading(station, date, temp, humidity);
    }

    public String getStation() { return station; }
    public String getDate() { return date; }
    public double getTemp() { return temp; }
    public int getHumidity() { return humidity; }

    @Override
    public String toString() {
        return String.format("Станция: %-12s | Дата: %s | Темп: %5.1f°C | Влажность: %d%%",
                station, date, temp, humidity);
    }
}
