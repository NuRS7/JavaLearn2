import java.util.Scanner;

public class OverliddingClasses {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Checking checking = new Checking();
        try {
            System.out.println(checking.parseIntFromString(input.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number");
        } finally {
            System.out.println("Good bye!");
        }
    }
}

class Checking {
    int parseIntFromString(String s) {
        return Integer.parseInt(s.trim());
    }
}