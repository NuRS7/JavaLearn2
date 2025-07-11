package StreamAPI.Ex15;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class User {
    int id;
    String name;

    User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User {" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

class UserService {
    public static Optional<User> findUserById(List<User> users, int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
public class Program {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Jack"));
        users.add(new User(2, "Jill"));
        users.add(new User(3, "Sam"));
        users.add(new User(4, "Sam"));
        users.add(new User(5, "Sam"));
        Optional<User> found = UserService.findUserById(users, 3);
        found.ifPresent(System.out::println);

    }
}
