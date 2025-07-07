package Exercises1.Ex5;


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class Order {
    private int id;
    private String productName;
    private String category;
    private double price;
    private LocalDate orderDate;
    private String city;
    public Order(int id, String productName, String category, double price, LocalDate orderDate, String city) {
        this.id = id;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.orderDate = orderDate;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", orderDate=" + orderDate +
                ", city='" + city + '\'' +
                '}';
    }
}
public class Program {
    public static void main(String[] args) {
        List<Order> orders = Arrays.asList(
                new Order(1, "Ноутбук", "Электроника", 75000, LocalDate.of(2023, 5, 10), "Москва"),
                new Order(2, "Смартфон", "Электроника", 50000, LocalDate.of(2023, 5, 15), "Санкт-Петербург"),
                new Order(3, "Книга", "Книги", 1500, LocalDate.of(2023, 5, 5), "Москва"),
                new Order(4, "Наушники", "Электроника", 8000, LocalDate.of(2023, 5, 20), "Казань"),
                new Order(5, "Футболка", "Одежда", 2500, LocalDate.of(2023, 5, 12), "Москва"),
                new Order(6, "Монитор", "Электроника", 35000, LocalDate.of(2023, 5, 18), "Екатеринбург"),
                new Order(7, "Кофеварка", "Бытовая техника", 12000, LocalDate.of(2023, 5, 3), "Москва")
        );

        System.out.println("Дороже 50000");
        List<Order> expensiveOrders = orders.stream().filter(order -> order.getPrice() > 50000).toList();
        System.out.println("Вывести заказы из Москвы.");
        List<Order> mascowOrders = orders.stream().filter(order -> order.getCity().equals("Москва")).toList();
        boolean hasOrderOfClothes = orders.stream().anyMatch(order -> order.getCategory().equals("Одежда"));
        List<Order> sortByDate = orders.stream().sorted(Comparator.comparing(Order::getOrderDate)).collect(Collectors.toList());

        Map<String, List<Order>> groupByCategory = orders.stream()
                .collect(Collectors.groupingBy(Order::getCategory));


        double total= orders.stream().mapToDouble(Order::getPrice).sum();

        double averagePriceOfOrderElectronics = orders.stream().filter(order -> order.getCategory().equals("Электроника")).mapToDouble(Order::getPrice).average().orElse(0.0);

        Optional<Order> highExpensiveOrder = orders.stream()
                .max(Comparator.comparing(Order::getPrice));


        List<Order> top3ExpensiveOrder = orders.stream().sorted(Comparator.comparing(Order::getPrice).reversed()).limit(3).toList();

        List<Order> filteredByTime = orders.stream().sorted(Comparator.comparing(Order::getOrderDate)).filter(order -> order.getOrderDate().isAfter(LocalDate.of(2023, 5, 15))).collect(Collectors.toList());

        System.out.println(expensiveOrders);
        System.out.println("Вывести заказы из Москвы.");
        System.out.println(mascowOrders);
        System.out.println("Проверка категории Одежда");
        System.out.println(hasOrderOfClothes);

        System.out.println("Сортировка по Date");
        System.out.println(sortByDate);

        System.out.println("Сгруппировать по категорям");
        System.out.println(groupByCategory);
        System.out.println("Общая сумма");
        System.out.println(total);


        System.out.println("Avarage value of Electronics category");
        System.out.println(averagePriceOfOrderElectronics);

        System.out.println("Самый дорогой заказ");
        System.out.println(highExpensiveOrder);


        System.out.println("Top 3");
        System.out.println(top3ExpensiveOrder);
        System.out.println("Половина мая");
        System.out.println(filteredByTime);

    }
}
