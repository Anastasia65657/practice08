package pr7;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== 1. ПРОВЕРКА СТЕКА НА ДВУХ РАЗНЫХ ТИПАХ (String и Integer) ===");

        Stack<String> airlines = new Stack<>();
        airlines.push("Aeroflot");
        airlines.push("S7 Airlines");
        airlines.push("Aurora");
        System.out.println("Верхний элемент (peek): " + airlines.peek());
        System.out.println("Извлечено (pop): " + airlines.pop());
        System.out.println("Осталось элементов в стеке строк: " + airlines.size());

        Stack<Integer> flights = new Stack<>();
        flights.push(101);
        flights.push(5223);
        System.out.println("Извлечён номер рейса (pop): " + flights.pop());

        System.out.println("\n=== 2. ПРОВЕРКА ПРАВИЛА PECS (Producer Extends) ===");
        Stack<Number> cargoWeights = new Stack<>();
        List<Integer> boxWeights = List.of(50, 120, 85);

        cargoWeights.addAll(boxWeights);
        System.out.println("Загружено грузов в стек Number: " + cargoWeights.size());
        System.out.println("Последний загруженный вес: " + cargoWeights.pop() + " кг");

        System.out.println("\n=== 3. ПРОВЕРКА КОНТЕЙНЕРА Pair.of() ===");
        Pair<String, Integer> flightGate = Pair.of("SU-2130", 14);
        System.out.println("Информация о посадке: " + flightGate);
        System.out.println("Рейс: " + flightGate.getFirst() + ", Выход: " + flightGate.getSecond());

        System.out.println("\n=== 4. АЛГОРИТМ ПРОВЕРКИ СКОБОК (АЭРОПОРТ) ===");
        String validCode = "Рейс(Груз[Контейнер{A1}]-ОК)";
        String invalidCode = "Рейс(Груз[Контейнер)-Ошибка]";
        System.out.println("Строка '" + validCode + "' корректна: " + AirportUtils.isBracketSequenceValid(validCode));
        System.out.println("Строка '" + invalidCode + "' корректна: " + AirportUtils.isBracketSequenceValid(invalidCode));

        System.out.println("\n=== 5. ПРОВЕРКА ИСКЛЮЧЕНИЯ ПРИ ПУСТОМ СТЕКЕ ===");
        Stack<Character> emptyStack = new Stack<>();
        try {
            emptyStack.pop();
        } catch (IllegalStateException e) {
            System.out.println("Успешно перехвачено ожидаемое исключение: " + e.getMessage());
        }
    }
}