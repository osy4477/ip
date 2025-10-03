import java.util.ArrayList;

/**
 * Represents the list of tasks managed by the chatbot.
 * Provides methods to add, remove, and find tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list with existing tasks.
     *
     * @param tasks Existing list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param t Task to add.
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Deletes a task from the list at the given index.
     *
     * @param index Index of the task to delete (0-based).
     * @return The deleted task.
     */
    public Task delete(int index) {
        return tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds all tasks containing the given keyword in their description.
     *
     * @param keyword Keyword to search for.
     * @return A list of tasks matching the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task t : tasks) {
            if (t.description.contains(keyword)) {
                results.add(t);
            }
        }
        return results;
    }
}
