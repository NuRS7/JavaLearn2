package CollectionsEx.Ex3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        // Создаем ArrayList для фильмов
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Shawshank Redemption", "Drama", 9.3));
        movies.add(new Movie("The Hangover", "Comedy", 7.7));
        movies.add(new Movie("Inception", "Sci-Fi", 8.8));
        movies.add(new Movie("Home Alone", "Comedy", 7.6));

        // 1. Выводим все фильмы
        System.out.println("Все фильмы:");
        printMovies(movies);

        // 2. Добавляем новый фильм
        System.out.println("\nДобавляем фильм 'Interstellar':");
        addMovie(movies, "Interstellar", "Sci-Fi", 8.6);
        printMovies(movies);

        // 3. Удаляем фильм по названию
        System.out.println("\nПосле удаления 'Home Alone':");
        removeMovieByTitle(movies, "Home Alone");
        printMovies(movies);

        // 4. Находим фильм с наивысшим рейтингом
        System.out.println("\nФильм с наивысшим рейтингом:");
        Movie topMovie = findTopRatedMovie(movies);
        System.out.println(topMovie != null ? topMovie : "Список пуст");

        // 5. Фильтруем фильмы по жанру
        System.out.println("\nФильмы жанра 'Comedy':");
        List<Movie> comedies = filterMoviesByGenre(movies, "Comedy");
        printMovies(comedies);

        // 6. Фильтруем фильмы с рейтингом выше 8.0
        System.out.println("\nФильмы с рейтингом выше 8.0:");
        List<Movie> highRated = getMoviesAboveRating(movies, 8.0);
        printMovies(highRated);

        // 7. Меняем местами фильмы по индексам
        System.out.println("\nПосле смены местами фильмов на индексах 0 и 2:");
        swapMovies(movies, 0, 2);
        printMovies(movies);
    }

    // Метод для вывода списка фильмов
    public static void printMovies(List<Movie> movies) {
        if (movies.isEmpty()) {
            System.out.println("Список пуст");
            return;
        }
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    // Метод для добавления фильма
    public static void addMovie(ArrayList<Movie> movies, String title, String genre, double rating) {
        // Проверяем, есть ли фильм с таким названием и жанром
        boolean exists = false;
        for (Movie movie : movies) {
            if (movie.getTitle().equals(title) && movie.getGenre().equals(genre)) {
                exists = true;
                break;
            }
        }
        if (exists) {
            System.out.println("Фильм с названием '" + title + "' и жанром '" + genre + "' уже существует");
        } else {
            try {
                Movie newMovie = new Movie(title, genre, rating);
                movies.add(newMovie);
                System.out.println("Фильм добавлен: " + newMovie);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка добавления фильма: " + e.getMessage());
            }
        }
    }

    // Метод для удаления фильма по названию
    public static void removeMovieByTitle(ArrayList<Movie> movies, String title) {
        if (title == null || title.isEmpty()) {
            System.out.println("Название не может быть пустым");
            return;
        }
        boolean removed = movies.removeIf(movie -> movie.getTitle().equals(title));
        if (!removed) {
            System.out.println("Фильм с названием '" + title + "' не найден");
        }
    }

    // Метод для поиска фильма с наивысшим рейтингом
    public static Movie findTopRatedMovie(ArrayList<Movie> movies) {
        if (movies.isEmpty()) {
            return null;
        }
        return Collections.max(movies);
    }

    // Метод для фильтрации фильмов по жанру
    public static List<Movie> filterMoviesByGenre(ArrayList<Movie> movies, String genre) {
        if (genre == null || genre.isEmpty()) {
            throw new IllegalArgumentException("Жанр не может быть пустым");
        }
        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getGenre().equals(genre)) {
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }

    // Метод для фильтрации фильмов с рейтингом выше заданного
    public static List<Movie> getMoviesAboveRating(ArrayList<Movie> movies, double rating) {
        if (rating < 0.0 || rating > 10.0) {
            throw new IllegalArgumentException("Рейтинг должен быть от 0.0 до 10.0");
        }
        List<Movie> result = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getRating() > rating) {
                result.add(movie);
            }
        }
        return result;
    }

    // Метод для смены местами двух фильмов по индексам
    public static void swapMovies(ArrayList<Movie> movies, int index1, int index2) {
        if (index1 < 0 || index1 >= movies.size() || index2 < 0 || index2 >= movies.size()) {
            throw new IllegalArgumentException("Некорректные индексы");
        }
        Movie temp = movies.get(index1);
        movies.set(index1, movies.get(index2));
        movies.set(index2, temp);
    }
}

class Movie implements Comparable<Movie> {
    private String title;
    private String genre;
    private double rating;

    // Конструктор
    public Movie(String title, String genre, double rating) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Название не может быть пустым");
        }
        if (genre == null || genre.isEmpty()) {
            throw new IllegalArgumentException("Жанр не может быть пустым");
        }
        if (rating < 0.0 || rating > 10.0) {
            throw new IllegalArgumentException("Рейтинг должен быть от 0.0 до 10.0");
        }
        this.title = title;
        this.genre = genre;
        this.rating = rating;
    }

    // Геттеры
    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public double getRating() {
        return rating;
    }

    // Сеттеры
    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Название не может быть пустым");
        }
        this.title = title;
    }

    public void setGenre(String genre) {
        if (genre == null || genre.isEmpty()) {
            throw new IllegalArgumentException("Жанр не может быть пустым");
        }
        this.genre = genre;
    }

    public void setRating(double rating) {
        if (rating < 0.0 || rating > 10.0) {
            throw new IllegalArgumentException("Рейтинг должен быть от 0.0 до 10.0");
        }
        this.rating = rating;
    }

    // Переопределяем toString
    @Override
    public String toString() {
        return "Movie{title='" + title + "', genre='" + genre + "', rating=" + rating + "}";
    }

    // Переопределяем equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.rating, rating) == 0 && title.equals(movie.title) && genre.equals(movie.genre);
    }

    // Переопределяем hashCode
    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + genre.hashCode();
        result = 31 * result + Double.hashCode(rating);
        return result;
    }

    // Реализуем Comparable для сортировки по рейтингу (по убыванию)
    @Override
    public int compareTo(Movie other) {
        return Double.compare(other.rating, this.rating); // По убыванию
    }
}