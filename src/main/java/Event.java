//AI was used in creating this class
/**
 * Represents a task with a description and a time range.
 * Example: "project meeting from 2-4pm".
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an Event task.
     *
     * @param description The task description
     * @param from The event start time
     * @param to The event end time
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTypeIcon() {
        return "E";
    }

    /**
     * Returns the task in a savable string format for Storage.
     *
     * @return The file format string of the event task
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
