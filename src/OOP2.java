public class OOP2 {
    public static void main(String[] args) {
        Transport[] transports = {
                new Car("Toyota", 2020, 0.5),
                new Bus("Yutong", 2018, 0.3),
                new Plane("Boeing 737", 2015, 2.5)
        };

        for (Transport t : transports) {
            System.out.println(t.getInfo());
            ((Bookable) t).book("Nursultan", 100);
            System.out.println("-----------------");
        }
    }
}

interface Bookable {
    void book(String passengers, double distance);
}
 abstract class Transport {
     String model;
     int year;
     double pricePerHour;

     abstract double calculatePrice(double distance);
     String getInfo() {
         return "Model " + model + " year: " + year + " pricePerHour: " + pricePerHour;
     }
}

class Car extends Transport implements Bookable {
    Car(String model) {
        this.model = model;
        this.year = 2021;
        this.pricePerHour = 1.5;

    }
    Car(String model, int year) {
        this.model = model;
        this.year = year;
        this.pricePerHour = 1.5;
    }

    Car() {
        this.model = "Car";
        this.year = 2021;
        this.pricePerHour = 1.5;
    }
    Car(String model, int year, double pricePerHour) {
        super.model = model;
        super.year = year;
        super.pricePerHour = pricePerHour;
    }
    @Override
    public double calculatePrice(double distance) {
        return pricePerHour * distance;
    }
    @Override
    public void book(final String passenger, double distance) {
        if (distance <= 0) {
            throw new IllegalArgumentException("Расстояние должно быть больше 0");
        }
        double price = calculatePrice(distance);
        System.out.println("Бронирование для: " + passenger);
        System.out.println("Расстояние: " + distance + " км");
        System.out.println("Общая стоимость: " + price + " USD");
    }

    public String getModel() {
        return model;
    }
    public int getYear() {
        return year;
    }
    public double getPricePerHour() {
        return pricePerHour;
    }
    public void setModel(String model) {
        if (model.isEmpty() || model == null) {
            throw new IllegalArgumentException();
        } else this.model = model;
    }

    public void setYear(int year) {
        if (year < 1990 || year > 2025) {
            throw new IllegalArgumentException();
        }
        else this.year = year;
    }

    public void setPricePerHour(double pricePerHour) {
        if (pricePerHour < 0 || pricePerHour > 100) {
            throw new IllegalArgumentException();
        } else this.pricePerHour = pricePerHour;
    }
}


class Bus extends Transport implements Bookable {
    Bus() {
        this.model = "Bus";
        this.year = 2020;
        this.pricePerHour = 1.5;
    }

    Bus(String model) {
        this.model = model;
        this.year = 2020;
        this.pricePerHour = 1.5;
    }

    Bus(String model, int year) {
        this.model = model;
        this.year = year;
        this.pricePerHour = 1.5;
    }

    Bus(String model, int year, double pricePerHour) {
        this.model = model;
        this.year = year;
        this.pricePerHour = pricePerHour;
    }
    public double calculatePrice(double distance) {
        return pricePerHour * distance;
    }
    @Override
    public void book(final String passenger, double distance) {
        if (distance <= 0) {
            throw new IllegalArgumentException("Расстояние должно быть больше 0");
        }
        double price = calculatePrice(distance);
        System.out.println("Бронирование для: " + passenger);
        System.out.println("Расстояние: " + distance + " км");
        System.out.println("Общая стоимость: " + price + " USD");
    }

}

class Plane extends Transport implements Bookable {
    Plane() {
        this.model = "Plane";
        this.year = 2020;
        this.pricePerHour = 1.5;
    }
    Plane(String model) {
        this.model = model;
        this.year = 2020;
        this.pricePerHour = 1.5;
    }
    Plane(String model, int year) {
        this.model = model;
        this.year = year;
        this.pricePerHour = 1.5;

    }
    Plane(String model, int year, double pricePerHour) {
        this.model = model;
        this.year = year;
        this.pricePerHour = pricePerHour;
    }
    public double calculatePrice(double distance) {
        if (distance <= 0) {
            throw new IllegalArgumentException("Должно больше 0 ");
        } else return pricePerHour * distance;
    }
    @Override
    public void book(final String passenger, double distance) {
        if (distance <= 0) {
            throw new IllegalArgumentException("Расстояние должно быть больше 0");
        }
        double price = calculatePrice(distance);
        System.out.println("Бронирование для: " + passenger);
        System.out.println("Расстояние: " + distance + " км");
        System.out.println("Общая стоимость: " + price + " USD");
    }
}