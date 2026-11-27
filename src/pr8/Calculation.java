package pr8;

@FunctionalInterface
public interface Calculation<T> {
    double calculate(T value);
}