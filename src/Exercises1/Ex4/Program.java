package Exercises1.Ex4;

import java.util.*;
import java.util.stream.Collectors;


class Employee {
    private String name;
    private int age;
    private double salary;

    public Employee(String name, int age, double salary) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be null");
        }
        if (age < 0 || age > 100) {
            throw new IllegalArgumentException("age must be between 0 and 100");
        }
        if (salary <= 0.0) {
            throw new IllegalArgumentException("salary cannot be negative");
        }
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary <= 0.0) {
            throw new IllegalArgumentException("salary cannot be negative");
        }
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0 || age > 100) {
            throw new IllegalArgumentException("age must be between 0 and 100");
        }
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be null");
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
public class Program {

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Иван", 25, 50000),
                new Employee("Мария", 30, 60000),
                new Employee("Алексей", 22, 45000),
                new Employee("Ольга", 35, 70000),
                new Employee("Дмитрий", 28, 55000)
        );


                // 1. Фильтрация (старше 25 и зарплата > 50_000)
        List<Employee> filteredEmployees = employees.stream().filter(employee -> employee.getAge() > 25 && employee.getSalary() > 50000.0).toList();

                // 2. Сортировка (по возрасту ↑ и зарплате ↓)
                List<Employee> sortedByAge = employees.stream().sorted(Comparator.comparingInt(Employee::getAge)).collect(Collectors.toList());
        List<Employee> sortedBySalaryDesc = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).collect(Collectors.toList());
                // 3. Преобразование (имена и средняя зарплата)
        List<String> names = employees.stream().map(Employee::getName).collect(Collectors.toList());
        double averageSalary = employees.stream().mapToDouble(Employee::getSalary).average().orElse(0.0);

                // 4. Поиск (максимальная зарплата и проверка >100k)
                Optional<Employee> maxSalaryEmployee = employees.stream().max(Comparator.comparingDouble(Employee::getSalary));
        boolean hasRichEmployee = employees.stream().anyMatch(employee -> employee.getSalary() > 100000.0);

                // Вывод результатов
                System.out.println("Фильтрация: " + filteredEmployees);
                System.out.println("Сортировка по возрасту: " + sortedByAge);
                System.out.println("Сортировка по зарплате: " + sortedBySalaryDesc);
                System.out.println("Имена: " + names);
                System.out.println("Средняя зарплата: " + averageSalary);
                System.out.println("Сотрудник с макс. зарплатой: " + maxSalaryEmployee.orElse(null));
                System.out.println("Есть ли богатые (>100k)? " + hasRichEmployee);


    }
}
