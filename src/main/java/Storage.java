//AI was used in creating this class
import java.io.*;
import java.util.ArrayList;

public class Storage {
    private final File file;

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
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                Task t;

                switch (type) {
                case "T":
                    t = new Todo(parts[2]);
                    break;
                case "D":
                    t = new Deadline(parts[2], parts[3]);
                    break;
                case "E":
                    t = new Event(parts[2], parts[3], parts[4]);
                    break;
                default:
                    continue; // skip invalid lines
                }

                if (isDone) t.markDone();
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
