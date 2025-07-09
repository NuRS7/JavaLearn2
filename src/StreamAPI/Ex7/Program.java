package StreamAPI.Ex7;

import java.util.stream.Stream;

public class Program {
    public static void main(String[] args) {
        Stream<Integer> streamInteger = Stream.of(1, 2, 3, 4, -4, -5, -6);
        streamInteger.sorted().takeWhile(integer -> integer < 0).forEach(s -> System.out.println(s));
        // -6
        // -5
        // -4
    }
}
