public class Test3 {
    public static void main(String[] args) {
        Integer i2 = Integer.valueOf("0010", 4);
        System.out.println(i2);
        GenericMethod genericMethod = new GenericMethod();
        System.out.println(GenericMethod.asByte(Float.valueOf(7.0F)));
        System.out.println(GenericMethod.asByte(String.valueOf("3")));

    }
}

class GenericMethod {

    public static <T> byte asByte(T num) {
        if (num instanceof Number) {
            return ((Number) num).byteValue();

        }
        if (num instanceof String) {
            return Byte.parseByte(((String) num));
        }
        return 0;
    }
}