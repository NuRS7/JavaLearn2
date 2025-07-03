package Exercises1.Ex3;

import java.util.*;

interface Product extends Comparable<Product> {
    UUID getProductId();

    String getModel();

    double getPrice();
}


abstract class AbstractProduct implements Product {
    private UUID productId;
    private String model;
    private double price;
    protected AbstractProduct(UUID productId, String model, double price) {
        if (productId == null) {
            throw new IllegalArgumentException("productId cannot be null");
        }
        if (model == null || model.length() == 0 || model.isEmpty()) {
            throw new IllegalArgumentException("model cannot be null or empty");
        }
        if (price < 0.0) {
            throw new IllegalArgumentException("price cannot be negative");
        }
        this.productId = productId;
        this.model = model;
        this.price = price;
    }
    protected AbstractProduct( String model, double price) {
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
            throw new IllegalArgumentException("model cannot be null or empty");
        }
        this.model = model;
    }
    public void setPrice(double price) {
        if (price < 0.0) {
            throw new IllegalArgumentException("price cannot be negative");
        }
        this.price = price;
    }

    public UUID updateUUID(UUID productId) {
        if (productId == null) {
            throw new IllegalArgumentException("productId cannot be null");
        }
        this.productId = productId;
        return productId;
    }

    @Override
    public String toString() {
        return "{" +
                "productId= " + productId +
                ", model= '" + model + '\'' +
                ", price= " + price +
                '}';
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
class IPhone extends AbstractProduct {
    private STORAGE storage;

    public IPhone(String model, double price, STORAGE storage) {
        super(model, price);
        if (storage == null) {
            throw new IllegalArgumentException("storage cannot be null");
        }
        this.storage = storage;
    }

    public IPhone(UUID productId,String model, double price, STORAGE storage) {
        super(productId,model, price);
        if (storage == null) {
            throw new IllegalArgumentException("storage cannot be null");
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

    public int hashcode() {
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
    M1,M2, M3, M4
}
class Macbook extends AbstractProduct {
    private PROCESSOR processor;
    public Macbook(String model, double price, PROCESSOR processor) {
        super(model, price);
        if (processor == null) {
            throw new IllegalArgumentException("processor cannot be null");
        }
        this.processor = processor;
    }

    public Macbook(UUID productId,String model, double price, PROCESSOR processor) {
        super(productId,model, price);
        if (processor == null) {
            throw new IllegalArgumentException("processor cannot be null");
        } this.processor = processor;
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

    public int hashcode() {
        int result = super.hashCode();
        result = 31 * result + processor.hashCode();
        return result;
    }
    @Override
    public String toString() {

        return "{" +
                "productId= " + this.getProductId() +
                ", model= '" + this.getModel() + '\'' +
                ", price= " + this.getPrice() +
                ", PROCESSOR= '" + this.getProcessor() + '\'' +
                '}';
    }

}

class IPad extends AbstractProduct {
    private double screenSize;

    IPad(String model, double price, double screenSize) {
        super(model, price);
        if (screenSize <= 0.0) {
            throw new IllegalArgumentException("screen size cannot be negative");
        }
        this.screenSize = screenSize;
    }

    public IPad(UUID productId, String model, double price, double screenSize) {
        super(productId, model, price);
        if (screenSize <= 0.0) {
            throw new IllegalArgumentException("screen size cannot be negative");
        }
        this.screenSize = screenSize;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        if (screenSize <= 0.0) {
            throw new IllegalArgumentException("screen size cannot be negative");
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


interface StoreManager {
    void addProduct(Product product);

    void removeProductById(UUID productId);

    Product findProductById(UUID productId);

    List<Product> filterProductsByPriceRange(double minPrice, double maxPrice);

    void sortProductsByPrice();

    void printAllProducts();

    List<Product> getProducts();

}

class AppleStore implements StoreManager {
    List<Product> products;

    public AppleStore() {
        this.products = new ArrayList<>();
    }


    @Override
    public void addProduct(Product product) {
        for (Product existingProduct : products) {
            if (existingProduct.getProductId().equals(product.getProductId())) {
                throw new IllegalArgumentException("product already exists");
            }
        }
        products.add(product);
        System.out.println("Added product " + product);
    }

    @Override
    public void removeProductById(UUID productId) {
        if (productId == null) {
            throw new IllegalArgumentException("productId cannot be null");
        }
        boolean isRemoved = false;
        for (int i = 0; i < products.size(); i++) {
            if (productId.equals(products.get(i).getProductId())) {
                isRemoved = true;
                products.remove(i);
                break;
            }
        }
        if (!isRemoved) {
            throw new IllegalArgumentException("product not found");
        }
    }

    @Override
    public Product findProductById(UUID productId) {
        if (productId == null) {
            throw new IllegalArgumentException("productId cannot be null");
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
        List<Product> filteredProducts = new ArrayList<Product>();
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
            System.out.println("Nothing to print");
            return;
        }
        for (Product product : products) {
            System.out.println(product);
        }
    }

    @Override
    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }
}
public class Program {
    public static void main(String[] args) {
        AppleStore appleStore = new AppleStore();
        appleStore.addProduct(new Macbook("Macbook Pro 24", 0.90, PROCESSOR.M1));
        appleStore.addProduct(new Macbook("Macbook 23", 1.30, PROCESSOR.M1));
        appleStore.addProduct(new Macbook("Macbook Pro 23", 1.20, PROCESSOR.M1));
        appleStore.addProduct(new IPad("IPad 23", 1.33, 6.7));
        appleStore.sortProductsByPrice();
        UUID IPhoneID = UUID.randomUUID();
        appleStore.addProduct(new IPhone(IPhoneID, "Iphone XR", 12.9, STORAGE.GB_128));
        System.out.println(appleStore.findProductById(IPhoneID));
        appleStore.printAllProducts();

    }
}


