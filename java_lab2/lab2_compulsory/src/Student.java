public class Student {
    private String name;
    private String dob;
    private String regNo;

    //constructor
    Student(String name, String dob, String regNo) {
        setName(name);
        setDob(dob);
        setRegNo(regNo);
    }

    //settere
    public void setName(String name){
        this.name = name;
    }

    public void setDob(String dob){
        this.dob = dob;
    }

    public void setRegNo(String regNo){
        this.regNo = regNo;
    }

    //gettere
    public String getName(){
        return this.name;
    }

    public String getDob(){
        return this.dob;
    }

    public String getRegNo(){
        return this.regNo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: " + getName() + "\n");
        sb.append("Dob: " + getDob() + "\n");
        sb.append("RegNo: " + getRegNo() + "\n");
        return sb.toString();
    }
}
