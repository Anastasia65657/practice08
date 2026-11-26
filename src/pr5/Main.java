package pr5;

public class Main {
    public static void main(String[] args) {
        // Создаем объекты наследников
        Machine press = new Press("Гидравлический Пресс-01", 2.5, 50.0);
        Machine conveyor = new Conveyor("Главная Лента Сборки", 1.2, 80.0);
        Machine cnc = new Cnc("5-осевой ЧПУ Фрезер", 4.0, 5);

        // Массив базового типа для сервиса
        Machine[] factoryFloor = { press, conveyor, cnc };
        MachineService service = new MachineService(factoryFloor);

        // Массивы интерфейсных типов
        Maintainable[] workshopService = { (Maintainable) press, (Maintainable) conveyor, (Maintainable) cnc };
        Startable[] workshopPower = { (Startable) press, (Startable) conveyor, (Startable) cnc };

        service.processMaintainables(workshopService);
        service.processStartables(workshopPower);

        System.out.println("\n==================================================");
        System.out.println("   РАБОТА С ФУНКЦИОНАЛЬНЫМИ ИНТЕРФЕЙСАМИ И ЛЯМБДАМИ");
        System.out.println("==================================================");

        // Способ 1: Анонимный класс
        Calculation anonymousCalc = new Calculation() {
            @Override
            public double calculate(Machine machine) {
                return machine.throughput();
            }
        };
        double sumAnonymous = service.aggregateSum(anonymousCalc);

        // Способ 2: Лямбда-выражение
        Calculation lambdaCalc = m -> m.throughput();
        double sumLambda = service.aggregateSum(lambdaCalc);

        System.out.printf("Сумма (Анонимный класс): %.2f%n", sumAnonymous);
        System.out.printf("Сумма (Лямбда-выражение): %.2f%n", sumLambda);
        System.out.println("Совпадение сумм подтверждено: " + (sumAnonymous == sumLambda));

        // Тестирование Selection
        System.out.println("\n--- Отбор оборудования по разным порогам производительности ---");

        // Первый порог
        double firstThreshold = 90.0;
        System.out.println("Порог отбора > " + firstThreshold + ":");
        service.filterAndPrint(m -> m.throughput() > firstThreshold);

        // Второй порог
        double secondThreshold = 100.0;
        System.out.println("Порог отбора > " + secondThreshold + ":");
        service.filterAndPrint(m -> m.throughput() > secondThreshold);

        // Присваиваем ссылку на метод переменной типа Runnable
        Runnable report = service::printReport;
        // Вызываем метод run() без запуска в отдельном потоке
        report.run();
    }
}
