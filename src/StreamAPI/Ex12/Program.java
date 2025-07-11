package StreamAPI.Ex12;


class Account<T> {
    private T id;
    private String email;
    private String password;
    Account(T id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }


    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
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
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

class Box<G> {
    private G box;

    Box(G box) {
        this.box = box;
    }
    public G get() {
        return box;
    }

    public void set(G value) {
        this.box = value;
    }


    public void printType() {
        System.out.println(box.getClass());
    }
}
public class Program {
    public static void main(String[] args) {
        Account<Integer> account = new Account<>(1, "nuriktoop777@gmail.com", "nurs123");
        System.out.println(account);

        Box<Integer> box = new Box<>(4);
        box.printType();
    }
}
