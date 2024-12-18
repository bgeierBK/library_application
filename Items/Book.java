package Items;

public class Book extends Item {
    String author;
    Integer pages;


    public Book(String title, int quantity, int publishedYear, String genre, String author, Integer pages) {
        super(title, quantity, publishedYear, genre);
        this.author = author;
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }
    public Integer getPages() {
        return pages;
    }



    public void setAuthor(String author) {
        this.author = author;
    }
    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String toCSV() {
        return String.format("%s,%d,%d,%s,%s,%d",
                getTitle(),
                getQuantity(),
                getPublishedYear(),
                getGenre(),
                author,
                pages);
    }

    public String toString() {
        return this.getTitle() + " by " + author;
    }

}
