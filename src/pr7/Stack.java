package pr7;

import java.util.Arrays;

public class Stack<T> {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;

    public Stack() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public void push(T element) {
        ensureCapacity();
        elements[size++] = element;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Стек пуст! Невозможно выполнить операцию pop.");
        }
        T element = (T) elements[--size];
        elements[size] = null;
        return element;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Стек пуст! Невозможно выполнить операцию peek.");
        }
        return (T) elements[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    // Wildcard по правилу PECS
    public void addAll(Iterable<? extends T> src) {
        for (T element : src) {
            push(element);
        }
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
    }
}