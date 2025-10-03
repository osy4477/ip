//AI was used in creating this class
/**
 * Represents a task with a description and a deadline.
 * Example: "submit report by Monday".
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a Deadline task.
     *
     * @param description The task description
     * @param by The deadline date/time
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getTypeIcon() {
        return "D";
    }

    /**
     * Returns the task in a savable string format for Storage.
     *
     * @return The file format string of the deadline task
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
