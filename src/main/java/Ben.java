import java.util.Scanner;
import java.util.ArrayList;

public class Ben {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
<<<<<<< HEAD
        ArrayList<Task> tasks = new ArrayList<>();
=======

        Storage storage = new Storage("./data/duke.txt");
        ArrayList<Task> tasks = storage.load();  // load tasks at startup

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Ben");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
>>>>>>> branch-Level-7

        while (true) {
            String input = scanner.nextLine();
            String[] words = input.split(" ", 2);

            try {
                if (input.equals("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;

                } else if (input.equals("list")) {
<<<<<<< HEAD
                    if (tasks.isEmpty()) {
                        System.out.println("Your list is empty!");
                    } else {
                        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");
                        for (int i = 0; i < tasks.size(); i++) {
                            output.append((i + 1) + "." + tasks.get(i) + "\n");
                        }
                        if (output.charAt(output.length() - 1) == '\n') {
                            output.setLength(output.length() - 1);
                        }
                        System.out.println(output.toString());
                    }
=======
                    // same as before ...

>>>>>>> branch-Level-7
                } else if (words[0].equals("mark")) {
                    int index = Integer.parseInt(words[1]) - 1;
                    tasks.get(index).markDone();
                    System.out.println("Nice! I've marked this task as done:\n  " + tasks.get(index));
<<<<<<< HEAD
=======
                    storage.save(tasks);   //save after change
>>>>>>> branch-Level-7

                } else if (words[0].equals("unmark")) {
                    int index = Integer.parseInt(words[1]) - 1;
                    tasks.get(index).markNotDone();
                    System.out.println("OK, I've marked this task as not done yet:\n  " + tasks.get(index));
<<<<<<< HEAD
=======
                    storage.save(tasks);   //save after change
>>>>>>> branch-Level-7

                } else if (words[0].equals("todo")) {
                    Task t = new Todo(words[1]);
                    tasks.add(t);
<<<<<<< HEAD
                   System.out.println("Got it. I've added this task:\n  " + t +
                            "\nNow you have " + tasks.size() + " tasks in the list.");
=======
                    System.out.println("Got it. I've added this task:\n  " + t +
                            "\nNow you have " + tasks.size() + " tasks in the list.");
                    storage.save(tasks);   // save after change
>>>>>>> branch-Level-7

                } else if (words[0].equals("deadline")) {
                    String[] parts = words[1].split(" /by ", 2);
                    Task t = new Deadline(parts[0], parts[1]);
                    tasks.add(t);
                    System.out.println("Got it. I've added this task:\n  " + t +
                            "\nNow you have " + tasks.size() + " tasks in the list.");
<<<<<<< HEAD
=======
                    storage.save(tasks);   // save after change
>>>>>>> branch-Level-7

                } else if (words[0].equals("event")) {
                    String[] parts = words[1].split(" /from | /to ");
                    Task t = new Event(parts[0], parts[1], parts[2]);
                    tasks.add(t);
                    System.out.println("Got it. I've added this task:\n  " + t +
                            "\nNow you have " + tasks.size() + " tasks in the list.");
<<<<<<< HEAD
=======
                    storage.save(tasks);   // ✅ save after change

                } else if (words[0].equals("delete")) {
                    int index = Integer.parseInt(words[1]) - 1;
                    Task removed = tasks.remove(index);
                    System.out.println("Noted. I've removed this task:\n  " + removed +
                            "\nNow you have " + tasks.size() + " tasks in the list.");
                    storage.save(tasks);   // ✅ save after change
>>>>>>> branch-Level-7

                } else if (words[0].equals("delete")) {
                    if (words.length < 2) throw new IllegalArgumentException("Please specify which task to delete.");
                    int index = Integer.parseInt(words[1]) - 1;

                    if (index < 0 || index >= tasks.size()) {
                        throw new IllegalArgumentException("Invalid task number.");
                    }

                    Task removed = tasks.remove(index);

                    System.out.println("Noted. I've removed this task:\n  " + removed +
                            "\nNow you have " + tasks.size() + " tasks in the list.");
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
