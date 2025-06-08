package staticAndFinalKeywords;

public class Program {
    public static void main(String[] args) {
        User user = new User();
        User user2 = new User();
        user2.printUser();
        System.out.println(User.getTotalUser());

    }
}

class User {
    static int totalUser = 1;
    final int id;

    User() {
        id = totalUser++;
    }
    void printUser() {
        System.out.println(id);
    }

    static int getTotalUser() {
        return totalUser;
    }
}