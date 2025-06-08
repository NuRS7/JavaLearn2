package CollectionsEx.Ex1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Test1", 90.0));
        students.add(new Student("Test2", 88.0));
        students.add(new Student("Test3", 50.0));
        Student student1 = new Student("Test4", 60.0);

        System.out.println("List of students: ");
        printStudents(students);
        System.out.println("Top student");
        topStudent(students);
        System.out.println("Remove student");
        removeByName(students, "Test1");
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("Filter reverse student");
        filterReverse(students);
        for (Student student : students) {
            System.out.println(student);
        }

        System.out.println("Filter by grade");
        filterByGrade(students);
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("=============");

    }    public static void printStudents(ArrayList<Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public static void topStudent(ArrayList<Student> students) {
        if (students.isEmpty()) {
            throw new IllegalArgumentException("Пусто");
        } else System.out.println(Collections.max(students));
    }

    public static void filterReverse(ArrayList<Student> students) {
        if (students.isEmpty()) {
            throw new IllegalArgumentException("Пусто");
        } else {

            Collections.sort(students);
            Collections.reverse(students);
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    public static void removeByName(ArrayList<Student> students, String name) {
        if (students.isEmpty()) {
            throw new IllegalArgumentException("Unkown student");
        } else {
            students.removeIf(student -> student.getName().equals(name));
        }
    }

    public static List<Student> filterByGrade(ArrayList<Student> students) {
        List<Student> highGrade = new ArrayList<>();
        for (Student student : students) {
            if (student.getGrade() >= 85.0) {
                highGrade.add(student);
            }

        }

        return highGrade;
    }
}

class Student implements Comparable<Student> {
    @Override
    public int compareTo(Student o) {
        return Double.compare(this.grade, o.grade);
    }

    private String name;
    private double grade;



    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student name: " + name + " grade: " + grade;
    }

    public String sorting() {
        if (this.grade >= 85.0) {
            return "Student name: " + this.name + " grade: " + this.grade;
        } else {
            return "Нет такого";
        }
    }


}