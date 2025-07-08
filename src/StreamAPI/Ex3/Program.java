package StreamAPI.Ex3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        List<String> phones = new ArrayList<>();

        phones.addAll(Arrays.asList(new String[]{"iPhone 6 S", "Lumia 950", "Huawei Nexus 6P",
                "Samsung Galaxy S 6", "LG G 4", "Xiaomi MI 5",
                "ASUS Zenfone 2", "Sony Xperia Z5", "Meizu Pro 5",
                "Lenovo S 850"}));
        phones.stream().skip(1).map(String::toUpperCase).forEach(System.out::println);

        int pageSize = 3;
        Scanner sc = new Scanner(System.in);
        while (true) {
            int page = sc.nextInt();

            if (page == 1) {
                break;
            }
            phones.stream().skip((page - 1) * pageSize).limit(pageSize).forEach(s->System.out.println(s));
        }
    }
}
