package StreamAPI.Ex14;


import java.util.*;

class Pair<K, V> {
    private K keyPair;
    private V valuePair;
    public Pair(K keyPair, V valuePair) {
        this.keyPair = keyPair;
        this.valuePair = valuePair;
    }

    public K getKeyPair() {
        return keyPair;
    }

    public void setKeyPair(K keyPair) {
        this.keyPair = keyPair;
    }

    public V getValuePair() {
        return valuePair;
    }

    public void setValuePair(V valuePair) {
        this.valuePair = valuePair;
    }

    @Override
    public String toString() {
        return
                "keyPair= " + keyPair +
                ", valuePair= " + valuePair +
                '}';
    }
}

class PairStorage<K, V> {
    private final List<Pair<K, V>> pairs = new ArrayList<>();

    public void addPair(Pair<K, V> pair) {
        pairs.add(pair);
    }

    public void printAll() {
        for (Pair<K, V> pair : pairs) {
            System.out.println(pair.getKeyPair() + " " + pair.getValuePair());
        }
    }

    public V getKeyByValue(K key) {
        for (Pair<K, V> pair : pairs) {
            if (pair.getKeyPair().equals(key)) {
                return pair.getValuePair();
            }
        }
        return null;
    }
    public List<Pair<K, V>> getAllPairs() {
        return pairs;
    }

    public Optional<V> findValueById(K key) {
        for (Pair<K, V> pair : pairs) {
            if (pair.getKeyPair().equals(key)) {
                return Optional.of(pair.getValuePair());
            }
        }
        return Optional.empty();
    }
}

//если добавить доп класс для реализовации
class PairService<K, V> {
    private final PairStorage<K, V> storage;

    public PairService(PairStorage<K, V> storage) {
        this.storage = storage;
    }

    public Optional<V> getPairById(K key) {
        for (Pair<K, V> pair : storage.getAllPairs()) {
            if (pair.getKeyPair().equals(key)) {
                return Optional.of(pair.getValuePair());
            }
        }
        return Optional.empty();
    }
    public Optional<K> findKeyByValue(V value) {
        for (Pair<K, V> pair : storage.getAllPairs()) {
            if (pair.getValuePair().equals(value)) {
                return Optional.of(pair.getKeyPair());
            }
        }
        return Optional.empty();
    }

    public List<Pair<K, V>> findPairsByKey (K key) {
        List<Pair<K, V>> newPairsList = new ArrayList<>();
        for (Pair<K, V> pair : storage.getAllPairs()) {
            if (pair.getKeyPair().equals(key)) {
                newPairsList.add(pair);
            }
        }
        return newPairsList;
    }

    public void replaceAllValuesByKey(K key, V value) {
        for (Pair<K, V> pair : storage.getAllPairs()) {
            if (pair.getKeyPair().equals(key)) {
                pair.setValuePair(value);
            }
        }
    }

    public Map<K, List<V>> groupByKey() {
        Map<K, List<V>> result = new HashMap<>();

        for (Pair<K, V> pair : storage.getAllPairs()) {
            K key = pair.getKeyPair();
            V value = pair.getValuePair();

            // Если ключ уже есть, добавим в список
            if (result.containsKey(key)) {
                result.get(key).add(value);
            } else {
                // Если ключа нет — создаём новый список
                List<V> newList = new ArrayList<>();
                newList.add(value);
                result.put(key, newList);
            }
        }

        return result;
    }


}
public class Program {
    public static void main(String[] args) {

        Pair<Integer, String> pair = new Pair<>(1, "Samsung");
        PairStorage<Integer, String> pairStorage = new PairStorage<>();
        pairStorage.addPair(new Pair<>(1, "Nursultan"));
        pairStorage.addPair(new Pair<>(2, "KZ"));
        pairStorage.addPair(new Pair<>(3, "Astana"));
        pairStorage.printAll();
        System.out.println(pairStorage.getKeyByValue(1));
        Optional<String> optional = pairStorage.findValueById(1);
        System.out.println(optional);
        String res = pairStorage.findValueById(99).orElse("Не найден");
        System.out.println(res);
        PairService<Integer, String> service = new PairService<>(pairStorage);
        System.out.println(service.getPairById(1));
        System.out.println(service.findKeyByValue("KZ").get());
        System.out.println(service.findPairsByKey(3));

        System.out.println(service.groupByKey());
    }
}
