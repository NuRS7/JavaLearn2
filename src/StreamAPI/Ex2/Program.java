package StreamAPI.Ex2;

import java.util.Arrays;
import java.util.stream.Stream;


class IPhone {
    private String model;
    private double price;
    IPhone(String model, double price) {
        this.model = model;
        this.price = price;
    }
    public String getModel() {
        return model;
    }
    public double getPrice() {
        return price;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String toString() {
        return "Model" +model + " price: " + price;
    }
}
public class Program {
    public static void main(String[] args) {

        Stream<IPhone> phoneStream = Stream.of(new IPhone("IPhone 11", 20.0), new IPhone("IPhone 12", 30.0));

        System.out.println("============================");
        phoneStream.flatMap(p -> Stream.of(
                String.format("название: %s  цена без скидки: %.2f", p.getModel(), p.getPrice()),
                String.format("название: %s  цена со скидкой: %.2f", p.getModel(), p.getPrice() - p.getPrice() * 0.1)
        )).forEach(System.out::println);
        Stream<String> stringStream = Arrays.stream(new String[]{"Astana", "Almaty", "Shymkent", "Turkistan"});


    }
}
