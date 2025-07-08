package StreamAPI.Ex4;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * @author Nursultan
 * @version 1
 * @comment: Метод count() возвращает количество элементов в потоке данных
 * Метод findFirst() извлекает из потока первый элемент, а findAny() извлекает случайный объект из потока (нередко так же первый):
 */
public class Program {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.addAll(Arrays.asList("Astana", "Almaty", "Shymkent", "Turkistan"));
        System.out.println(list.stream().distinct().count());
        System.out.println(list.stream().distinct().filter(len -> len.length() > 6).count());
        list.stream().findFirst().ifPresent(System.out::println);
        System.out.println(list.stream().findAny());


    }
}
