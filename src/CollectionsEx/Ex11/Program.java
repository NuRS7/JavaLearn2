package CollectionsEx.Ex11;


import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

class Product {
    private int id;
    private String name;
    private double price;

    Product(int id, String name, double price) {
        if (id < 0 || name.isEmpty() || price < 0.0) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.name = name;
        this.price = price;
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

    public boolean existingBoolean(Product product, int id, String name) {
        if (product.getId() == id || product.getName().equalsIgnoreCase(name)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !o.getClass().equals(this.getClass())) {
            return false;
        }
        Product p = (Product) o;
        return id == p.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

public class Program {
    public static void main(String[] args) {
        Set<Product> products = new TreeSet<>((p1, p2) -> {
            int result = Double.compare(p1.getId(), p2.getId());
            if (result == 0) {
                result = p1.getName().compareTo(p2.getName());
            }
            return result;
        });
        products.add(new Product(1, "A", 100));
        products.add(new Product(2, "B", 200));
        products.add(new Product(3, "C", 300));
        products.add(new Product(4, "D", 400));
        products.add(new Product(5, "A", 400));


        Product product1 = new Product(6, "B", 400);
        if (product1.existingBoolean(product1, product1.getId(), product1.getName())) {
            System.out.println("Already existing product");
        }
        else products.add(product1);
        for (Product p : products) {
            System.out.println(p);
        }
    }
}
