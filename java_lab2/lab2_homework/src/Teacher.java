/**
 * The Teacher class represents a teacher, extending the Person class,
 * with an associated list of projects.
 */
public class Teacher extends Person {
    private Project[] projectsList;

    /**
     * Constructs a Teacher with the specified name and date of birth,
     * initializing the projects list as null.
     *
     * @param name the name of the teacher
     * @param dob the date of birth of the teacher
     */
    Teacher(String name, String dob) {
        super(name, dob);
        setProjects(null);
    }

    /**
     * Constructs a Teacher with the specified name, date of birth, and projects.
     *
     * @param name the name of the teacher
     * @param dob the date of birth of the teacher
     * @param projects a variable number of Project objects associated with the teacher
     */
    Teacher(String name, String dob, Project ... projects) {
        super(name, dob);
        setProjects(projects);
    }

    /**
     * Sets the projects list for the teacher and assigns this teacher to each project.
     *
     * @param projects a variable number of Project objects to set for the teacher
     */
    public void setProjects(Project ... projects) {
        this.projectsList = projects;
        for (Project project : projectsList) {
            project.setTeacher(this);
        }
    }

    /**
     * Retrieves the list of projects associated with the teacher.
     *
     * @return an array of Project objects
     */
    public Project[] getProjects() {
        return this.projectsList;
    }

    /**
     * Returns a string representation of the teacher, including personal details
     * and a list of projects.
     *
     * @return a formatted string with the teacher's details and projects list
     */
    @Override
    public String toString() {
        StringBuilder teacherData = new StringBuilder();
        teacherData.append("Name: " + getName() + "\n");
        teacherData.append("Dob: " + getDob() + "\n");
        StringBuilder projects = new StringBuilder();
        for (Project project : projectsList) {
            projects.append("•) " + project.getProjectName()).append("\n");
        }
        teacherData.append("Projects list: \n" + projects + "\n");
        return teacherData.toString();
    }

    /**
     * Determines whether the specified object is equal to this teacher.
     * Two teachers are considered equal if they have the same name.
     *
     * @param obj the object to compare
     * @return true if the specified object is equal to this teacher, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Teacher)) {
            return false;
        }
        Teacher other = (Teacher) obj;
        return (this.getName().equals(other.getName()));
    }
}
