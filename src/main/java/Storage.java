//AI was used in creating this class
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles loading tasks from a save file and saving tasks
 * back into the file for persistence across sessions.
 */
public class Storage {
    private String filePath;

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
        this.filePath = filePath;
    }

    /**
     * Loads the saved tasks from the file.
     *
     * @return A list of tasks loaded from file
     * @throws IOException If the file cannot be read
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
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
                continue;
            }

            if (isDone) {
                t.markDone();
            }
            tasks.add(t);
        }
        br.close();
        return tasks;
    }

    /**
     * Saves the current list of tasks into the file.
     *
     * @param tasks The task list to save
     * @throws IOException If the file cannot be written
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
        for (Task t : tasks) {
            if (t instanceof Todo) {
                bw.write("T | " + (t.isDone ? "1" : "0") + " | " + t.description);
            } else if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                bw.write("D | " + (d.isDone ? "1" : "0") + " | " + d.description + " | " + d.by);
            } else if (t instanceof Event) {
                Event e = (Event) t;
                bw.write("E | " + (e.isDone ? "1" : "0") + " | " + e.description + " | " + e.from + " | " + e.to);
            }
            bw.newLine();
        }
        bw.close();
    }

}
