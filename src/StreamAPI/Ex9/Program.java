package StreamAPI.Ex9;

import java.util.ArrayList;
import java.util.Arrays;

public class Program {
    public static void main(String[] args) {
        ArrayList<String> newList = new ArrayList<>();
        newList.addAll(Arrays.asList(new String[]{"Tom", "Bill", "Sasha", "Anna"}));
        System.out.println(newList.stream().anyMatch(s -> s.equals("Tom")));
        // возвращает true, если все элементы потока удовлетворяют условию в предикате
        System.out.println(newList.stream().allMatch(s -> s.length() == 3));

        // возвращает true, если хоть один элемент потока удовлетворяют условию в предикате
        System.out.println(newList.stream().anyMatch(s -> s.length() == 3));
        // НЕТ ЛИ в потоке строки "Bill". Если нет, то true, если есть, то false
        System.out.println(newList.stream().noneMatch(s -> s == "Bill"));

        // здесь скип мы получается скиппаем на 2 элемента и дальше найдем следующий элемент из потока
        System.out.println(newList.stream().skip(2).findAny());
        // здесь мы находим самый первый элемент из потока поэтому findFirst()
        System.out.println(newList.stream().findFirst());



    }
}
