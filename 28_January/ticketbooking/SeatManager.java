package ticketbooking;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock; //reentrant lock ke bina wait(), await() aur notify() etc use nahin kar sakte kya
public class  SeatManager{

    private final AtomicInteger availableSeats;
    private final ReentrantLock lock;  // how will I get to know
    private final Condition seatsAvailable;

    public SeatManager(int totalSeats){
        this.availableSeats = new AtomicInteger(totalSeats);
        this.lock = new ReentrantLock(true);
        // fairness of lock ke liye reentrant use kiya also wait() method aur notify() method use karne ke liye
        this.seatsAvailable = lock.newCondition();
    }

    // Queue waala approach laga so that the user gets back to zero
    public boolean bookSeats(String user, int seatsRequested){
        lock.lock();
        try {
            System.out.println(user + "is trying to book the " + seatsRequested + "seats");

            // yahan if else use kiya toh galat kyun hai????
            if (availableSeats.get() < seatsRequested) {
                System.out.println(" No seats available for user " + user);
                seatsAvailable.await();// yeh method mujhe nahin maalum thi
            } else {
                availableSeats.addAndGet(-seatsRequested);

                System.out.println( user + " booked " + seatsRequested + " seats. Remaining: " + availableSeats.get());
                return true;
            }

        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
            return false;
        }finally{
            lock.unlock();
        }
    }

    public void cancelSeats(String user, int seats) {
        lock.lock();
        try {
            availableSeats.addAndGet(seats);
            System.out.println( user + " cancelled " + seats + " seats. Available now: " + availableSeats.get());

            seatsAvailable.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public int getAvailableSeats() {
        return availableSeats.get();
    }
}
