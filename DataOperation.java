import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class DataOperation {
    private static final DataOperation dataOperation = new DataOperation();
    private final Input input = Input.getInstance();
    private final Database database = Database.getInstance();
    private DataOperation() {}
    public static DataOperation getInstance() {
        return dataOperation;
    }

    public void addPerson() {
        String firstName = input.getInput("Enter first name.");
        String lastName = input.getInput("Enter last name.");
        String identificationNumber = getIdentificationNumber();

        database.addPerson(new Person(identificationNumber, new PersonName(firstName, lastName)));
    }

    public void removePerson() {
        database.removePerson(getIdentificationNumber());
        input.print("Done");
    }

    public void getPerson() {
        Person person = database.getPerson(getIdentificationNumber());
        if (person == null)
            return;

        input.print("First name : " + person.getFirstName());
        input.print("Last name: " + person.getLastName());
        input.print("Identification number: " + person.identificationNumber);
        input.print("Age: " + getAgeFromIdentificationNumber(person.identificationNumber) + "\n");
    }

    private String getIdentificationNumber() {
        String identificationNumber = input.getInput("Enter identification number", this::identificationNumberIsValid);
        return fixIdentificationNumberFormat(identificationNumber);
    }

    private String fixIdentificationNumberFormat(String identificationNumber) {
        return identificationNumber.contains("/") 
                ? identificationNumber
                : identificationNumber.substring(0, 6) + "/" + identificationNumber.substring(6);
    }
    
    private int getAgeFromIdentificationNumber(String identificationNumber) {
        LocalDate birthDate = LocalDate.parse(identificationNumber.substring(0, 6), DateTimeFormatter.ofPattern("yyMMdd"));
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
    
    public boolean identificationNumberIsValid(String identificationNumber) {
       return Pattern.matches("^([0-9][0-9])((([05])[1-9])|(([16])[012]))([0-2][1-9]|3[0-1])(/?)([0-9]{4})$", identificationNumber);
    }
}