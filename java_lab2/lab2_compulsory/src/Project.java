public class Project {
    enum projectType{
        THEORETICAL, PRACTICAL;
    }

    enum projectCategory{
        A, B, C;
    }
    private String projectName;
    private projectType projectType;
    private projectCategory projectCategory;

    //constructor
    Project(String projectName, projectType projectType, projectCategory projectCategory){
        setProjectName(projectName);
        setProjectType(projectType);
        setProjectCategory(projectCategory);
    }

    //settere
    public void setProjectName(String projectName){
        this.projectName = projectName;
    }

    public void setProjectType(projectType projectType){
        this.projectType = projectType;
    }

    public void setProjectCategory(projectCategory projectCategory){
        this.projectCategory = projectCategory;
    }

    //gettere
    public String getProjectName(){
        return this.projectName;
    }

    public projectType getProjectType(){
        return this.projectType;
    }

    public projectCategory getProjectCategory(){
        return this.projectCategory;
    }
    public int categoryToGrade(projectCategory category){
        switch(category){
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
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Project name: " + getProjectName() + "\n");
        sb.append("Project type: " + getProjectType() + "\n");
        sb.append("Project category: " + getProjectCategory() + "\n");
        sb.append("Maximum grade: " + categoryToGrade(getProjectCategory()) +"\n");
        return sb.toString();
    }
}
