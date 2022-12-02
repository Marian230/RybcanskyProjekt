public class Person {
    public String identificationNumber;
    public PersonName personName;
    
    public Person(String identificationNumber, PersonName personName) {
        this.identificationNumber = identificationNumber;
        this.personName = personName;
    }
    
    public String getFirstName() {
        return personName.firstName;
    }
    
    public String getLastName() {
        return personName.lastName;
    }
}