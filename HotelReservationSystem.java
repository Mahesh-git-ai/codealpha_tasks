import java.util.*;

class Room {
    int roomNumber;
    String category;
    boolean isBooked;

    Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isBooked = false;
    }
}

class Booking {
    String guestName;
    Room room;
    String paymentStatus;

    Booking(String guestName, Room room, String paymentStatus) {
        this.guestName = guestName;
        this.room = room;
        this.paymentStatus = paymentStatus;
    }
}

public class HotelReservationSystem {
    static Scanner scanner = new Scanner(System.in);
    static List<Room> rooms = new ArrayList<>();
    static List<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {
        initializeRooms();

        while (true) {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Booking Details");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 : 
                viewAvailableRooms();
                break;
                case 2 : makeReservation();
                break;
                case 3 : viewBookings();
                break;
                case 4 : {
                    System.out.println("Thank you for chooing our hotel.we hope you had a wonderful stay and we look forward to welcoming you back soon.");
                    return;
                }
                default : System.out.println("Invalid choice.");
            }
        }
    }

    static void initializeRooms() {
        rooms.add(new Room(101, "Single"));
        rooms.add(new Room(102, "Single"));
        rooms.add(new Room(201, "Double"));
        rooms.add(new Room(202, "Double"));
        rooms.add(new Room(301, "Suite"));
    }

    static void viewAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        boolean found = false;
        for (Room room : rooms) {
            if (!room.isBooked) {
                System.out.println("Room " + room.roomNumber + " - " + room.category);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No rooms available.");
        }
    }

    static void makeReservation() {
        viewAvailableRooms();
        System.out.print("Enter your name: ");
        scanner.nextLine(); // clear buffer
        String name = scanner.nextLine();

        System.out.print("Enter room number to reserve: ");
        int roomNum = scanner.nextInt();

        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.roomNumber == roomNum && !room.isBooked) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Room not available or doesn't exist.");
            return;
        }

        // Simulated Payment
        System.out.print("Enter payment amount to confirm booking: ");
        double payment = scanner.nextDouble();

        if (payment <= 0) {
            System.out.println("Invalid payment. Booking failed.");
            return;
        }

        selectedRoom.isBooked = true;
        Booking booking = new Booking(name, selectedRoom, "Paid: $" + payment);
        bookings.add(booking);

        System.out.println("Booking successful for " + name + " in Room " + selectedRoom.roomNumber);
    }

    static void viewBookings() {
        System.out.println("\nCurrent Bookings:");
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            for (Booking b : bookings) {
                System.out.printf("Guest: %s | Room: %d (%s) | Payment: %s\n",
                        b.guestName, b.room.roomNumber, b.room.category, b.paymentStatus);
            }
        }
    }
}