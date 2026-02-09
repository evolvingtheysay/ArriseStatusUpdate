public class Deadlock {

    static final Object resourceA = new Object();
    static final Object resourceB = new Object();
    static final Object resourceC = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("Thread 1 locked resource A");
                sleep();
                synchronized (resourceB) {
                    System.out.println("Thread 1 locked resource B");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (resourceB) {
                System.out.println("Thread 2 locked resource B ");
                sleep();
                synchronized (resourceC) {
                    System.out.println("Thread 2 locked resource C");
                }
            }
        });

        Thread t3 = new Thread(() -> {
            synchronized (resourceC) {
                System.out.println("Thread 3 locked resource C");
                sleep();
                synchronized (resourceA) {/opt/jdk-17/bin/java --module-path /opt/javafx-sdk-18.0.1/lib --add-modules=javafx.controls,javafx.fxml,javafx.swing,javafx.base --add-exports javafx.base/com.sun.javafx.event=ALL-UNNAMED -jar dealermodule.jar
                    System.out.println("Thread 3 locked resource A");
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }

    private static void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {}
    }
}
