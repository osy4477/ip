public class Parser {
    public static Command parse(String input) {
        String[] words = input.split(" ", 2);
        String command = words[0];
        String args = (words.length > 1) ? words[1] : "";
        return new Command(command, args);
    }
}