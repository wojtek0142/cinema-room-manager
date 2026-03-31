package cinema;
import java.util.Scanner;
import java.util.Arrays;

public class Cinema {

    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        Scanner scan = new Scanner(System.in);
        int rows = Integer.parseInt(scan.nextLine());
        System.out.println("Enter the number of seats in each row:");
        int seats = Integer.parseInt(scan.nextLine());

        char[][] places = new char[rows][seats];
        for (char[] row : places) {
            Arrays.fill(row, 'S');
        }

        boolean running = true;
        int numberOfTickets = 0;
        int income = 0;

        while (running) {
            System.out.println("""
                    1. Show the seats
                    2. Buy a ticket
                    3. Statistics
                    0. Exit
                    """);
            int action = Integer.parseInt(scan.nextLine());
            switch (action) {
                case 1:
                    showSeats(places);
                    break;
                case 2:
                    boolean isFree = false;
                    int row = 0;
                    int seat = 0;
                    while (!isFree) {
                        System.out.println("Enter a row number:");
                        row = Integer.parseInt(scan.nextLine());
                        System.out.println("Enter a seat number in that row:");
                        seat = Integer.parseInt(scan.nextLine());
                        if (row < 1 || row > places.length || seat < 1 || seat > places[0].length) {
                            System.out.println("Wrong input!");
                        }
                        else if (places[row - 1][seat - 1] == 'B') {
                            System.out.println("That ticket has already been purchased!");
                        } else {
                            isFree = true;
                        }
                    }
                    places[row-1][seat-1] = 'B';
                    numberOfTickets+=1;
                    int price = calcTicketPrice(rows,seats,row);
                    income += price;
                    System.out.println("Ticket price: $" + price);
                    System.out.println();
                    break;
                case 3:
                    System.out.println("Number of purchased tickets: "+ numberOfTickets);
                    System.out.printf("Percentage: %.2f%%\n",1.0 * numberOfTickets/(seats*rows)*100);
                    System.out.println("Current income: $" + income);
                    System.out.println("Total income: $" + calcTotalIncome(rows,seats));
                    System.out.println();
                    break;
                case 0:
                    running = false;
                    break;
                default:
            }
        }
    }
    public static void showSeats(char[][] places){
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i <= places[0].length; i++){
            System.out.print(" " + i);
        }
        System.out.println();
        for (int n = 0; n < places.length; n++) {
            System.out.print(n+1);
            for (int j = 0; j < places[n].length; j++){
                System.out.print(" "+ places[n][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    public static int calcTicketPrice(int rows, int seats, int row){
        if (rows * seats <= 60) {
            return 10;
        } else if (row <= rows / 2){
            return 10;
        } else {
            return 8;
        }
    }
    public static int calcTotalIncome(int rows, int seats){
        if (rows * seats <= 60) {
            return rows * seats * 10;
        }
        int frontRows = rows / 2;
        int backRows = rows - frontRows;
        return frontRows * seats * 10 + backRows * seats * 8;
    }
}