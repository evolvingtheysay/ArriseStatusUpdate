package ticketbooking;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        SeatManager seatManager = new SeatManager(10);
        TicketBooking bookingService = new TicketBooking(seatManager);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.execute(new BookingThread(bookingService, "abhinav", 4));
        executor.execute(new BookingThread(bookingService, "japneet", 3));
        executor.execute(new BookingThread(bookingService, "tanuj", 5));

        executor.shutdown();
    }
}
