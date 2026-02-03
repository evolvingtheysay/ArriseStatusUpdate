package ticketbooking;

class BookingThread implements Runnable {

    private final TicketBooking service;
    private final String user;
    private final int seats;

    public BookingThread(TicketBooking service, String user, int seats) {
        this.service = service;
        this.user = user;
        this.seats = seats;
    }

    @Override
    public void run() {
        service.book(user, seats);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (Math.random() > 0.7) {
            service.cancel(user, seats);
        }
    }
}
