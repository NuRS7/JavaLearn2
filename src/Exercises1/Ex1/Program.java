package Exercises1.Ex1;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

// Интерфейс для элементов инвентаря
interface InventoryItem extends Comparable<InventoryItem> {
    UUID getItemCode();
    String getName();
    double getPrice();
}

// Интерфейс для управления инвентарем
interface InventoryManager {
    void addItem(InventoryItem item);
    void removeItemByCode(UUID itemCode);
    InventoryItem findMostExpensiveItem();
    List<InventoryItem> filterItemsByPriceRange(double minPrice, double maxPrice);
    void sortItemsByPrice();
    void printAllItems();
    List<InventoryItem> getItems();
}

// Абстрактный класс для элементов инвентаря
abstract class AbstractInventoryItem implements InventoryItem {
    private UUID itemCode;
    private String name;
    private double price;

    // Конструктор с автоматической генерацией UUID
    protected AbstractInventoryItem(String name, double price) {
        this(UUID.randomUUID(), name, price);
    }

    // Конструктор с явным указанием UUID
    protected AbstractInventoryItem(UUID itemCode, String name, double price) {
        if (itemCode == null) {
            throw new IllegalArgumentException("Код товара не может быть null");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Название товара не может быть пустым");
        }
        if (price < 0.0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        }
        this.itemCode = itemCode;
        this.name = name;
        this.price = price;
    }

    @Override
    public UUID getItemCode() {
        return itemCode;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Название товара не может быть пустым");
        }
        this.name = name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0.0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        }
        this.price = price;
    }

    @Override
    public int compareTo(InventoryItem item) {
        return Double.compare(item.getPrice(), this.price); // Сортировка по убыванию цены
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractInventoryItem that = (AbstractInventoryItem) o;
        return Double.compare(price, that.price) == 0 &&
                Objects.equals(itemCode, that.itemCode) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemCode, name, price);
    }

    @Override
    public String toString() {
        return "InventoryItem{code=" + itemCode + ", name='" + name + "', price=" + price + "}";
    }
}

// Класс для электроники
class Electronics extends AbstractInventoryItem {
    private int warrantyMonths;

    public Electronics(UUID itemCode, String name, double price, int warrantyMonths) {
        super(itemCode, name, price);
        if (warrantyMonths < 1) {
            throw new IllegalArgumentException("Гарантия должна быть положительной");
        }
        this.warrantyMonths = warrantyMonths;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    public void setWarrantyMonths(int warrantyMonths) {
        if (warrantyMonths < 1) {
            throw new IllegalArgumentException("Гарантия должна быть положительной");
        }
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Electronics electronics = (Electronics) o;
        return warrantyMonths == electronics.warrantyMonths;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + warrantyMonths;
        return result;
    }

    @Override
    public String toString() {
        return "Electronics{code=" + getItemCode() + ", name='" + getName() +
                "', price=" + getPrice() + ", warranty=" + warrantyMonths + " months}";
    }
}

// Перечисление для размеров одежды
enum SIZE {
    S, M, L
}

// Класс для одежды
class Clothing extends AbstractInventoryItem {
    private SIZE size;

    public Clothing(UUID itemCode, String name, double price, SIZE size) {
        super(itemCode, name, price);
        if (size == null) {
            throw new IllegalArgumentException("Размер не может быть null");
        }
        this.size = size;
    }

    public SIZE getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Clothing clothing = (Clothing) o;
        return size == clothing.size;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + size.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Clothing{code=" + getItemCode() + ", name='" + getName() +
                "', price=" + getPrice() + ", size=" + size + "}";
    }
}

// Класс для управления инвентарем
class Inventory implements InventoryManager {
    private List<InventoryItem> items;

    public Inventory() {
        this.items = new ArrayList<InventoryItem>();
    }

    @Override
    public List<InventoryItem> getItems() {
        return new ArrayList<InventoryItem>(items); // Возвращаем копию для инкапсуляции
    }

    @Override
    public void addItem(InventoryItem item) {
        for (InventoryItem existingItem : items) {
            if (existingItem.getItemCode().equals(item.getItemCode())) {
                System.out.println("Товар с кодом " + item.getItemCode() + " уже существует");
                return;
            }
        }
        items.add(item);
        System.out.println("Товар добавлен: " + item);
    }

    @Override
    public void removeItemByCode(UUID itemCode) {
        boolean removed = false;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getItemCode().equals(itemCode)) {
                items.remove(i);
                removed = true;
                break;
            }
        }
        if (!removed) {
            System.out.println("Товар с кодом " + itemCode + " не найден");
        }
    }

    @Override
    public InventoryItem findMostExpensiveItem() {
        if (items.isEmpty()) {
            return null;
        }
        return Collections.max(items);
    }

    @Override
    public List<InventoryItem> filterItemsByPriceRange(double minPrice, double maxPrice) {
        if (minPrice < 0 || maxPrice < minPrice) {
            throw new IllegalArgumentException("Некорректный ценовой диапазон");
        }
        List<InventoryItem> result = new ArrayList<InventoryItem>();
        for (InventoryItem item : items) {
            if (item.getPrice() >= minPrice && item.getPrice() <= maxPrice) {
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public void sortItemsByPrice() {
        Collections.sort(items);
    }

    @Override
    public void printAllItems() {
        if (items.isEmpty()) {
            System.out.println("Инвентарь пуст");
            return;
        }
        for (InventoryItem item : items) {
            System.out.println(item);
        }
    }
}

// Главный класс для демонстрации работы
public class Program {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        inventory.addItem(new Electronics(UUID.randomUUID(), "Laptop", 999.99, 24));
        inventory.addItem(new Clothing(UUID.randomUUID(), "T-Shirt", 19.99, SIZE.M));
        inventory.addItem(new Electronics(UUID.randomUUID(), "Smartphone", 599.99, 12));
        inventory.addItem(new Clothing(UUID.randomUUID(), "Jeans", 49.99, SIZE.L));

        // 1. Выводим все товары
        System.out.println("Все товары:");
        inventory.printAllItems();

        // 2. Добавляем новый товар
        System.out.println("\nДобавляем товар 'Headphones':");
        inventory.addItem(new Electronics(UUID.randomUUID(), "Headphones", 79.99, 6));
        inventory.printAllItems();

        // 3. Удаляем товар по коду
        System.out.println("\nПосле удаления первого товара:");
        UUID firstItemCode = inventory.getItems().get(0).getItemCode();
        inventory.removeItemByCode(firstItemCode);
        inventory.printAllItems();

        // 4. Находим самый дорогой товар
        System.out.println("\nСамый дорогой товар:");
        InventoryItem expensiveItem = inventory.findMostExpensiveItem();
        System.out.println(expensiveItem != null ? expensiveItem : "Инвентарь пуст");

        // 5. Фильтруем товары по ценовому диапазону
        System.out.println("\nТовары в диапазоне цен от 50.0 до 600.0:");
        List<InventoryItem> midRangeItems = inventory.filterItemsByPriceRange(50.0, 600.0);
        for (InventoryItem item : midRangeItems) {
            System.out.println(item);
        }

        // 6. Сортируем товары по цене
        System.out.println("\nТовары, отсортированные по цене:");
        inventory.sortItemsByPrice();
        inventory.printAllItems();
    }
}