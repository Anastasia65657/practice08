package pr5;

public interface Maintainable {
    void maintain();

    // Default-метод
    default void printMaintenanceStatus() {
        System.out.println("[Maintainable] Объект готов к техническому обслуживанию.");
    }
}
