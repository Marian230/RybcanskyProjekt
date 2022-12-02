import java.util.HashMap;
import java.util.Map;

public class Database {
    private static final Database database = new Database();
    private final Map<String, PersonName> data = new HashMap<>(); // String (key) is identificationNumber 

    private Database() {}
    public static Database getInstance() {
        return Database.database;
    }

    public void addPerson(Person person)  {
        try {
            if (data.putIfAbsent(person.identificationNumber, person.personName) != null)
                throw new Exception("Already existing person.");
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }

    public void removePerson(String identificationNumber) {
        try {
            if (data.remove(identificationNumber) == null)
                throw new Exception("Person does not exist.");
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }

    public Person getPerson(String identificationNumber) {
        PersonName name = null;
        try {
            if ((name = data.get(identificationNumber)) == null)
                throw new Exception("Person does not exist.");
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        
        return name == null 
                ? null 
                : new Person(identificationNumber, name);
    }
}