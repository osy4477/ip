import java.util.Scanner;
import java.util.ArrayList;

public class Ben {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Ben(String filePath) {
            ui = new Ui();
            storage = new Storage(filePath);
            try {
                tasks = new TaskList(storage.load());
            } catch (Exception e) {
                ui.showError("Error loading file. Starting with empty task list.");
                tasks = new TaskList();
            }
        }

        public void run() {
            ui.showWelcome();
            boolean isExit = false;
            while (!isExit) {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                switch (c.getCommand()) {
                case "bye":
                    ui.showBye();
                    isExit = true;
                    break;
                case "list":
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i));
                    }
                    break;
                case "todo":
                    Task t = new Todo(c.getArgs());
                    tasks.add(t);
                    System.out.println("Added: " + t);
                    break;
                case "deadline":
                    String[] parts = c.getArgs().split(" /by ", 2);
                    if (parts.length < 2) {
                        ui.showError("Deadline must include a description and '/by <time>'.");
                        break;
                    }
                    Task d = new Deadline(parts[0], parts[1]);
                    tasks.add(d);
                    System.out.println("Added: " + d);
                    break;

                case "event":
                    String[] eParts = c.getArgs().split(" /from | /to ");
                    if (eParts.length < 3) {
                        ui.showError("Event must include description, '/from <start>', and '/to <end>'.");
                        break;
                    }
                    Task e = new Event(eParts[0], eParts[1], eParts[2]);
                    tasks.add(e);
                    System.out.println("Added: " + e);
                    break;

                case "mark":
                    int markIndex = Integer.parseInt(c.getArgs()) - 1;
                    tasks.get(markIndex).markDone();
                    System.out.println("Nice! I've marked this task as done:\n  " + tasks.get(markIndex));
                    break;

                case "unmark":
                    int unmarkIndex = Integer.parseInt(c.getArgs()) - 1;
                    tasks.get(unmarkIndex).markNotDone();
                    System.out.println("OK, I've marked this task as not done yet:\n  " + tasks.get(unmarkIndex));
                    break;

                case "delete":
                    int deleteIndex = Integer.parseInt(c.getArgs()) - 1;
                    Task removed = tasks.delete(deleteIndex);
                    System.out.println("Noted. I've removed this task:\n  " + removed +
                            "\nNow you have " + tasks.size() + " tasks in the list.");
                case "find":
                    String keyword = c.getArgs();
                    ArrayList<Task> results = tasks.findTasks(keyword);
                    if (results.isEmpty()) {
                        ui.showError("No matching tasks found!");
                    } else {
                        ui.showLine();
                        System.out.println("Here are the matching tasks in your list:");
                        for (int i = 0; i < results.size(); i++) {
                            System.out.println((i + 1) + "." + results.get(i));
                        }
                        ui.showLine();
                    }
                    break;
                default:
                    ui.showError("Unknown command: " + c.getCommand());
                }
                try {
                    storage.save(tasks.getTasks());
                } catch (Exception e) {
                    ui.showError("Error saving tasks!");
                }
            }
        }

        public static void main(String[] args) {
            new Ben("./data/Ben.txt").run();
        }
}
