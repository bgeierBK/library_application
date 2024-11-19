package Items;

public abstract class Item {
    private String title;
    private int quantity;
    private int publishedYear;
    private String genre;
    private boolean available;

    public Item(String name, int quantity, int publishedYear , String genre) {
        this.title = name;
        this.quantity = quantity;
        this.publishedYear = publishedYear;
        this.genre = genre;
        this.available = true;
    }

    public int getPublishedYear() {
        return publishedYear;
    }
    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity() {
        this.quantity++;
    }

    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isAvailable() {
        return available;
    }
    public void setAvailable() {
        this.available = !this.available;
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", quantity=" + quantity +
                ", publishedYear=" + publishedYear +
                ", genre='" + genre + '\'' +
                ", available=" + available +
                '}';
    }
}
