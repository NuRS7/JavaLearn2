package OOP1.Ex2;

public class Progran {
    public static void main(String[] args) {
        Car car = new Car("Mercedes Benz", 2025);
        car.move();
        car.refuel();
        System.out.println(car.getInfo());
        Bus bus = new Bus("Huyndai", 2014);
        bus.move();
        bus.refuel();
        System.out.println(bus.getInfo());
    }
}

abstract class Transport {

    String model;
    int age;
Transport(String model, int age) {
        this.model = model;
        this.age = age;
    }
    abstract void move();
    String getInfo() {
        return "Model " + model + " age " + age;
    }
}


interface Refuellable {
    void refuel();
}


class Car extends Transport implements Refuellable {
    Car(String model, int age) {
        super(model, age);
    }
    @Override
    public void move() {
        System.out.println("Car moved");
    }

    @Override
    public void refuel() {
        System.out.println("Refueling");
    }
}

class Bus extends Transport implements Refuellable {
    Bus(String model, int age) {
        super(model, age);
    }
    public void refuel() {
        System.out.println("Refueling");
    }
    @Override
    public void move() {
        System.out.println("Bus moved");
    }
}