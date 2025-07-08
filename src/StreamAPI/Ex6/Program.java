package StreamAPI.Ex6;




record User(String name, int age) {}


sealed interface ApiResponse permits Success, Error, Redirect {}

record Success(String data) implements ApiResponse { }
record Error(int code, String message) implements ApiResponse { }
record Redirect(String url) implements ApiResponse { }


public class Program {
    public static void main(String[] args) {
        User user = new User("John", 25);
        System.out.println(user.age());
    }


}
