package StreamAPI.Ex11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class Order {
    String item;
    double price;
    public Order(String item, double price) {
        this.item = item;
        this.price = price;
    }
    @Override
    public String toString() {
        return "order " + item + "price is " + price;
    }

    public String getItem() {
        return item;
    }

    public double getPrice() {
        return price;
    }
    
    public double getAllSum(List<Order> orders) {
        return orders.stream().map(Order::getPrice).reduce(0.0, Double::sum);
    }
    
}

class User {
    String name;
    int age;


    public User() {
        this.name = getName();
        this.age = getAge();
    }
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String toString() {
        return "Name: " + name + ", Age: " + age;
    }
    public User getMaxAge(List<User> users) {
        return users.stream().reduce((x, y) -> x.getAge() > y.getAge() ? x : y).get();
    }

}
public class Program {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 10);
        System.out.println(numbers.stream().reduce(Integer::sum).get());

        List<Integer> numbers2 = Arrays.asList(1, 3, 5, 7);
        System.out.println(numbers2.stream().reduce((x, y) -> x * y).get());

        List<Integer> numbers3 = Arrays.asList(15, 7, 22, 3, 11);
        System.out.println(numbers3.stream().reduce(Integer::max).get());

        List<String> words = Arrays.asList("Java", "is", "awesome");
        System.out.println(words.stream().reduce((x,y)-> x +" "+y).get());

        List<String> words2 = Arrays.asList("cat", "elephant", "dog", "tiger");
        System.out.println(words2.stream().reduce((x,y)-> x.length()>y.length()?x:y).get());

        List<User> user = new ArrayList<>();
        user.add(new User("John", 18));
        user.add(new User("Jane", 19));
        User usr = new User();
        System.out.println(usr.getMaxAge(user));
        
        
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("Snickers", 18));
        orders.add(new Order("KitKat", 20));
        orders.add(new Order("Albeni", 19));
        Order usrOrder = new Order("Snickers", 19);
        System.out.println(usrOrder.getAllSum(orders));
    }
}
