package CollectionsEx.Ex7;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Post {
    private String title;
    private String author;
    private int likes;



    public Post(String title, String author, int likes) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (author == null || author.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (likes <= 0) {
            throw new IllegalArgumentException();
        }
        this.title = title;
        this.author = author;
        this.likes = likes;
    }

    public String getTitile() {
        return title;
    }

    public void setTitile(String titile) {
        this.title = titile;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void increaseLikes() {
        this.likes++;
    }
    public void decreaseLikes() {
        this.likes--;
    }


    @Override
    public String toString() {
        return "Post{" +
                "titile='" + title + '\'' +
                ", author='" + author + '\'' +
                ", likes=" + likes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return likes == post.likes && Objects.equals(title, post.title) && Objects.equals(author, post.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, likes);
    }
}
public class Program {
    public static void main(String[] args) {
        Map<String, Post> posts = new HashMap<>();
        posts.put("Hello Kazakshatan", new Post("About KZ", "Nursutlan", 30));
        posts.put("delicious", new Post("Global Coffee", "Ers", 40));
        posts.put("cake is yummy", new Post("Caramel", "Nurbak", 50));
        posts.put("good cartoon", new Post("Tom and Jerry", "Jerry", 60));


        System.out.println(posts.size());

        posts.values().forEach(System.out::println);
        posts.keySet().forEach(System.out::println);
        posts.get("delicious").increaseLikes();

        for (Map.Entry<String, Post> entry : posts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }


        System.out.println("Увеличь лайки у одного из постов");

        System.out.println(posts.get("delicious"));

    }
}
