/**
 * The Student class represents a student, extending the Person class,
 * with additional attributes such as registration number, grade, project preferences,
 * and an assigned project.
 */
public class Student extends Person {
    private String regNo;
    private double grade;
    private Project[] preferences;
    private Project assignedProject = null;

    /**
     * Constructs a Student with the specified details.
     *
     * @param name the name of the student
     * @param dob the date of birth of the student
     * @param regNo the registration number of the student
     * @param grade the grade of the student
     * @param projects a variable number of preferred Project objects
     */
    Student(String name, String dob, String regNo, double grade, Project ... projects) {
        super(name, dob);
        setRegNo(regNo);
        setGrade(grade);
        setPreferences(projects);
    }

    /**
     * Sets the registration number for the student.
     *
     * @param regNo the registration number to set
     */
    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    /**
     * Sets the grade for the student.
     *
     * @param grade the grade to set
     */
    public void setGrade(double grade) {
        this.grade = grade;
    }

    /**
     * Sets the project preferences for the student.
     *
     * @param preferences an array of Project objects representing the student's preferences
     */
    public void setPreferences(Project[] preferences) {
        this.preferences = preferences;
    }

    /**
     * Sets the assigned project for the student.
     *
     * @param project the Project object to assign to the student
     */
    public void setAssignedProject(Project project) {
        assignedProject = project;
    }

    /**
     * Retrieves the registration number of the student.
     *
     * @return the registration number
     */
    public String getRegNo() {
        return this.regNo;
    }

    /**
     * Retrieves the grade of the student.
     *
     * @return the grade
     */
    public double getGrade() {
        return this.grade;
    }

    /**
     * Retrieves the array of project preferences of the student.
     *
     * @return an array of Project objects representing the student's project preferences
     */
    public Project[] getPreferences() {
        return this.preferences;
    }

    /**
     * Retrieves the assigned project of the student.
     *
     * @return the assigned Project object
     */
    public Project getAssignedProject() {
        return this.assignedProject;
    }

    /**
     * Returns a string representation of the student, including personal details,
     * grade, and project preferences.
     *
     * @return a formatted string with the student's details and preferences
     */
    @Override
    public String toString() {
        StringBuilder studentData = new StringBuilder();
        studentData.append("Name: " + getName() + "\n");
        studentData.append("Dob: " + getDob() + "\n");
        studentData.append("RegNo: " + getRegNo() + "\n");
        studentData.append("Grade: " + getGrade() + "\n");
        StringBuilder studentProjects = new StringBuilder();
        int i = 1;
        for (Project project : preferences) {
            studentProjects.append(i + ". " + project.getProjectName()).append("\n");
            i++;
        }
        studentData.append("Project preferences: \n" + studentProjects + "\n");
        return studentData.toString();
    }

    /**
     * Determines whether the specified object is equal to this student.
     * Two students are considered equal if they have the same name and registration number.
     *
     * @param obj the object to compare
     * @return true if the specified object is equal to this student, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Student)) {
            return false;
        }
        Student other = (Student) obj;
        return (this.getName().equals(other.getName()) && getRegNo().equals(other.getRegNo()));
    }
}
