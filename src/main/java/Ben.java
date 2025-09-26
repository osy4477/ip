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
