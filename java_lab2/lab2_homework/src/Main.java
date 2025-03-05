/**
 * The Main class demonstrates the creation of projects, students, and teachers,
 * sets up the problem instance, and executes the solution to assign projects to students.
 */
public class Main {
    /**
     * The main method is the entry point of the application.
     * It creates instances of Project, Student, and Teacher, initializes a Problem instance,
     * and then uses a Solution instance to assign projects to students based on their preferences and grades.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Project p1 = new Project("OfflineMessenger", Project.projectType.PRACTICAL, Project.projectCategory.B);
        Project p2 = new Project("Monitor", Project.projectType.PRACTICAL, Project.projectCategory.A);
        Project p3 = new Project("Compilator", Project.projectType.PRACTICAL, Project.projectCategory.A);
        Project p4 = new Project("Mersul Trenurilor", Project.projectType.PRACTICAL, Project.projectCategory.B);
        Project p5 = new Project("Criptografie Postcuantica", Project.projectType.THEORETICAL, Project.projectCategory.A);

        Student s1 = new Student("Pasnicu Paul-Stefan", "24.06.2004", "12342KF", 9.25, p5, p1, p4, p2);
        Student s2 = new Student("Buciog Ana", "09.04.2006", "1313IA", 9.75, p3, p2, p1, p4);
        Student s3 = new Student("Bozianu Denisa", "26.10.2003", "1102AAS", 10, p1, p3, p2, p4);
        Student s4 = new Student("Putinica Andreea", "07.07.2006", "1A932S", 8.95, p4, p1, p3, p2);

        Teacher t1 = new Teacher("Scutelnicu Andrei", "20.07.1995", p1, p2, p4);
        Teacher t2 = new Teacher("Captarencu Oana", "20.02.1979", p3);
        Teacher t3 = new Teacher("Tiplea FL", "04.10.1962", p5);

        Student[] students = {s1, s2, s3, s4};
        Teacher[] teachers = {t1, t2, t3};
        Problem problem1 = new Problem(students, teachers);
        Solution solution1 = new Solution(problem1);
        solution1.solveProblem();
    }
}
