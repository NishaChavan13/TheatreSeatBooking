import java.util.*;
public class Main {
    public static void main(String[] args) {
        int row = 10;
        int totalSeats = 100;
        TheatreBookingSeats PVR = new TheatreBookingSeats("PVR", row, totalSeats);
        PVR.printSeatMap();
        bookSeat(PVR, 'A', 9);

    }

    private static void bookSeat(TheatreBookingSeats theatre, char row, int seatNo){
        String seat = theatre.reserveSeat(row, seatNo);
        if(seat!=null){
            System.out.println("Congratulation! Your reserved seat is " + seat);
            theatre.printSeatMap();
        }else{
            System.out.println("Sorry! Unable to reserve "+ row + seatNo);
        }
    }
}