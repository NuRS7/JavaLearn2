package CollectionsEx.Ex6;

public class Program {

}

interface LibraryItem {
    public String getTitle();

    public int getPublicationYear();

    public String getIdentifier();
}


abstract class AbstractLibraryItem implements Comparable<LibraryItem> {
    private String title;
    private int publicationYear;
    private String identifier;



}