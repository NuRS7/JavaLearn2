package StreamAPI.Ex10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


class Product  {
    private String nameOfProduct;
    private double price;

    public Product(String name, double price) {
        this.nameOfProduct = name;
        this.price = price;
    }
    public String getNameOfProduct() {
        return nameOfProduct;
    }
    public double getPrice() {
        return price;
    }
    @Deprecated
    public String toString() {
        return "Product name " + nameOfProduct + " price " + price;
    }

public Optional<Product> getExpensive(List<Product> products) {
    return products.stream().reduce((p1, p2) -> p1.getPrice() > p2.getPrice() ? p1 : p2);
}
}

public class Program {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Apple", 30.0));
        products.add(new Product("Orange", 40.0));
        products.add(new Product("Water", 50.0));
        products.add(new Product("Pear", 60.0));
        products.add(new Product("Pineapple", 70.0));
        Product product = new Product("Apple", 30.0);
        System.out.println(product.getExpensive(products));
        List<Integer> list = Arrays.asList(new Integer[]{1, 3, 4, 4, 5});
        System.out.println(list.stream().reduce(0, Integer::max));
    }
}
