package pr7;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AirportTests {

    @Test
    public void testStringStack() {
        Stack<String> airlines = new Stack<>();
        airlines.push("Aeroflot");
        airlines.push("S7 Airlines");

        assertEquals(2, airlines.size());
        assertEquals("S7 Airlines", airlines.peek());
        assertEquals("S7 Airlines", airlines.pop());
        assertEquals("Aeroflot", airlines.pop());
        assertTrue(airlines.isEmpty());
    }

    @Test
    public void testIntegerStack() {
        Stack<Integer> flightNumbers = new Stack<>();
        flightNumbers.push(101);
        flightNumbers.push(5223);

        assertEquals(2, flightNumbers.size());
        assertEquals(5223, flightNumbers.pop());
        assertEquals(101, flightNumbers.pop());
        assertTrue(flightNumbers.isEmpty());
    }

    @Test
    public void testPecsWildcard() {
        Stack<Number> cargoWeights = new Stack<>();
        List<Integer> boxWeights = List.of(50, 120, 85);

        cargoWeights.addAll(boxWeights);

        assertEquals(3, cargoWeights.size());
        assertEquals(85, cargoWeights.pop());
    }

    @Test
    public void testPairFactoryOf() {
        Pair<String, Integer> flightInfo = Pair.of("SU-2130", 1450);

        assertNotNull(flightInfo);
        assertEquals("SU-2130", flightInfo.getFirst());
        assertEquals(1450, flightInfo.getSecond());
    }

    @Test
    public void testBracketValidatorValid() {
        String validFormat = "Рейс(Груз[Контейнер{A1}]-ОК)";
        assertTrue(AirportUtils.isBracketSequenceValid(validFormat));
    }

    @Test
    public void testBracketValidatorInvalid() {
        String invalidFormat = "Рейс(Груз[Контейнер)-Ошибка]";
        assertFalse(AirportUtils.isBracketSequenceValid(invalidFormat));
    }

    @Test
    public void testEmptyStackException() {
        Stack<Character> emptyStack = new Stack<>();

        // Проверяем, что выбрасывается именно требуемое unchecked-исключение
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            emptyStack.pop();
        });

        assertEquals("Стек пуст! Невозможно выполнить операцию pop.", exception.getMessage());
    }
}