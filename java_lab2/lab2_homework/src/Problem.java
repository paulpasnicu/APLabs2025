/**
 * The Problem class encapsulates a collection of students and teachers,
 * and provides methods to retrieve related persons and projects.
 */
public class Problem {
    private Student[] students;
    private Teacher[] teachers;

    /**
     * Constructs a Problem instance with the specified arrays of students and teachers.
     *
     * @param students an array of Student objects
     * @param teachers an array of Teacher objects
     */
    Problem(Student[] students, Teacher[] teachers) {
        setStudents(students);
        setTeachers(teachers);
    }

    /**
     * Sets the students for the problem.
     *
     * @param students a variable number of Student objects
     */
    public void setStudents(Student ... students) {
        this.students = students;
    }

    /**
     * Sets the teachers for the problem.
     *
     * @param teachers a variable number of Teacher objects
     */
    public void setTeachers(Teacher ... teachers) {
        this.teachers = teachers;
    }

    /**
     * Retrieves the array of students.
     *
     * @return an array of Student objects
     */
    public Student[] getStudents() {
        return students;
    }

    /**
     * Retrieves the array of teachers.
     *
     * @return an array of Teacher objects
     */
    public Teacher[] getTeachers() {
        return teachers;
    }

    /**
     * Retrieves all persons (both students and teachers) as a combined array.
     *
     * @return an array of Person objects containing both students and teachers
     */
    public Person[] getPersons() {
        Person[] persons = new Person[students.length + teachers.length];
        int i = 0;
        for (Student student : students) {
            persons[i++] = student;
        }

        for (Teacher teacher : teachers) {
            persons[i++] = teacher;
        }
        return persons;
    }

    /**
     * Retrieves all projects from the teachers.
     *
     * @return an array of Project objects from the teachers
     */
    public Project[] getProjects() {
        Project[] projects = new Project[100];
        int j = 0;
        for (Teacher t : teachers) {
            for (Project p : t.getProjects()) {
                projects[j++] = p;
            }
        }
        return projects;
    }

    /**
     * Returns a string representation of the problem,
     * including each student's name and their project preferences.
     *
     * @return a formatted string with student names and their project preferences
     */
    @Override
    public String toString() {
        StringBuilder problemData = new StringBuilder();
        Student[] students = getStudents();
        for (Student student : students) {
            problemData.append(student.getName() + ": ");
            Project[] preferences = student.getPreferences();
            for (Project project : preferences) {
                problemData.append(project.getProjectName() + "; ");
            }
            problemData.append("\n");
        }
        return problemData.toString();
    }
}
