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
                if (output.length() > 0 && output.charAt(output.length() - 1) == '\n') {
                    output.setLength(output.length() - 1);
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

            } else if (words[0].equals("todo")) {
                Task t = new Todo(words[1]);
                tasks[taskCount++] = t;
                System.out.println("Got it. I've added this task:\n  " + t +
                        "\nNow you have " + taskCount + " tasks in the list.");

            } else if (words[0].equals("deadline")) {
                String[] parts = words[1].split(" /by ", 2);
                Task t = new Deadline(parts[0], parts[1]);
                tasks[taskCount++] = t;
                System.out.println("Got it. I've added this task:\n  " + t +
                        "\nNow you have " + taskCount + " tasks in the list.");

            } else if (words[0].equals("event")) {
                String[] parts = words[1].split(" /from | /to ");
                // parts[0] = desc, parts[1] = from, parts[2] = to
                Task t = new Event(parts[0], parts[1], parts[2]);
                tasks[taskCount++] = t;
                System.out.println("Got it. I've added this task:\n  " + t +
                        "\nNow you have " + taskCount + " tasks in the list.");

            } else {
                System.out.println("I'm sorry, I don't know what that means :-(");
            }
        }
    }
}
