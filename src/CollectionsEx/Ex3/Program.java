package CollectionsEx.Ex3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Program {
    public static void main(String[] args) {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Shawshank Redemption", "Drama", 9.3));
        movies.add(new Movie("The Hangover", "Comedy", 7.7));
        movies.add(new Movie("Inception", "Sci-Fi", 8.8));
        movies.add(new Movie("Home Alone", "Comedy", 7.6));
        findTopRatedMovie(movies);

    }

    public static void addMovie(ArrayList<Movie> movies, String title, String genre, double rating) {
        if (genre == null || genre.isEmpty()) {
            throw new IllegalArgumentException("No genre found");
        }
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("No title found");
        }
        if (rating < 0 || rating > 10.0) {
            throw new IllegalArgumentException("Invalid rating");
        }

        Movie newMovie = new Movie(title, genre, rating);
        if (movies.contains(newMovie.getTitle()) && movies.contains(newMovie.getGenre())) {
            movies.add(newMovie);
        } else {
            throw new IllegalArgumentException("Уже имеется данный жанр или данный фильм");
        }
    }
    public static void removeMovieByTitle(ArrayList<Movie> movies, String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Не может быть пусто");
        }
        for (Movie movie : movies) {
            if (movie.getTitle().equals(title)) {
                movies.remove(movie);
            }
            else {
                throw new IllegalArgumentException("Нет такого фильма");
            }

        }
    }

    public static void findTopRatedMovie(ArrayList<Movie> movies) {
        Collections.sort(movies, Collections.reverseOrder());
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }
    public static List<Movie> filterMoviesByGenre(ArrayList<Movie> movies, String genre) {
        if (genre == null || genre.isEmpty()) {
            throw new IllegalArgumentException("Не может быть пусто");
        }
        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getGenre().equals(genre)) {
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }

    public static List<Movie> getMoviesAboveRating(ArrayList<Movie> movies, double rating) {
        List<Movie> result = new ArrayList<>();
        if (rating < 0) {
            throw new IllegalArgumentException("Рейтинг не может ниже 0");
        }
        for (Movie movie : movies) {
            if (movie.getRating() > rating) {
                result.add(movie);
            }         }
        return result;
    }

    public static void swapMovies(ArrayList<Movie> movies, int index1, int index2) {
        Movie temp = movies.get(index1);
        movies.set(index1, movies.get(index2));
        movies.set(index2, temp);
    }

}

class Movie implements Comparable<Movie> {
    private String title;
    private String genre;
    private double rating;

    public Movie(String title, String genre, double rating) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (genre == null || genre.isEmpty()) {
            throw new IllegalArgumentException("Genre cannot be null or empty");

        }
        if (rating < 0 || rating > 10.0) {
            throw new IllegalArgumentException("Rating cannot be null or empty");
        }
        this.title = title;
        this.genre = genre;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }


    public void setGenre(String genre) {
        if (genre == null || genre.isEmpty()) {
            throw new IllegalArgumentException("Genre cannot be null or empty");
        }
        this.genre = genre;
    }


    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        if (rating < 0 || rating > 100.0) {
            throw new IllegalArgumentException("Rating cannot be null or empty");
        }
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "title: " + title + ", genre: " + genre + ", rating: " + rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movie movie = (Movie) o;
        return rating == movie.rating && title.equals(movie.title) && genre.equals(movie.genre);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + genre.hashCode();
        result = 31 * result + (int) rating;
        return result;
    }

    @Override
    public int compareTo(Movie o) {
        return Double.compare(this.rating, o.rating);
    }
}
