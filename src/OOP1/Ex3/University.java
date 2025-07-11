package OOP1.Ex3;

public class University {
    public static void main(String[] args) {
        JavaCourse courses[] = {new JavaCourse(), new JavaCourse()};
        courses[0].courseName = "MegaCourse";
        for (JavaCourse course : courses) {
            course = new JavaCourse();
        }
        for (JavaCourse course : courses) {
            System.out.println(course.courseName);
        }
    }
}
