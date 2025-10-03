/**
 * Represents a user command that can be executed
 * on the task list, with optional interaction with
 * the UI and Storage.
 */
public class Command {
    String command;
    String args;

    public Command(String command, String args) {
        this.command = command;
        this.args = args;
    }

    public String getCommand() { return command; }
    public String getArgs() { return args; }
}