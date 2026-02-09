import java.util.concurrent.locks.*;

class LockDemo {
    private final Lock lock = new ReentrantLock();

    void print() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " executing");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockDemo obj = new LockDemo();

        new Thread(obj::print).start();
        new Thread(obj::print).start();
    }
}
