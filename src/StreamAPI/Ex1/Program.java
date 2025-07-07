package StreamAPI.Ex1;


import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;
/**
 * @author Nursutlan
 * @version 1.0
 * @comment: Создание потока с помощью Stream
 */
public class Program {
    public static void main(String[] args) {

        Stream<String> stream = Arrays.stream(new String[]{"Astana", "Almaty", "Shymkent"});
        stream.forEach(System.out::println);
        IntStream intStream = IntStream.range(1, 5);
        IntStream intStream2 = Arrays.stream(new int[]{1, 2, 3});
        intStream2.forEach(System.out::println);
        DoubleStream doubleStream = Arrays.stream(new double[]{1.0, 2.1, 3.2});
        System.out.println(doubleStream.count());
        String[] cities = new String[]{"Astana", "Almaty", "Shymkent"};
        Stream<String> stream1 = Stream.of(cities);
        stream1.forEach(System.out::println);
        }

}
