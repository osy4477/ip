//AI was used in creating this class
/**
 * Represents a generic task in the task list.
 * A task has a description and can be marked as done or not done.
 */
abstract public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks this task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markNotDone() {
        isDone = false;
    }

    /**
     * Returns the status icon ("X" if done, " " if not done).
     *
     * @return The status icon string.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the type icon representing the specific task type.
     *
     * @return The task type icon.
     */
    public abstract String getTypeIcon();

    @Override
    public String toString() {
        return "[" + getTypeIcon() + "][" + getStatusIcon() + "] " + description;
    }
}
