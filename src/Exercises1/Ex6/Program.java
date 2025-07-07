package Exercises1.Ex6;


import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

class Sale {
    private int orderId;
    private String product;
    private String category;
    private double price;
    private int quantity;
    private LocalDate date;
    private String city;
    private int clientId;

    public Sale(int orderId, String product, String category, double price, int quantity, LocalDate date, String city, int clientId) {
        if (orderId < 0) {
            throw new IllegalArgumentException("Order id must be greater than zero");
        }
        if (product == null || product.isEmpty()) {
            throw new IllegalArgumentException("Product cannot be null or empty");
        }
        if (category == null || category.isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }
        if (price < 0.0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (city == null || city.isEmpty()) {
            throw new IllegalArgumentException("City cannot be null or empty");
        }
        if (clientId < 0) {
            throw new IllegalArgumentException("ClientId cannot be negative");
        }
        this.orderId = orderId;
        this.product = product;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.date = date;
        this.city = city;
        this.clientId = clientId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "orderId=" + orderId +
                ", product='" + product + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", date=" + date +
                ", city='" + city + '\'' +
                ", clientId=" + clientId +
                '}';
    }
}

public class Program {
    public static void main(String[] args) {
        List<Sale> sales = Arrays.asList(
                new Sale(1, "Ноутбук", "Электроника", 75000, 1, LocalDate.of(2023, 5, 10), "Москва", 101),
                new Sale(2, "Смартфон", "Электроника", 50000, 2, LocalDate.of(2023, 5, 15), "Санкт-Петербург", 102),
                new Sale(3, "Книга", "Книги", 1500, 3, LocalDate.of(2023, 6, 5), "Москва", 103),
                new Sale(4, "Наушники", "Электроника", 8000, 1, LocalDate.of(2023, 6, 20), "Казань", 104),
                new Sale(5, "Футболка", "Одежда", 2500, 2, LocalDate.of(2023, 7, 12), "Москва", 101),
                new Sale(6, "Монитор", "Электроника", 35000, 1, LocalDate.of(2023, 7, 18), "Екатеринбург", 105),
                new Sale(7, "Кофеварка", "Бытовая техника", 12000, 1, LocalDate.of(2023, 8, 3), "Москва", 102),
                new Sale(8, "Ноутбук", "Электроника", 80000, 1, LocalDate.of(2023, 8, 25), "Новосибирск", 106),
                new Sale(9, "Смартфон", "Электроника", 60000, 1, LocalDate.of(2023, 9, 14), "Москва", 103),
                new Sale(10, "Планшет", "Электроника", 45000, 2, LocalDate.of(2023, 9, 30), "Казань", 107)
        );
        System.out.println("Сгруппировать продажи по месяцам (Map<Month, List<Sale>>)");
        Map<Month, List<Sale>> groupBySaleMonth = sales.stream().collect(Collectors.groupingBy(sale -> sale.getDate().getMonth()));
        System.out.println(groupBySaleMonth);


        System.out.println("Найти месяц с максимальной выручкой");
//        Optional<Sale> maxMonth = sales.stream().max(Comparator.comparing(Sale::getPrice));
        Month topMonth = sales.stream()
                .collect(Collectors.groupingBy(
                        sale -> sale.getDate().getMonth(),
                        Collectors.summingDouble(sale -> sale.getPrice()* sale.getQuantity())
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        double avaragePriceOfMonth = sales.stream().mapToDouble(s -> s.getPrice()).average().orElse(0.0);

        System.out.println("Найти топ-3 клиентов по общей сумме покупок");
        List<Sale> top3Clients = sales.stream().sorted(Comparator.comparing(Sale::getPrice).reversed()).limit(3).collect(Collectors.toList());

        System.out.println("Определить, сколько клиентов сделали более 1 заказа");

        List<Sale> findMoreOrders = sales.stream().sorted(Comparator.comparing(Sale::getQuantity)).toList();

        System.out.println("Найти самый популярный товар (по количеству продаж)");
        List<Sale> popularProduct = sales.stream().sorted(Comparator.comparing(Sale::getQuantity).reversed()).limit(1).toList();

        System.out.println("Определить, сколько клиентов сделали более 1 заказа");

        int findCountOfOrdersByClients = (int) sales.stream().filter(sale -> sale.getQuantity() > 1).count();

        System.out.println("Товары которые покупали только 1 раз");
        List<Sale> findOrderWhereBoughtBought1Time = sales.stream().filter(sale -> sale.getQuantity() == 1).toList();

        System.out.println("Определить категорию с наибольшей выручкой");
        String findCategoryMostGreatestProfit = sales.stream().max(Comparator.comparing(Sale::getPrice).reversed()).get().getCategory();

        System.out.println("Найти города, где средний чек выше общего среднего чека");
        double avaragePrice = sales.stream().mapToDouble(Sale::getPrice).average().orElse(0.0);
        String findCityAvaragePrice = sales.stream().filter(sale -> sale.getPrice() > avaragePrice).findFirst().get().getCity();


        System.out.println("Рассчитать динамику продаж по месяцам (рост/падение в %)");
        Map<Month, Double> monthlyRevenue = sales.stream()
                .collect(Collectors.groupingBy(
                        sale -> sale.getDate().getMonth(),
                        Collectors.summingDouble(Sale::getPrice)
                ));

        // 2. Сортировка месяцев
        List<Map.Entry<Month, Double>> sortedMonths = monthlyRevenue.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toList());

        // 3. Вывод динамики
        if (sortedMonths.size() < 2) {
            System.out.println("Недостаточно данных для анализа");
            return;
        }

        System.out.println("Динамика продаж:");
        for (int i = 1; i < sortedMonths.size(); i++) {
            double prev = sortedMonths.get(i - 1).getValue();
            double curr = sortedMonths.get(i).getValue();
            double change = ((curr - prev) / prev) * 100;

            System.out.printf("%s → %s: %6.2f%% %s\n",
                    sortedMonths.get(i - 1).getKey(),
                    sortedMonths.get(i).getKey(),
                    Math.abs(change),
                    change >= 0 ? "↑ рост" : "↓ спад"
            );

            System.out.println(topMonth);


            System.out.println("Среднаяя выручка за месяц");
            System.out.println(avaragePriceOfMonth);

            System.out.println("Top 3 client");
            System.out.println(top3Clients);

            System.out.println("Определить, сколько клиентов сделали более 1 заказа");
            System.out.println(findMoreOrders);


            System.out.println("Самый популярный товар");
            System.out.println(popularProduct);


            System.out.println("Определить, сколько клиентов сделали более 1 заказа");
            System.out.println(findCountOfOrdersByClients);


            System.out.println("Товары которые покупали только 1 раз");
            System.out.println(findOrderWhereBoughtBought1Time);

            System.out.println("Определить категорию с наибольшей выручкой");
            System.out.println(findCategoryMostGreatestProfit);
            System.out.println(avaragePrice);
            System.out.println(findCityAvaragePrice);


        }
    }
}