package StreamAPI.Ex7;

import java.util.stream.Stream;

public class Test3 {
    public static void main(String[] args) {
        Stream<String> people = Stream.of("Tom", "Bob", "Sam", "Tom", "Alice", "Kate", "Sam");
        people.distinct().forEach(p -> System.out.println(p));
        //Метод distinct() возвращает только уникальные элементы в виде потока

    }
}
