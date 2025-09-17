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

            try {
                if (input.equals("bye")) {
                    System.out.println(byeMessage);
                    break;

                } else if (input.equals("list")) {
                    if (taskCount == 0) {
                        System.out.println("Your list is empty!");
                    } else {
                        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");
                        for (int i = 0; i < taskCount; i++) {
                            output.append((i + 1) + "." + tasks[i] + "\n");
                        }
                        if (output.charAt(output.length() - 1) == '\n') {
                            output.setLength(output.length() - 1);
                        }
                        System.out.println(output.toString());
                    }

                } else if (words[0].equals("mark")) {
                    if (words.length < 2) throw new IllegalArgumentException("Please specify which task to mark.");
                    int index = Integer.parseInt(words[1]) - 1;
                    tasks[index].markDone();
                    System.out.println("Nice! I've marked this task as done:\n  " + tasks[index]);

                } else if (words[0].equals("unmark")) {
                    if (words.length < 2) throw new IllegalArgumentException("Please specify which task to unmark.");
                    int index = Integer.parseInt(words[1]) - 1;
                    tasks[index].markNotDone();
                    System.out.println("OK, I've marked this task as not done yet:\n  " + tasks[index]);

                } else if (words[0].equals("todo")) {
                    if (words.length < 2 || words[1].trim().isEmpty()) {
                        throw new IllegalArgumentException("Sorry the description of a todo cannot be empty...");
                    }
                    Task t = new Todo(words[1]);
                    tasks[taskCount++] = t;
                    System.out.println("Got it. I've added this task:\n  " + t +
                            "\nNow you have " + taskCount + " tasks in the list.");

                } else if (words[0].equals("deadline")) {
                    if (words.length < 2 || !words[1].contains(" /by ")) {
                        throw new IllegalArgumentException("Deadline must have a description and '/by <time>'.");
                    }
                    String[] parts = words[1].split(" /by ", 2);
                    Task t = new Deadline(parts[0], parts[1]);
                    tasks[taskCount++] = t;
                    System.out.println("Got it. I've added this task:\n  " + t +
                            "\nNow you have " + taskCount + " tasks in the list.");

                } else if (words[0].equals("event")) {
                    if (words.length < 2 || !words[1].contains(" /from ") || !words[1].contains(" /to ")) {
                        throw new IllegalArgumentException("Event must have a description, '/from <start>', and '/to <end>'.");
                    }
                    String[] parts = words[1].split(" /from | /to ");
                    Task t = new Event(parts[0], parts[1], parts[2]);
                    tasks[taskCount++] = t;
                    System.out.println("Got it. I've added this task:\n  " + t +
                            "\nNow you have " + taskCount + " tasks in the list.");

                } else if (words[0].equals("delete")) {
                    if (words.length < 2) throw new IllegalArgumentException("Please specify which task to delete.");
                    int index = Integer.parseInt(words[1]) - 1;

                    if (index < 0 || index >= taskCount) {
                        throw new IllegalArgumentException("Invalid task number.");
                    }

                    Task removed = tasks[index];

                    // Shift tasks left to fill the gap
                    for (int i = index; i < taskCount - 1; i++) {
                        tasks[i] = tasks[i + 1];
                    }
                    tasks[--taskCount] = null; // clear last slot

                    System.out.println("Noted. I've removed this task:\n  " + removed +
                            "\nNow you have " + taskCount + " tasks in the list.");
                }
                else {
                    throw new IllegalArgumentException("Sorry but I don't know what that means...");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
