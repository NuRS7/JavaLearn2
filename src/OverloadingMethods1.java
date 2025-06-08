import java.util.Arrays;

public class OverloadingMethods1 {
    public static void main(String[] args) {
            Printer printer = new Printer();
        printer.print(20);
        printer.print(20.0);
        printer.print("Nr");
        printer.print(new String[]{"Java", "Python", "C++"});
    }
}

class Printer {
    void print(String string) {
        System.out.println(string);
    }
    void print(int number) {
        System.out.println(number);

    }
    void print(double DoubleNumber) {
        System.out.println(DoubleNumber);
    }

    void print(String[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}