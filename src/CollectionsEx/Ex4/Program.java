package CollectionsEx.Ex4;

import java.util.ArrayList;

public class Program {

    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee("Nursultan", "dev", 20.0));
        employees.add(new Employee("Test2", "manager", 40.0));
        employees.add(new Employee("Test", "tester", 30.0));
        printAllEmployees(employees);
        addEmployee(employees, "Test3", "dev", 20.0);
        System.out.println("==========");
        findEmployeesByPosition(employees, "dev");
        System.out.println("====");
        System.out.println(getTotalSalary(employees));
        System.out.println("=======");
        removeEmployeeById(employees, 3);
        printAllEmployees(employees);

    }

    public static void addEmployee(ArrayList<Employee> e, String name, String position, double salary) {
        boolean exists = false;
        for (Employee e1 : e) {
            if (e1.getName().equals(name)) {
                exists = true;
                break;
            }
        }
        if (exists) {
            System.out.println(name + " уже сущетсвует");
        } else {
            try {
                Employee employee = new Employee(name, position, salary);
                e.add(employee);
                System.out.println(employee + " добавлен");
            } catch (IllegalArgumentException exception) {
                System.out.println("Exeption: " + exception.getMessage());
            }
        }
    }

    public static void removeEmployeeById(ArrayList<Employee> employees, int id) {
        boolean removed = employees.removeIf(e -> e.getId() == id);
        if (!removed) {
            System.out.println("Нет такого юзера с ID " + id);
        } else {
            System.out.println("Сотрудник с ID " + id + " удален");
        }
    }


    public static void findEmployeesByPosition(ArrayList<Employee> e, String position) {
        if (position == null || position.isEmpty()) {
            throw new IllegalArgumentException("Пустой");
        } else {
            for (Employee employee : e) {
                if (employee.getPositions().contains(position)) {
                    System.out.println(employee);
                }
            }
        }
    }

    public static void printAllEmployees(ArrayList<Employee> e) {
        if (e.isEmpty()) {
            System.out.println("Список пуст");
            return;
        }
        for (Employee employee : e) {
            System.out.println(employee);
        }
    }


    public static double getTotalSalary(ArrayList<Employee> e) {

        double total = 0.0;
        for (Employee employee : e) {
            if (employee.getSalary() < 0.0) {
                throw new IllegalArgumentException("Не может быть ниже 0");
            } else {
                total = total + employee.getSalary();

            }
        }
        return total;
    }
}
class Employee implements Comparable<Employee> {
    private static int counter = 1;
    private final int id;
    private String name;
    private String positions;
    private double salary;

    public Employee(String name, String positions, double salary) {
        if ( name == null || positions == null || salary < 0) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        this.id = counter++;
        this.name = name;
        this.positions = positions;
        this.salary = salary;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Ошибка");
        }
        this.name = name;
    }

    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        if (positions == null || positions.isEmpty()) {
            throw new IllegalArgumentException("Error");
        }
        this.positions = positions;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 0.0) {
            throw new IllegalArgumentException("Error");
        }
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ID: " + id + " name is " + name + " positions is " + positions + " salary are " + salary;
    }

    @Override
    public int compareTo(Employee o) {
        return Double.compare(this.salary, o.salary);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        if (id != employee.id) return false;
        return salary == employee.salary && name.equals(employee.name) &&  positions.equals(employee.positions);
    }
}