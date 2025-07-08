package StreamAPI.Ex5;


import java.util.Arrays;
import java.util.stream.Stream;

class Phone {
    private String model;
    private double price;

    public Phone(String model, double price) {
        if (model.isEmpty() || model == null) {
            throw new IllegalArgumentException();
        }
        if (price < 0.0) {
            throw new IllegalArgumentException();
        } this.model = model;
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "model='" + model + '\'' +
                ", price=" + price +
                '}';
    }
}
public class Program {
    public static void main(String[] args) {
        Stream<Phone> phoneStream = Stream.of(new Phone("Asus ROG 5", 10.0), new Phone("IPhone 16 Pro Max", 80.0));

        double sum = phoneStream.filter(phone -> phone.getPrice() > 10.0).map(Phone::getPrice).reduce((double) 0, (x, y) -> x + y);
        System.out.println(sum);
    }
}
