package pr5;

public class MachineService {
    private final Machine[] machines; //массив всех станков

    public MachineService(Machine[] machines) {
        this.machines = machines;
    }

    //Полиморфная работа с интерфейсными типами
    public void processMaintainables(Maintainable[] maintainables) {
        System.out.println("=== Техническое обслуживание оборудования ===");
        for (Maintainable m : maintainables) {
            m.maintain();
            m.printMaintenanceStatus();
        }
    }

    public void processStartables(Startable[] startables) {
        System.out.println("\n=== Управление запуском ===");
        for (Startable s : startables) {
            s.start();
            s.stop();
        }
    }

    // Агрегирование сумм через переданный функциональный интерфейс
    public double aggregateSum(Calculation calc) {
        double total = 0;
        for (Machine m : machines) {
            // Вызываем логику переданной лямбды/анонимного класса, а не напрямую throughput()
            total += calc.calculate(m);
        }
        return total;
    }

    // Фильтрация объектов по условию (Selection)
    public void filterAndPrint(Selection selection) {
        for (Machine m : machines) {
            if (selection.test(m)) {
                System.out.println("  [Пройден отбор] " + m.name + " -> Производительность: " + m.throughput());
            }
        }
    }

    // Метод для ссылки на метод
    public void printReport() {
        System.out.println("\n===== ФАБРИЧНЫЙ ОТЧЁТ ПО ОБОРУДОВАНИЮ =====");
        for (Machine m : machines) {
            System.out.println(m);
        }
        System.out.println("===========================================");
    }
}