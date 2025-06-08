package CollectionsEx.Ex1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Program {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Test1", 90.0));
        students.add(new Student("Test2", 88.0));
        students.add(new Student("Test3", 50.0));

        System.out.println("Все студенты: ");
        for (Student student : students) {
            System.out.println(student);
        }

        System.out.println("Самый высший балл: ");
        System.out.println(Collections.max(students));

        System.out.println("Сортировка");
        Collections.sort(students);
        Collections.reverse(students);
        for (Student student : students) {
            System.out.println(student);
        }


        System.out.println("Фильтровать студентов");
        
    }
}

class Student implements Comparable<Student> {
    @Override
    public int compareTo(Student o) {
        return Double.compare(this.grade, o.grade);
    }
    private String name;
    private double grade;
    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }
    @Override
    public String toString() {
        return "Student name: " + name + " grade: " + grade;
    }
}