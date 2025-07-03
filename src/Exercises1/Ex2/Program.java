package Exercises1.Ex2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

// Интерфейс для транспортных средств
interface Vehicle extends Comparable<Vehicle> {
    UUID getVehicleId();
    String getModel();
    double getMaxSpeed();
}

// Интерфейс для управления автопарком
interface FleetManager {
    void addVehicle(Vehicle vehicle);
    void removeVehicleById(UUID vehicleId);
    Vehicle findFastestVehicle();
    List<Vehicle> filterVehiclesBySpeedRange(double minSpeed, double maxSpeed);
    void sortVehiclesBySpeed();
    void printAllVehicles();
    List<Vehicle> getVehicles();
}

// Абстрактный класс для транспортных средств
abstract class AbstractVehicle implements Vehicle {
    private UUID vehicleId;
    private String model;
    private double maxSpeed;

    protected AbstractVehicle(String model, double maxSpeed) {
        this(UUID.randomUUID(), model, maxSpeed);
    }

    protected AbstractVehicle(UUID vehicleId, String model, double maxSpeed) {
        if (vehicleId == null) {
            throw new IllegalArgumentException("Идентификатор транспорта не может быть null");
        }
        if (model == null || model.isEmpty()) {
            throw new IllegalArgumentException("Модель транспорта не может быть пустой");
        }
        if (maxSpeed <= 0.0) {
            throw new IllegalArgumentException("Максимальная скорость должна быть положительной");
        }
        this.vehicleId = vehicleId;
        this.model = model;
        this.maxSpeed = maxSpeed;
    }

    @Override
    public UUID getVehicleId() {
        return vehicleId;
    }

    @Override
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (model == null || model.isEmpty()) {
            throw new IllegalArgumentException("Модель транспорта не может быть пустой");
        }
        this.model = model;
    }

    @Override
    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        if (maxSpeed <= 0.0) {
            throw new IllegalArgumentException("Максимальная скорость должна быть положительной");
        }
        this.maxSpeed = maxSpeed;
    }

    @Override
    public int compareTo(Vehicle vehicle) {
        return Double.compare(vehicle.getMaxSpeed(), this.maxSpeed); // Сортировка по убыванию скорости
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractVehicle that = (AbstractVehicle) o;
        return Double.compare(maxSpeed, that.maxSpeed) == 0 &&
                Objects.equals(vehicleId, that.vehicleId) &&
                Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleId, model, maxSpeed);
    }

    @Override
    public String toString() {
        return "Vehicle{id=" + vehicleId + ", model='" + model + "', maxSpeed=" + maxSpeed + "}";
    }
}

// Класс для автомобилей
class Car extends AbstractVehicle {
    private int doors;

    public Car(UUID vehicleId, String model, double maxSpeed, int doors) {
        super(vehicleId, model, maxSpeed);
        if (doors <= 0) {
            throw new IllegalArgumentException("Количество дверей должно быть положительным");
        }
        this.doors = doors;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        if (doors <= 0) {
            throw new IllegalArgumentException("Количество дверей должно быть положительным");
        }
        this.doors = doors;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Car car = (Car) o;
        return doors == car.doors;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + doors;
        return result;
    }

    @Override
    public String toString() {
        return "Car{id=" + getVehicleId() + ", model='" + getModel() +
                "', maxSpeed=" + getMaxSpeed() + ", doors=" + doors + "}";
    }
}

// Перечисление для типов двигателя мотоцикла
enum ENGINE_TYPE {
    PETROL, ELECTRIC
}

// Класс для мотоциклов
class Motorcycle extends AbstractVehicle {
    private ENGINE_TYPE engineType;

    public Motorcycle(UUID vehicleId, String model, double maxSpeed, ENGINE_TYPE engineType) {
        super(vehicleId, model, maxSpeed);
        if (engineType == null) {
            throw new IllegalArgumentException("Тип двигателя не может быть null");
        }
        this.engineType = engineType;
    }

    public ENGINE_TYPE getEngineType() {
        return engineType;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Motorcycle motorcycle = (Motorcycle) o;
        return engineType == motorcycle.engineType;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + engineType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Motorcycle{id=" + getVehicleId() + ", model='" + getModel() +
                "', maxSpeed=" + getMaxSpeed() + ", engineType=" + engineType + "}";
    }
}

// Класс для управления автопарком
class Fleet implements FleetManager {
    private List<Vehicle> vehicles;

