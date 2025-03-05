
public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Paul", "24.06.2004", "12342KF");
        System.out.println(s1.toString());
        Project p1 = new Project("OfflineMessenger", Project.projectType.PRACTICAL, Project.projectCategory.B);
        System.out.println(p1.toString());
    }
}