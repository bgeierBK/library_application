package Users;
import java.util.ArrayList;
import Items.Item;
import Items.Book;
import Items.DVD;
import java.util.List;

public class LibraryUser {
    private String firstName;
    private String lastName;
    private String email;
    private Integer passCode;
    private boolean isadmin;
    private ArrayList<Book> checkedOutBooks;
    private ArrayList<DVD> checkedOutDVDs;

    public LibraryUser(String firstName, String lastName, String email, Integer passCode, boolean isAdmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passCode = passCode;
        this.isadmin = isAdmin;
        this.checkedOutBooks = new ArrayList<>();
        this.checkedOutDVDs = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public List<Book> getCheckedOutBooks() {
        return checkedOutBooks;
    }

    public List<DVD> getCheckedOutDVDs() {
        return checkedOutDVDs;
    }

    public Boolean getIsAdmin() {
        return this.isadmin;
    }

    public void switchIsAdmin(Boolean isAdmin) {
        this.isadmin = !isAdmin;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getPassCode() {
        return passCode;
    }
    public void setPassCode(Integer passCode) {
        this.passCode = passCode;
    }

    public void checkOutBook(Book book) {
        checkedOutBooks.add(book);

    }

    public void returnBook(Book book) {
        checkedOutBooks.remove(book);
        book.increaseQuantity();

    }

    public void checkOutDVD(DVD dvd) {
        checkedOutDVDs.add(dvd);

    }

    public void returnDVD(DVD dvd) {
        checkedOutDVDs.remove(dvd);
        dvd.increaseQuantity();

    }

    public String toString(){
        return this.firstName + " " + this.lastName;
    }

    public String toCSV() {
        StringBuilder csvLine = new StringBuilder();
        csvLine.append(firstName)
                .append(",")
                .append(lastName)
                .append(",")
                .append(email)
                .append(",")
                .append(passCode)
                .append(",")
                .append(isadmin);


        if (checkedOutBooks.isEmpty()) {
            csvLine.append(",");
        } else {
            String booksCSV = String.join(",", checkedOutBooks.stream().map(Book::getTitle).toList());
            csvLine.append(",").append(booksCSV);
        }

        return csvLine.toString();
    }



}
