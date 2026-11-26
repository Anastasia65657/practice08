package pr5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MachineTests {

    @Test // Проверяем правильность расчёта формулы производительности Пресса
    public void testPressThroughput() {
        Machine press = new Press("Пресс-Тест", 2.5, 50.0);
        // Формула пресса: baseFactor * pressureTons -> 2.5 * 50 = 125.0
        assertEquals(125.0, press.throughput(), 0.001);
    }

    @Test // Проверяем правильность расчёта формулы Конвейера
    public void testConveyorThroughput() {
        Machine conveyor = new Conveyor("Конвейер-Тест", 1.2, 80.0);
        // Формула конвейера: baseFactor * speed -> 1.2 * 80 = 96.0
        assertEquals(96.0, conveyor.throughput(), 0.001);
    }

    @Test // Проверяем правильность расчёта формулы ЧПУ станка
    public void testCncThroughput() {
        Machine cnc = new Cnc("ЧПУ-Тест", 4.0, 5);
        // Формула ЧПУ: (baseFactor * 100) / axesCount -> (4.0 * 100) / 5 = 80.0
        assertEquals(80.0, cnc.throughput(), 0.001);
    }

    @Test // Проверяем, что лямбда и анонимный класс считают сумму одинаково
    public void testLambdaAndAnonymousEquality() {
        Machine press = new Press("Пресс", 2.5, 50.0);       // 125.0
        Machine conveyor = new Conveyor("Конвейер", 1.2, 80.0); // 96.0
        Machine cnc = new Cnc("ЧПУ", 4.0, 5);                  // 80.0

        Machine[] factoryFloor = { press, conveyor, cnc };
        MachineService service = new MachineService(factoryFloor);

        // Реализация через Анонимный класс
        Calculation anonymousCalc = new Calculation() {
            @Override
            public double calculate(Machine machine) {
                return machine.throughput();
            }
        };
        double sumAnonymous = service.aggregateSum(anonymousCalc);

        // Реализация через Лямбду
        Calculation lambdaCalc = m -> m.throughput();
        double sumLambda = service.aggregateSum(lambdaCalc);

        // Проверяем, что результаты совпали
        assertEquals(301.0, sumAnonymous, 0.001);
        assertEquals(301.0, sumLambda, 0.001);
        assertEquals(sumAnonymous, sumLambda, "Сумма анонимного класса и лямбды должна совпадать!");
    }

    @Test // Проверяем фильтрацию Selection
    public void testFilterWithCapturedThreshold() {
        Machine press = new Press("Тяжелый Пресс", 2.5, 50.0);    // 125.0
        Machine cnc = new Cnc("Слабый ЧПУ", 1.0, 5);              // 20.0

        Machine[] machines = { press, cnc };

        // Effectively final, которую захватит лямбда
        double threshold = 100.0;

        // Проверяем работу логики фильтрации напрямую
        Selection selection = m -> m.throughput() > threshold;

        assertTrue(selection.test(press), "Пресс с производительностью 125 должен пройти порог 100");
        assertFalse(selection.test(cnc), "ЧПУ с производительностью 20 НЕ должен пройти порог 100");
    }
}