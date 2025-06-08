public class Program
{
    public static void main(String[] args) {
        Person person = new Person("Nurs", 13);
        Person person1 = new Student("nurs", 12, "AUES", 2025);
        System.out.println(person1.getAge());
    }

}

class Person {


    String name;
    int age;
    Person(String name, int age) {

        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}