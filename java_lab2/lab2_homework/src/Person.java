/**
 * The Person class represents a person with a name and date of birth.
 */
public class Person {
    private String name;
    private String dob;

    /**
     * Default constructor for Person.
     */
    Person() { }

    /**
     * Constructs a Person with the specified name and date of birth.
     *
     * @param name the name of the person
     * @param dob the date of birth of the person
     */
    Person(String name, String dob) {
        setName(name);
        setDob(dob);
    }

    /**
     * Sets the name of the person.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the date of birth of the person.
     *
     * @param dob the date of birth to set
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * Retrieves the name of the person.
     *
     * @return the name of the person
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the date of birth of the person.
     *
     * @return the date of birth of the person
     */
    public String getDob() {
        return this.dob;
    }

    /**
     * Returns a string representation of the person, including name and date of birth.
     *
     * @return a formatted string with the person's details
     */
    @Override
    public String toString() {
        StringBuilder personData = new StringBuilder();
        personData.append("Name: " + getName() + "\n");
        personData.append("Dob: " + getDob() + "\n");
        return personData.toString();
    }

}
