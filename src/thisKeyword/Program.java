package thisKeyword;

public class Program {
    public static void main(String[] args) {
        Book book = new Book("Abai zholy", "Mukhtar Auezov");
        book.PrintInfo();
    }
}

class Book {
    private String title;
   private String author;

    public Book(String author, String title) {
        this.author = author;
        this.title = title;
    }

    protected void PrintInfo() {
        System.out.println("Title: " + this.title);
        System.out.println("Author: " + this.author);
    }
}