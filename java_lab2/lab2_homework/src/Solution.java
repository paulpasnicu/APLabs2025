/**
 * The Solution class encapsulates the logic for solving the problem by assigning projects to students.
 */
public class Solution {
    private Problem problem;

    /**
     * Constructs a Solution with the specified Problem instance.
     *
     * @param problem the Problem instance to be solved
     */
    Solution(Problem problem) {
        setProblem(problem);
    }

    /**
     * Sets the Problem instance for this solution.
     *
     * @param problem the Problem instance to set
     */
    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    /**
     * Retrieves the Problem instance associated with this solution.
     *
     * @return the Problem instance
     */
    public Problem getProblem() {
        return problem;
    }

    /**
     * Searches for a project in the given list of projects.
     *
     * @param project the project to search for
     * @param projectsList the array of projects to search within
     * @return true if the project is found in the list, false otherwise
     */
    public boolean search(Project project, Project[] projectsList) {
        for (Project p : projectsList) {
            if (p != null && p.equals(project)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes the specified project from the given project list.
     *
     * @param project the project to be deleted
     * @param projectList the array of projects from which to delete the project
     * @return a new array of projects with the specified project removed
     */
    public Project[] delete(Project project, Project[] projectList) {
        int n = projectList.length;
        int i = 0;
        while (i < n) {
            if (projectList[i] != null && projectList[i].equals(project)) {
                break;
            }
            i++;
        }

        if (i == n) {
            return projectList;
        }

        Project[] newArray = new Project[n - 1];

        for (int j = 0; j < i; j++) {
            newArray[j] = projectList[j];
        }

        for (int j = i + 1; j < n; j++) {
            newArray[j - 1] = projectList[j];
        }

        return newArray;
    }

    /**
     * Solves the problem by assigning projects to students based on their preferences and grades.
     * <p>
     * The method sorts the students in descending order of their grades, then iterates through each student's
     * project preferences. If a preferred project is available, it is assigned to the student and removed from
     * the available projects list. Finally, the assignment results are printed.
     * </p>
     */
    public void solveProblem() {
        Problem problema = getProblem();
        Student[] students = problema.getStudents();
        Project[] projects = problema.getProjects();

        // Order students in descending order by grade
        for (int i = 0; i < students.length; i++) {
            for (int j = i+1; j < students.length; j++) {
                if (students[i].getGrade() < students[j].getGrade()) {
                    Student aux = students[i];
                    students[i] = students[j];
                    students[j] = aux;
                }
            }
        }

        // Assign projects to students based on their preferences
        for (Student s : students) {
            Project[] preferences = s.getPreferences();
            for (Project p : preferences) {
                if (search(p, projects)) {
                    s.setAssignedProject(p);
                    projects = delete(p, projects);
                    break;
                }
            }
        }

        // Print the assignment results
        for (Student s : students) {
            System.out.println("Student: " + s.getName() + "\nAssigned project: "
                    + s.getAssignedProject().getProjectName() + "\nTeacher: "
                    + s.getAssignedProject().getTeacher().getName());
            System.out.println();
        }
    }

}
