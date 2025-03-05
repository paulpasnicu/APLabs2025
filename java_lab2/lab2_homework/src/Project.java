/**
 * The Project class represents a project with its details such as name, type, category, and associated teacher.
 */
public class Project {
    /**
     * Enumeration for the type of project.
     */
    enum projectType {
        THEORETICAL, PRACTICAL;
    }

    /**
     * Enumeration for the category of project.
     */
    enum projectCategory {
        A, B, C;
    }

    private String projectName;
    private projectType projectType;
    private projectCategory projectCategory;
    private Teacher teacher;

    /**
     * Constructs a Project with the specified name, type, category, and associated teacher.
     *
     * @param projectName the name of the project
     * @param projectType the type of the project
     * @param projectCategory the category of the project
     * @param teacher the teacher associated with the project
     */
    Project(String projectName, projectType projectType, projectCategory projectCategory, Teacher teacher) {
        setProjectName(projectName);
        setProjectType(projectType);
        setProjectCategory(projectCategory);
        setTeacher(teacher);
    }

    /**
     * Constructs a Project with the specified name, type, and category.
     *
     * @param projectName the name of the project
     * @param projectType the type of the project
     * @param projectCategory the category of the project
     */
    Project(String projectName, projectType projectType, projectCategory projectCategory) {
        setProjectName(projectName);
        setProjectType(projectType);
        setProjectCategory(projectCategory);
    }

    /**
     * Sets the teacher associated with the project.
     *
     * @param teacher the teacher to set
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     * Sets the project name.
     *
     * @param projectName the name to set for the project
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Sets the type of the project.
     *
     * @param projectType the type to set for the project
     */
    public void setProjectType(projectType projectType) {
        this.projectType = projectType;
    }

    /**
     * Sets the category of the project.
     *
     * @param projectCategory the category to set for the project
     */
    public void setProjectCategory(projectCategory projectCategory) {
        this.projectCategory = projectCategory;
    }

    /**
     * Retrieves the name of the project.
     *
     * @return the project name
     */
    public String getProjectName() {
        return this.projectName;
    }

    /**
     * Retrieves the type of the project.
     *
     * @return the project type
     */
    public projectType getProjectType() {
        return this.projectType;
    }

    /**
     * Retrieves the category of the project.
     *
     * @return the project category
     */
    public projectCategory getProjectCategory() {
        return this.projectCategory;
    }

    /**
     * Retrieves the teacher associated with the project.
     *
     * @return the teacher associated with the project
     */
    public Teacher getTeacher() {
        return this.teacher;
    }

    /**
     * Converts a project category to its corresponding maximum grade.
     *
     * @param category the project category to convert
     * @return the maximum grade corresponding to the project category
     */
    public int categoryToGrade(projectCategory category) {
        switch (category) {
            case A:
                return 10;
            case B:
                return 8;
            case C:
                return 6;
            default:
                return 0;
        }
    }

    /**
     * Returns a string representation of the project, including its name, type, category, and maximum grade.
     *
     * @return a formatted string with the project's details
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Project name: " + getProjectName() + "\n");
        sb.append("Project type: " + getProjectType() + "\n");
        sb.append("Project category: " + getProjectCategory() + "\n");
        sb.append("Maximum grade: " + categoryToGrade(getProjectCategory()) + "\n");
        return sb.toString();
    }

    /**
     * Determines whether the specified object is equal to this project.
     * Two projects are considered equal if their project names are equal.
     *
     * @param obj the object to compare
     * @return true if the specified object is equal to this project, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Project)) {
            return false;
        }
        Project other = (Project) obj;
        return (this.getProjectName().equals(other.getProjectName()));
    }
}
