//package CollectionsEx.Ex5;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.UUID;
//public class Progran {
//    public static void main(String[] args) {
//        Airplane airplane = new Airplane("Boing-747", 300, 2000.0, false);
//        List<Airplane> airFleet = new AirFleet().addAirplane(airplane, "Airbus-380", 100, 3000, false);
//        airFleet.forEach(System.out::println);
//    }
//}
//
//class Airplane {
//    private final UUID uuid;
//
//    public UUID getUuid() {
//        return uuid;
//    }
//
//    public String getModel() {
//        return model;
//    }
//
//    public int getCapacity() {
//        return capacity;
//    }
//
//    public double getRange() {
//        return range;
//    }
//
//    public boolean isInService() {
//        return inService;
//    }
//
//    private String model;
//    private int capacity;
//    private double range;
//    private boolean inService;
//
//    Airplane(String model, int capacity, double range, boolean inService) {
//        if (model == null || model.isEmpty() || capacity < 0 || capacity > 400 || range < 0 || range > 20000) {
//            throw new IllegalArgumentException("Invalid arguments");
//        }
//        this.uuid = UUID.randomUUID();
//        this.model = model;
//        this.capacity = capacity;
//        this.range = range;
//        this.inService = inService;
//    }
//    @Override
//    public String toString() {
//        return "Airplane model: " + model + ", capacity: " + capacity + ", range: " + range + ", inService: " + inService;
//    }
//
//}
//
//
//class AirFleet {
//    public static List<Airplane> addAirplane(Airplane airplane, String model, int capacity, double range, boolean inService) {
//        if (airplane.getModel().contains(model)) {
//            throw new IllegalArgumentException("Invalid arguments");
//        }
//        if (capacity < 0 || capacity > 400) {
//            throw new IllegalArgumentException("Invalid arguments");
//        }
//        if (range < 0 || range > 20000) {
//            throw new IllegalArgumentException("Invalid arguments");
//        }
//        ArrayList<Airplane> airplanes = new ArrayList<Airplane>();
//        airplanes.add(airplane);
//        return airplanes;
//    }
//
//    public static void printAllAirplanes(List<Airplane> airplanes) {
//        for (Airplane airplane : airplanes) {
//            System.out.println(airplane);
//        }
//    }
//
//    public static Airplane sortByCapacity(ArrayList<Airplane> airplanes) {
//        if (airplanes == null || airplanes.isEmpty()) {
//            throw new IllegalArgumentException("Invalid arguments");
//        }
//        Collections.sort(airplanes);
//        for (Airplane airplane : airplanes) {
//            System.out.println(airplane);
//        }
//
//    }
//
//    public static List<Airplane> findByModel(List<Airplane> airplanes, String model) {
//        if (model == null || model.isEmpty()) {
//            throw new IllegalArgumentException("Invalid arguments");
//        }
//        for (Airplane airplane : airplanes) {
//            if (airplane.getModel().contains(model)) {
//                return airplanes;
//            }
//        }
//        return null;
//    }
//
//
//    public static void getAllInService(List<Airplane> airplanes) {
//        for (Airplane airplane : airplanes) {
//            if (airplane.isInService()) {
//                printAllAirplanes(airplanes);
//            } else {
//                System.out.println("Нет самолетов в сервисе");
//            }
//        }
//    }
//
//
//
//
//}