package StreamAPI.Ex7;

import java.util.stream.Stream;

public class Test2 {
    public static void main(String[] args) {
        Stream<String> stringStream1 = Stream.of("Astana", "Almaty");
        Stream<String> stringStream2 = Stream.of("Aktau", "Shymkent");
        Stream.concat(stringStream1, stringStream2).forEach(s -> System.out.println(s));
        // Статический метод concat() объединяет элементы двух потоков, возвращая объединенный поток

    }
}
