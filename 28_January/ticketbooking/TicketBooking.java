package ticketbooking;

public class TicketBooking {

    private final SeatManager seatManager;

    public TicketBooking(SeatManager seatManager) {
        this.seatManager = seatManager;
    }

    public void book(String user, int seats) {
        seatManager.bookSeats(user, seats);
    }

    public void cancel(String user, int seats) {
        seatManager.cancelSeats(user, seats);
    }
}
