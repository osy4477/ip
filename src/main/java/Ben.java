import java.util.Scanner;

public class Ben {
    public static void main(String[] args) {

        String greeting = "____________________________________________________________\n" +
                " Hello! I'm Ben\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        String byeMessage = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";

        System.out.println(greeting);
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100]; // fixed-size array
        int taskCount = 0;                // number of tasks stored

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println(byeMessage);
                break;
            } else if (input.equals("list")) {
                //display stored tasks
                StringBuilder output = new StringBuilder();
                for (int i = 0; i < taskCount; i++) {
                    output.append((i + 1) + ". " + tasks[i] + "\n");
                }
                //remove last newline if exists
                if (output.length() > 0) {
                    output.setLength(output.length() - 1);
                }
                System.out.println(output.toString());
            } else {
                //add new task
                tasks[taskCount] = input;
                taskCount++;
                System.out.println("added: " + input);
            }
        }
    }
}
