package StreamAPI.Ex8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class Program {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(new Integer[]{1, 2, 3, 4, 4}));
        Optional<Integer> min = list.stream().min(Integer::compareTo);
        Optional<Integer> max = list.stream().max(Integer::compareTo);
        System.out.println(min.get());
        System.out.println(max.get());
    }
}
