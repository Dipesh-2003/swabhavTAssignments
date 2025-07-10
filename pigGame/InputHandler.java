import java.util.Scanner;

public class InputHandler {
    private Scanner scanner;

    public InputHandler() {
        scanner = new Scanner(System.in);
    }

    public String getRollOrHold() {
        while (true) {
            System.out.print("Roll or hold? (r/h): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("r") || input.equals("h")) {
                return input;
            }
            System.out.println("Invalid input. Please enter 'r' or 'h'.");
        }
    }

    public void close() {
        scanner.close();
    }
}
