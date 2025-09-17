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
        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (true) {
            String input = scanner.nextLine();
            String[] words = input.split(" ", 2);

            if (input.equals("bye")) {
                System.out.println(byeMessage);
                break;
            } else if (input.equals("list")) {
                StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");
                for (int i = 0; i < taskCount; i++) {
                    output.append((i + 1) + "." + tasks[i] + "\n");
                }
                if (output.charAt(output.length() - 1) == '\n') {
                    output.setLength(output.length() - 1); // remove trailing newline
                }
                System.out.println(output.toString());

            } else if (words[0].equals("mark")) {
                int index = Integer.parseInt(words[1]) - 1;
                tasks[index].markDone();
                System.out.println("Nice! I've marked this task as done:\n  " + tasks[index]);

            } else if (words[0].equals("unmark")) {
                int index = Integer.parseInt(words[1]) - 1;
                tasks[index].markNotDone();
                System.out.println("OK, I've marked this task as not done yet:\n  " + tasks[index]);

            } else {
                // Add task
                tasks[taskCount] = new Task(input);
                taskCount++;
                System.out.println("added: " + input);
            }
        }
    }
}
