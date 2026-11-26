package pr7;

public class AirportUtils {
    public static boolean isBracketSequenceValid(String sequence) {
        if (sequence == null) {
            return true;
        }

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < sequence.length(); i++) {
            char ch = sequence.charAt(i);

            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                char openBracket = stack.pop();
                if (!isMatching(openBracket, ch)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static boolean isMatching(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '[' && close == ']') ||
                (open == '{' && close == '}');
    }
}