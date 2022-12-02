import java.util.*;

public class Program {
    private static final Program program = new Program();
    private final Input input = Input.getInstance();
    private final DataOperation dataOperation = DataOperation.getInstance();

    private String selectedAction;
    
    private final Map<String, String> selectOptions;
    private final Map<String, String> repeatOptions;

    private Program() {
        selectOptions = new HashMap<>() {{
            put("new", " for adding person");
            put("delete", " for removing person");
            put("search", " for searching person by identification number");
        }};

        repeatOptions = new HashMap<>() {{
            put("continue", " to continue in program");
            put("exit", " for exit");
        }};
    }
    public static Program getInstance() {
        return Program.program;
    }

    public void run() {
        while (true) {
            selectAction();
            performAction();
            repeatAction();
        }
    }

    private void selectAction() {
        selectedAction = input.getInput(getMessage(selectOptions), getOptions(selectOptions));
    }
    
    private String getMessage(Map<String, String> options) {
        StringBuilder message = new StringBuilder("");
        for (var option : options.entrySet()) 
            message.append("\"" + option.getKey() + "\"" + option.getValue() + "\n");
        
        return message.toString();
    }
    
    private List<String> getOptions(Map<String, String> options) {
        return new ArrayList<>(options.keySet());
    }

    private void performAction()  {
        switch (selectedAction) {
            case "new" -> dataOperation.addPerson();
            case "delete" -> dataOperation.removePerson();
            case "search" -> dataOperation.getPerson();
        };
    }
    
    private void repeatAction() {
        if (input.getInput(getMessage(repeatOptions), getOptions(repeatOptions)).equals(repeatOptions.get("exit")))
            System.exit(0);
    }
}