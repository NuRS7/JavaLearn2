package AbstractClasses;

public class Program {
    public static void main(String[] args) {
        Manager manager = new Manager("Nurs", true, 2000.0);
        manager.work();
        System.out.println(manager.getInfo());
        System.out.println(manager.getSalary());
        Student student = new Student("Nurss", 20);
        student.study();
    }
}


interface PersonInterfaces {
    void work();
}
abstract class Person {
    String name;
    int age;
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    abstract void study();
}

abstract class Emplpyee {
    String name;
    Boolean isYoung;
    double salary;
    Emplpyee(String name, Boolean isYoung, double salary) {
        this.name = name;
        this.isYoung = isYoung;
        this.salary = salary;
    }

    abstract double getSalary();
    String getInfo() {
        return "Сотрудник " + name + " с зарплатой " + salary + (isYoung ? " (молодой)" : " (опытный)");
    }
}
class Manager extends Emplpyee implements PersonInterfaces {
    Manager(String name, Boolean isYoung, double salary) {
        super(name, isYoung, salary);
    }

    @Override
    double getSalary() {
        return super.salary;
    }

    @Override
    public void work() {
        System.out.println("Workk");
    }
}
class Student extends Person {
    Student(String name, int age) {
        super(name, age);
    }

    @Override
    void study() {
        System.out.println("Student is studying");
    }

}

class Teacher extends Person implements PersonInterfaces {
    Teacher(String name, int age) {
        super(name, age);
    }
    @Override
    public void study() {
        System.out.println("Teacher is studying");
    }
    @Override
    public void work() {
        System.out.println("Teacher is working");
    }
}

