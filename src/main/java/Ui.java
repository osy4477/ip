import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
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

    public String readCommand() {
        return scanner.nextLine();
    }
}