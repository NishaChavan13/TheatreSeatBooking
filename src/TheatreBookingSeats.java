import java.util.*;
public class TheatreBookingSeats {

    class Seat implements Comparable<Seat>{

        private String seatNum;
        private  boolean reserved;

        public Seat(char rowChar, int seatNo){
            this.seatNum = "%c%03d".formatted(rowChar, seatNo).toUpperCase();
        }

        public String toString(){
            return seatNum;
        }

        @java.lang.Override
        public int compareTo(Seat o) {
            return seatNum.compareTo(o.seatNum);
        }
    }

    private String theatreName;
    private int seatsPerRow;

    NavigableSet<Seat> seats;

    public TheatreBookingSeats(String theatreName, int rows, int totalSeats){
        this.theatreName = theatreName;
        this.seatsPerRow = totalSeats/rows;

        seats = new TreeSet<>();
        for(int i=0; i<totalSeats; i++){
            char rowChar = (char)(i/seatsPerRow + (int) 'A');
            int seatInRow = i % seatsPerRow +1;
            seats.add(new Seat(rowChar, seatInRow));
        }
    }

    public void printSeatMap(){
        String seperatorLine = "-".repeat(90);
        System.out.printf("%1$s%n%2$s Seat Map%n%1$s%n", seperatorLine, theatreName);
        int index = 0;
        for(Seat s : seats){
            System.out.printf("%-8s%s",
                              s.seatNum + ((s.reserved)?"(\u25CF)" : ""),
                              ((index++ +1) % seatsPerRow == 0)? "\n" : "");
        }

        System.out.println(seperatorLine);
    }

    public String reserveSeat(char row, int seat){
        Seat requestedSeat = new Seat(row, seat);
        Seat requested = seats.floor(requestedSeat);

        if(requested == null || !requested.seatNum.equals(requestedSeat.seatNum)){
            System.out.print("--> No such seat: " + requestedSeat);
            System.out.printf(": Seat must be between %s and %s%n", seats.first().seatNum , seats.last().seatNum);
        }else{
            if(!requested.reserved){
                requested.reserved = true;
                return requested.seatNum;
            }else{
                System.out.println("Seat's already reserved.");
            }
        }
        return null;
    }
}
