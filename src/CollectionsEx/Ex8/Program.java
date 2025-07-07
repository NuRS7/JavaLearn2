package CollectionsEx.Ex8;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Student {
    private String name;
    protected int studentId;
    Map<String, Integer> subjects;


    public Student(String name, int studentId, Map<String, Integer> subjects) {
        this.name = name;
        this.studentId = studentId;
        this.subjects = subjects;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Map<String, Integer> getSubjects() {
        return subjects;
    }

    public void updateSubject(String subjectName, int newGrade) {
        if (subjects.containsKey(subjectName)) {
            subjects.put(subjectName, newGrade);
        }
    }
    public void setSubjects(Map<String, Integer> subjects) {
        if (subjects == null) {
            throw new IllegalArgumentException();
        }
        this.subjects = subjects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", studentId=" + studentId +
                ", subjects=" + subjects +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, studentId, subjects);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) && studentId == student.studentId && Objects.equals(subjects, student.subjects);
    }

}
public class Program {
    public static void main(String[] args) {
        Map<String, Student> students = new HashMap<>();
        Map<String, Integer> subjects = new HashMap<>();
        subjects.put("Math", 70);
        subjects.put("Science", 80);
        subjects.put("Computer", 90);
        students.put("Math", new Student("Nurs", 70, subjects));
        students.put("Science", new Student("Science", 80, subjects));



        for (Student student : students.values()) {
            System.out.println(student);
        }


        System.out.println("==========================");

        for (Map.Entry<String,Student> entry : students.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println("Увеличь оценку по какому-нибудь предмету у одного студента.");
        Map<String, Integer> newPoint = new HashMap<>();
        newPoint.put("Math", 90);
        students.put("Math", new  Student("Nurs", 70, newPoint));
        for (Map.Entry<String,Student> entry : students.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

    }
}
