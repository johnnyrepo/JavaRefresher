package streams;

import java.util.Random;

public record SeatReservation(char rowMarker, int seatNumber, boolean isReserved) {

    public SeatReservation(char rowMarker, int seatNumber) {
        this(rowMarker, seatNumber, new Random().nextBoolean());
    }
}
