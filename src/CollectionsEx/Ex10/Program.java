package CollectionsEx.Ex10;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Product {
    private int id;
    private String name;
    private double price;

    Product(int id, String name, double price) {
        if (id < 0 || name == null || name.isEmpty() || price < 0.0) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.name = name;
        this.price = price;

    }
    public  boolean existsByIdOrName(Set<Product> products, int id, String name) {
        for (Product product : products) {
            if (product.getId() == id || product.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Price: " + price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }


}

public class Program {
    public static void main(String[] args) {
        Set<Product> products = new HashSet<>();
        products.add(new Product(1, "Icecream", 10.0));
        products.add(new Product(2, "Apple", 10.0));
        products.add(new Product(3, "Pineapple", 10.0));
        products.add(new Product(4, "Pineapple", 10.0));

        Product product = new Product(1, "Pineapple", 10.0);
        if (product.existsByIdOrName(products, product.getId(), product.getName())) {
            System.out.println("Product with ID: " + product.getId() + " and Name: " + product.getName() + " already exists");
        }
        else {
            products.add(product);
        }
    }
}
