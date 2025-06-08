package CollectionsEx.Ex2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        // Создаем ArrayList для книг
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Book 1", "Test1", 2000));
        books.add(new Book("Book 2", "Author 2", 2024));
        books.add(new Book("Book 3", "Author 3", 2025));

        // 1. Выводим все книги
        System.out.println("Все книги:");
        printAllBooks(books);

        // 2. Добавляем новую книгу
        System.out.println("\nДобавляем книгу 'Nurs':");
        addBook(books, "Nurs", "Test1", 2024);
        printAllBooks(books);

        // 3. Удаляем книгу по названию
        System.out.println("\nПосле удаления 'Book 1':");
        removeBookByTitle(books, "Book 1");
        printAllBooks(books);

        // 4. Находим самую новую книгу
        System.out.println("\nСамая новая книга:");
        Book latestBook = findLatestBook(books);
        System.out.println(latestBook != null ? latestBook : "Список пуст");

        // 5. Сортируем книги по году
        System.out.println("\nКниги, отсортированные по году:");
        sortBooksByYear(books);
        printAllBooks(books);

        // 6. Фильтруем книги по автору
        System.out.println("\nКниги автора 'Author 2':");
        List<Book> authorBooks = filterBooksByAuthor(books, "Author 2");
        printAllBooks(authorBooks);
    }

    // Метод для вывода списка книг
    public static void printAllBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("Список пуст");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // Метод для добавления книги (проверка на уникальность)
    public static void addBook(ArrayList<Book> books, String title, String author, int year) {
        Book newBook = new Book(title, author, year);
        if (books.contains(newBook)) {
            System.out.println("Книга уже существует: " + newBook);
        } else {
            books.add(newBook);
            System.out.println("Книга добавлена: " + newBook);
        }
    }

    // Метод для удаления книги по названию
    public static void removeBookByTitle(ArrayList<Book> books, String title) {
        boolean removed = books.removeIf(book -> book.getTitle().equals(title));
        if (!removed) {
            System.out.println("Книга с названием '" + title + "' не найдена");
        }
    }


    // Метод для поиска самой новой книги
    public static Book findLatestBook(ArrayList<Book> books) {
        if (books.isEmpty()) {
            return null;
        }
        return Collections.max(books);
    }

    // Метод для сортировки книг по году
    public static void sortBooksByYear(ArrayList<Book> books) {
        Collections.sort(books);
    }

    // Метод для фильтрации книг по автору
    public static List<Book> filterBooksByAuthor(ArrayList<Book> books, String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                result.add(book);
            }
        }
        return result;
    }
}

class Book implements Comparable<Book> {
    private String title;
    private String author;
    private int year;

    // Конструктор
    public Book(String title, String author, int year) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (author == null || author.isEmpty()) {
            throw new IllegalArgumentException("Author cannot be null or empty");
        }
        if (year <= 0 || year > 2025) {
            throw new IllegalArgumentException("Year must be between 1 and 2025");
        }
        this.title = title;
        this.author = author;
        this.year = year;
    }

    // Геттеры
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    // Сеттеры
    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.title = title;
    }

    public void setAuthor(String author) {
        if (author == null || author.isEmpty()) {
            throw new IllegalArgumentException("Author cannot be null or empty");
        }
        this.author = author;
    }

    public void setYear(int year) {
        if (year <= 0 || year > 2025) {
            throw new IllegalArgumentException("Year must be between 1 and 2025");
        }
        this.year = year;
    }

    // Переопределяем toString
    @Override
    public String toString() {
        return "Book{title='" + title + "', author='" + author + "', year=" + year + "}";
    }

    // Переопределяем equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return year == book.year && title.equals(book.title) && author.equals(book.author);
    }

    // Переопределяем hashCode
    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + year;
        return result;
    }

    // Реализуем Comparable для сортировки по году
    @Override
    public int compareTo(Book other) {
        return Integer.compare(this.year, other.year);
    }
}