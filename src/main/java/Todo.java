//AI was used in creating this class
/**
 * Represents a simple task with only a description
 * and no date or time attached.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task.
     *
     * @param description The task description
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTypeIcon() {
        return "T";
    }
}
