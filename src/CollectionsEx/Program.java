package CollectionsEx;

import java.util.ArrayList;
import java.util.Collections;

public class Program {
    public static void main(String[] args) {
        // Создаем ArrayList для студентов
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Nursultan", 90.0));
        students.add(new Student("Inkar", 80.0));
        students.add(new Student("Aigerim", 95.0));

        // Выводим всех студентов
        System.out.println("Все студенты:");
        for (Student student : students) {
            System.out.println(student);
        }

        // Сортируем студентов по оценке
        Collections.sort(students);
        System.out.println("\nСтуденты, отсортированные по оценке:");
        for (Student student : students) {
            System.out.println(student);
        }

        // Находим студента с наивысшей оценкой
        Student topStudent = Collections.max(students);
        System.out.println("\nЛучший студент: " + topStudent);
    }
}

class Student implements Comparable<Student> {
    String name;
    double grade;

    // Конструктор
    Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }

    // Переопределяем toString для читаемого вывода
    @Override
    public String toString() {
        return "Student{name='" + name + "', grade=" + grade + "}";
    }

    // Переопределяем equals для сравнения по имени и оценке
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Double.compare(student.grade, grade) == 0 && name.equals(student.name);
    }

    // Переопределяем hashCode
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + Double.hashCode(grade);
        return result;
    }

    // Реализуем Comparable для сортировки по оценке
    @Override
    public int compareTo(Student other) {
        return Double.compare(this.grade, other.grade); // Сортировка по возрастанию оценки
    }
}