package Items;

public class DVD extends Item {
    String director;
    Integer minutes;


    public DVD(String name, int quantity, int publishedYear, String genre, String director, Integer minutes) {
        super(name, quantity, publishedYear, genre);
        this.director = director;
        this.minutes = minutes;
    }



    public String getDirector() {
        return director;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public String toCSV() {
        return String.format("%s,%d,%d,%s,%s,%d",
                getTitle(),
                getQuantity(),
                getPublishedYear(),
                getGenre(),
                director,
                minutes);
    }

    public String toString() {
        return this.getTitle() + " directed by: " + director;
    }
}


