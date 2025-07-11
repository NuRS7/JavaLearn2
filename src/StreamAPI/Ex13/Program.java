package StreamAPI.Ex13;




class Pair<K, V> {
    private K key;
    private V value;
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
    public void printType() {
        System.out.println("Key type: " + key.getClass() + " Value type: " + value.getClass());
    }
    @Override
    public String toString() {
        return "Class Pair and K: " + key + ", value: " + value;
    }
}
public class Program {
    public static void main(String[] args) {
        Pair<String, Integer> pair = new Pair<>("Key1", 3);
        pair.printType();
        System.out.println(pair.getKey());
        System.out.println(pair.getValue());
    }
}
