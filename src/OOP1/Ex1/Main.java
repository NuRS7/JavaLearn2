package OOP1.Ex1;


class A {
    public void job() {
        System.out.println("Class A");

    }
}
    class B extends A {
        public void job(int i) {
            System.out.println("Class B");
        }
    }


public class Main {
    public static void main(String[] args) {
        B b = new B();
        b.job();

    }

}
