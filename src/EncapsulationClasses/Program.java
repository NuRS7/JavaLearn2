package EncapsulationClasses;

public class Program {
    public static void main(String[] args) {
        Student student = new Student("Nursultan", 90);
        System.out.println(student.getGrade());
        System.out.println(student.getName());
        System.out.println(student.isPassed());
        Student student2 = new Student("Sultan", 50);
        System.out.println(student2.isPassed());
    }
}

class Student {
    private String name;
    private double grade;
    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }
    public double getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(double grade) {
        if (grade >= 0 && grade <= 100) {
            this.grade = grade;
        } else {
            System.out.println("Недопустимая оценка: " + grade);
        }
    }


    public boolean isPassed() {
        return grade >= 60;
    }

}