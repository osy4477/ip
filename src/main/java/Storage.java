//AI was used in creating this class
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private final File file;

    private static final String TASK_TODO = "T";
    private static final String TASK_DEADLINE = "D";
    private static final String TASK_EVENT = "E";
    private static final String DONE_MARK = "1";
    private static final String DELIMITER = " \\| ";
    private static final int IDX_TYPE = 0;
    private static final int IDX_DONE = 1;
    private static final int IDX_DESC = 2;
    private static final int IDX_DEADLINE_BY = 3;
    private static final int IDX_EVENT_FROM = 3;
    private static final int IDX_EVENT_TO = 4;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    // Load tasks from disk
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            if (!file.exists()) {
                // Create folder if missing
                file.getParentFile().mkdirs();
                file.createNewFile();
                return tasks;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                String type = parts[IDX_TYPE];
                boolean isDone = parts[IDX_DONE].equals(DONE_MARK);
                Task t;

                switch (type) {
                case TASK_TODO:
                    t = new Todo(parts[IDX_DESC]);
                    break;
                case TASK_DEADLINE:
                    t = new Deadline(parts[IDX_DESC], parts[IDX_DEADLINE_BY]);
                    break;
                case TASK_EVENT:
                    t = new Event(parts[IDX_DESC], parts[IDX_EVENT_FROM], parts[IDX_EVENT_TO]);
                    break;
                default:
                    continue; // skip invalid lines
                }

                if (isDone) {
                    t.markDone();
                }
                tasks.add(t);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }

        return tasks;
    }

    // Save tasks to disk
    public void save(ArrayList<Task> tasks) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Task t : tasks) {
                bw.write(formatTask(t));
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    private String formatTask(Task t) {
        String type;
        if (t instanceof Todo) {
            type = "T";
            return String.format("%s | %d | %s", type, t.isDone ? 1 : 0, t.description);
        } else if (t instanceof Deadline) {
            type = "D";
            return String.format("%s | %d | %s | %s", type, t.isDone ? 1 : 0,
                    t.description, ((Deadline) t).by);
        } else if (t instanceof Event) {
            type = "E";
            return String.format("%s | %d | %s | %s | %s", type, t.isDone ? 1 : 0,
                    t.description, ((Event) t).from, ((Event) t).to);
        }
        return "";
    }
}