    public Fleet() {
        this.vehicles = new ArrayList<Vehicle>();
    }

    @Override
    public List<Vehicle> getVehicles() {
        return new ArrayList<Vehicle>(vehicles); // Возвращаем копию для инкапсуляции
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            System.out.println("Ошибка: транспорт не может быть null");
            return;
        }
        for (Vehicle existingVehicle : vehicles) {
            if (existingVehicle.getVehicleId().equals(vehicle.getVehicleId())) {
                System.out.println("Транспорт с ID " + vehicle.getVehicleId() + " уже существует");
                return;
            }
        }
        vehicles.add(vehicle);
        System.out.println("Транспорт добавлен: " + vehicle);
    }

    @Override
    public void removeVehicleById(UUID vehicleId) {
        boolean removed = false;
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getVehicleId().equals(vehicleId)) {
                vehicles.remove(i);
                removed = true;
                break;
            }
        }
        if (!removed) {
            System.out.println("Транспорт с ID " + vehicleId + " не найден");
        }
    }

    @Override
    public Vehicle findFastestVehicle() {
        if (vehicles.isEmpty()) {
            return null;
        }
        return Collections.max(vehicles);
    }

    @Override
    public List<Vehicle> filterVehiclesBySpeedRange(double minSpeed, double maxSpeed) {
        if (minSpeed < 0 || maxSpeed < minSpeed) {
            throw new IllegalArgumentException("Некорректный диапазон скоростей");
        }
        List<Vehicle> result = new ArrayList<Vehicle>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getMaxSpeed() >= minSpeed && vehicle.getMaxSpeed() <= maxSpeed) {
                result.add(vehicle);
            }
        }
        return result;
    }

    @Override
    public void sortVehiclesBySpeed() {
        Collections.sort(vehicles);
    }

    @Override
    public void printAllVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("Автопарк пуст");
            return;
        }
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }
}

// Главный класс для демонстрации работы
public class Program {
    public static void main(String[] args) {
        Fleet fleet = new Fleet();
        fleet.addVehicle(new Car(UUID.randomUUID(), "Toyota Camry", 180.0, 4));
        fleet.addVehicle(new Motorcycle(UUID.randomUUID(), "Harley-Davidson", 160.0, ENGINE_TYPE.PETROL));
        fleet.addVehicle(new Car(UUID.randomUUID(), "Tesla Model S", 250.0, 4));
        fleet.addVehicle(new Motorcycle(UUID.randomUUID(), "Zero SR/F", 200.0, ENGINE_TYPE.ELECTRIC));

        // 1. Выводим все транспортные средства
        System.out.println("Все транспортные средства:");
        fleet.printAllVehicles();

        // 2. Добавляем новый транспорт
        System.out.println("\nДобавляем мотоцикл 'Kawasaki Ninja':");
        fleet.addVehicle(new Motorcycle(UUID.randomUUID(), "Kawasaki Ninja", 300.0, ENGINE_TYPE.PETROL));
        fleet.printAllVehicles();

        // 3. Удаляем транспорт по ID
        System.out.println("\nПосле удаления первого транспорта:");
        UUID firstVehicleId = fleet.getVehicles().get(0).getVehicleId();
        fleet.removeVehicleById(firstVehicleId);
        fleet.printAllVehicles();

        // 4. Находим самый быстрый транспорт
        System.out.println("\nСамый быстрый транспорт:");
        Vehicle fastestVehicle = fleet.findFastestVehicle();
        System.out.println(fastestVehicle != null ? fastestVehicle : "Автопарк пуст");

        // 5. Фильтруем транспорт по диапазону скоростей
        System.out.println("\nТранспорт в диапазоне скоростей от 150.0 до 250.0 км/ч:");
        List<Vehicle> midRangeVehicles = fleet.filterVehiclesBySpeedRange(150.0, 250.0);
        for (Vehicle vehicle : midRangeVehicles) {
            System.out.println(vehicle);
        }

        // 6. Сортируем транспорт по скорости
        System.out.println("\nТранспорт, отсортированный по скорости:");
        fleet.sortVehiclesBySpeed();
        fleet.printAllVehicles();
    }
}