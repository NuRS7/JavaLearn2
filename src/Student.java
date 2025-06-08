public class Student extends Person implements Actions {

    String university;
    int yearOfStudy;

    Student(String name, int age, String university, int yearOfStudy) {
        super(name,age);
        this.university = university;
        this.yearOfStudy = yearOfStudy;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    @Override
    public void study() {
        System.out.println(name+" is studying");

    }

    @Override
    public void sleep() {
        System.out.println(name+" is sleeping");
    }

    @Override
    public void getUp() {
        System.out.println(name+" is getting up");
    }
}
