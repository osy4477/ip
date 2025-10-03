import java.util.Scanner;

/**
 * Handles user interactions such as printing messages and reading input.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui instance with a new Scanner.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Ben\nWhat can I do for you?");
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The user's input command.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}