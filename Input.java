import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class Input {
    private static final Input input = new Input();
    private final Scanner scanner = new Scanner(System.in);
    
    private final String cancelCommand = "CANCEL";

    // Messages
    private final String emptyInputMessage;
    private final String badFormat;

    private Input() {
        emptyInputMessage = "This field cannot be empty. Repeat.";
        badFormat = "Bad format.";
    }
    public static Input getInstance() {
        return input;
    }

    public String getInput(String message) {
        System.out.println(message);
        String input;
        while ((input = scanner.nextLine()).equals(""))
            System.out.println(emptyInputMessage);

        return input;
    }

    public String getInput(String message, Function<String, Boolean> formatCheck) {
        String input;
        while (!formatCheck.apply(input = getInput(message)))
            System.out.println(badFormat);

        return input;
    }
    
    public String getInput(String message, List<String> possibleOptions) { // returns option selected by user of the program
        return getInput(message, possibleOptions::contains);
    }
    
    public void print(String message) {
        System.out.println(message);
    }
}