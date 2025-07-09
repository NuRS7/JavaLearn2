package StreamAPI.Ex7;

import java.util.stream.Stream;

public class Test1 {
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, -4, -5, -6);
        stream.sorted().dropWhile(integer -> integer<0).forEach(s -> System.out.println(s));
    }
}
