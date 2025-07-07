package CollectionsEx.Ex9;

import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;


class User {
    private int id;
    private String name;
    private String email;
    private String password;
    User(int id, String name, String email, String password) {
        if (id < 0) {
            throw new IllegalArgumentException();
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
public class Program {
    public static void main(String[] args) {
        Set<User> users = new HashSet<User>();
        users.add(new User(1, "Nursultan", "nuriktoop777@gmail.com", "nurs123"));
        users.add(new User(2, "Aidos", "aidos112@gmail.com", "Aidos123"));
        User newUser = new User(1, "Nursultan", "nuriktoop777@gmail.com", "Nursultan123");
        if (users.contains(newUser)) {
            System.out.println("Такой юзер уже существует");
        }
        else users.add(newUser);


        for (User user : users) {
            System.out.println(user);
        }
    }
}
