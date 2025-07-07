package Exercises1.Ex7;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

abstract class Book {
    protected int id;
    protected String title;
    protected String author;
    protected int year;

    public Book(int id, String title, String author, int year) {
        if (id < 0) {
            throw new IllegalArgumentException("ID must be a positive integer");
        }
        if (title.isEmpty() || title == null) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (author.isEmpty() || author == null) {
            throw new IllegalArgumentException("Author cannot be null or empty");
        }
        if (year < 0) {
            throw new IllegalArgumentException("Year must be a positive integer");
        }
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public abstract String getBookType();
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
interface LibraryOperations {
    void addBook(Book book);
    void removeBook(Book book);
    Book findBookById(int id);
}


class FictionBook extends Book {

    private String genre;

    public FictionBook(int id, String title, String author, int year, String genre) {
        super(id, title, author, year);
        this.genre = genre;
    }
    @Override
    public String getBookType() {
        return "Художественная книга";
    }
}

class ScienceBook extends Book {
    private String field;

    public ScienceBook(int id, String title, String author, int year, String field) {
        super(id, title, author, year);
        this.field = field;
    }

    @Override
    public String getBookType() {
        return "Научная книга";
    }
}

class User {
    private int id;
    private String username;
    private List<Book> borrowedBooks = new ArrayList<>();

    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }
    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

}

interface BookFilter {
    boolean test(Book book);
}
class Library implements LibraryOperations {
    private List<Book> books = new ArrayList<>();
    private Set<User> users = new HashSet<>();
    @Override
    public void addBook(Book book) {
        books.add(book);
    }
    public void borrowBook(Book book) {
        books.add(book);
    }
    @Override
    public void removeBook(Book book) {
        books.remove(book);
    }
    public Book findBookById(int id) {
        return books.stream().filter(book -> book.getId() == id).findFirst().orElse(null);
    }

    public List<Book> findBooks(BookFilter bookFilter) {
        return books.stream()
                .filter(bookFilter::test)
                .collect(Collectors.toList());
    }
    public void registerUser(User user) {
        users.add(user);
    }

    List<Book> findBookByAuhor(String author) {
        return books.stream().filter(book -> book.getAuthor().equals(author)).collect(Collectors.toList());
    }

    List<Book> findBookAfterYear(int year) {
        return findBooks(book -> book.getYear() > year);
    }

    List<Book> getBooks() {
        return new ArrayList<>(books);
    }
}

public class Program {
    public static void main(String[] args) {
        Library library = new Library();

        // Добавляем книги
        library.addBook(new FictionBook(1, "1984", "Оруэлл", 1949, "Антиутопия"));
        library.addBook(new ScienceBook(2, "Краткая история времени", "Хокинг", 1988, "Физика"));

        // Регистрируем пользователей
        User user1 = new User(1, "Иван");
        library.registerUser(user1);

        // Поиск с лямбда-функцией
        List<Book> modernBooks = library.findBooks(b -> b.getYear() > 2000);

        // Stream API статистика
        double avgYear = library.getBooks().stream()
                .mapToInt(Book::getYear)
                .average()
                .orElse(0);

        System.out.println("Средний год издания: " + avgYear);
    }
}
