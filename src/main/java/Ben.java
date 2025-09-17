import java.util.Scanner;

public class Ben {
    public static void main(String[] args) {

        String greeting = "____________________________________________________________\n" +
                " Hello! I'm Ben\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        String byeMessage = " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";

        System.out.println(greeting);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println(byeMessage);
                break;
            } else {
                System.out.println(input);
            }
        }
    }
}
