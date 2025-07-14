

import java.io.*;
import java.util.*;

interface Product extends Comparable<Product>, Serializable {
    UUID getProductId();
    String getModel();
    double getPrice();
}

interface StoreManager {
    void addProduct(Product product);
    void removeProductById(UUID productId);
    Product findProductById(UUID productId);
    List<Product> filterProductsByPriceRange(double minPrice, double maxPrice);
    void sortProductsByPrice();
    void printAllProducts();
    List<Product> getProducts();
}

abstract class AbstractProduct implements Product {
    private UUID productId;
    private String model;
    private double price;

    protected AbstractProduct(UUID productId, String model, double price) {
        if (productId == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        if (model == null || model.isEmpty()) {
            throw new IllegalArgumentException("Model cannot be null or empty");
        }
        if (price <= 0.0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        this.productId = productId;
        this.model = model;
        this.price = price;
    }

    protected AbstractProduct(String model, double price) {
        this(UUID.randomUUID(), model, price);
    }

    @Override
    public UUID getProductId() {
        return productId;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setModel(String model) {
        if (model == null || model.isEmpty()) {
            throw new IllegalArgumentException("Model cannot be null or empty");
        }
        this.model = model;
    }

    public void setPrice(double price) {
        if (price <= 0.0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        this.price = price;
    }

    public void updateUUID(UUID productId) {
        if (productId == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Product{id=" + productId + ", model='" + model + "', price=" + price + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractProduct that = (AbstractProduct) o;
        return Double.compare(price, that.price) == 0 && productId.equals(that.productId) && Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, model, price);
    }

    @Override
    public int compareTo(Product o) {
        return Double.compare(o.getPrice(), this.price);
    }
}

enum STORAGE {
    GB_128, GB_256, GB_512, TB_1
}

class IPhone extends AbstractProduct implements Serializable {
    private STORAGE storage;

    public IPhone(String model, double price, STORAGE storage) {
        super(model, price);
        if (storage == null) {
            throw new IllegalArgumentException("Storage cannot be null");
        }
        this.storage = storage;
    }

    public IPhone(UUID productId, String model, double price, STORAGE storage) {
        super(productId, model, price);
        if (storage == null) {
            throw new IllegalArgumentException("Storage cannot be null");
        }
        this.storage = storage;
    }

    public STORAGE getStorage() {
        return storage;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        IPhone iPhone = (IPhone) o;
        return storage == iPhone.storage;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + storage.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "IPhone{id=" + getProductId() + ", model='" + getModel() +
                "', price=" + getPrice() + ", storage=" + storage + "}";
    }
}

enum PROCESSOR {
    M1, M2, M3, M4
}

class Macbook extends AbstractProduct implements Serializable {
    private PROCESSOR processor;

    public Macbook(String model, double price, PROCESSOR processor) {
        super(model, price);
        if (processor == null) {
            throw new IllegalArgumentException("Processor cannot be null");
        }
        this.processor = processor;
    }

    public Macbook(UUID productId, String model, double price, PROCESSOR processor) {
        super(productId, model, price);
        if (processor == null) {
            throw new IllegalArgumentException("Processor cannot be null");
        }
        this.processor = processor;
    }

    public PROCESSOR getProcessor() {
        return processor;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Macbook macbook = (Macbook) o;
        return processor == macbook.processor;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + processor.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Macbook{id=" + getProductId() + ", model='" + getModel() +
                "', price=" + getPrice() + ", processor=" + processor + "}";
    }
}

class IPad extends AbstractProduct implements Serializable {
    private double screenSize;

    public IPad(String model, double price, double screenSize) {
        super(model, price);
        if (screenSize <= 0.0) {
            throw new IllegalArgumentException("Screen size cannot be negative or zero");
        }
        this.screenSize = screenSize;
    }

    public IPad(UUID productId, String model, double price, double screenSize) {
        super(productId, model, price);
        if (screenSize <= 0.0) {
            throw new IllegalArgumentException("Screen size cannot be negative or zero");
        }
        this.screenSize = screenSize;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        if (screenSize <= 0.0) {
            throw new IllegalArgumentException("Screen size cannot be negative or zero");
        }
        this.screenSize = screenSize;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        IPad iPad = (IPad) o;
        return Double.compare(screenSize, iPad.screenSize) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp = Double.doubleToLongBits(screenSize);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "IPad{id=" + getProductId() + ", model='" + getModel() +
                "', price=" + getPrice() + ", screenSize=" + screenSize + "}";
    }
}

class AppleStore implements StoreManager, Serializable {
    private List<Product> products;

    public AppleStore() {
        this.products = new ArrayList<>();
    }

    @Override
    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        for (Product existingProduct : products) {
            if (existingProduct.getProductId().equals(product.getProductId())) {
                throw new IllegalArgumentException("Product with ID " + product.getProductId() + " already exists");
            }
        }
        products.add(product);
        System.out.println("Added product: " + product);
    }

    @Override
    public void removeProductById(UUID productId) {
        if (productId == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        boolean isRemoved = false;
        for (int i = 0; i < products.size(); i++) {
            if (productId.equals(products.get(i).getProductId())) {
                products.remove(i);
                isRemoved = true;
                break;
            }
        }
        if (!isRemoved) {
            throw new IllegalArgumentException("Product with ID " + productId + " not found");
        }
    }

    @Override
    public Product findProductById(UUID productId) {
        if (productId == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        for (Product existingProduct : products) {
            if (existingProduct.getProductId().equals(productId)) {
                return existingProduct;
            }
        }
        System.out.println("Product with ID " + productId + " not found");
        return null;
    }

    @Override
    public List<Product> filterProductsByPriceRange(double minPrice, double maxPrice) {
        if (minPrice < 0 || maxPrice < minPrice) {
            throw new IllegalArgumentException("Invalid price range");
        }
        List<Product> filteredProducts = new ArrayList<>();
        for (Product existingProduct : products) {
            if (existingProduct.getPrice() >= minPrice && existingProduct.getPrice() <= maxPrice) {
                filteredProducts.add(existingProduct);
            }
        }
        return filteredProducts;
    }

    @Override
    public void sortProductsByPrice() {
        Collections.sort(products);
    }

    @Override
    public void printAllProducts() {
        if (products.isEmpty()) {
            System.out.println("Store is empty");
            return;
        }
        System.out.println("All products:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    @Override
    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
            System.out.println("Store saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AppleStore loadFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (AppleStore) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

public class Test4 {
    public static void main(String[] args) {
        AppleStore store = new AppleStore();
        store.addProduct(new IPhone("iPhone 14 Pro", 999.99, STORAGE.GB_256));
        store.addProduct(new Macbook("MacBook Air", 1299.99, PROCESSOR.M2));
        store.addProduct(new IPad("iPad Pro", 799.99, 11.0));

        // Сохраняем магазин в файл
        store.saveToFile("store.text");

        // Загружаем магазин из файла
        AppleStore restoredStore = AppleStore.loadFromFile("store.text");
        if (restoredStore != null) {
            restoredStore.printAllProducts();
        }
    }
}