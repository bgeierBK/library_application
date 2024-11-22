package LibraryStock;
import Items.Book;
import Items.DVD;
import Users.LibraryUser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LibraryStock {
    static ArrayList<Book> books;
    static ArrayList<DVD> dvds;
    ArrayList<LibraryUser> users;


    public LibraryStock() {
        books = new ArrayList<>();
        dvds = new ArrayList<>();
        users = new ArrayList<>();
    }

    public static ArrayList<Book> getBooks() {
        return books;
    }
    public static ArrayList<DVD> getDVDs() {
        return dvds;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addDVD(DVD dvd) {
        dvds.add(dvd);
    }

    public ArrayList<LibraryUser> getUsers() {
        return users;
    }

    public void addUser(LibraryUser user) {
        users.add(user);
    }

    public void addDVDsFromFile() throws IOException {
       try (Scanner scanner = new Scanner(Paths.get("DVDs"))){
           while (scanner.hasNextLine()) {
               String line = scanner.nextLine();
               String[] splitLine = line.split(",");
               String title = splitLine[0];
               Integer quantity = Integer.valueOf(splitLine[1]);
               Integer publishedYear = Integer.valueOf(splitLine[2]);
               String genre = splitLine[3];
               String director = splitLine[4];
               Integer minutes = Integer.valueOf(splitLine[5]);
               DVD dvd = new DVD(title, quantity, publishedYear, genre, director, minutes);
               addDVD(dvd);
           }
       }catch (IOException e){
           System.out.println(e.getMessage());
       }
    }

    public void saveDVDsToFile() throws IOException {
        Path path = Paths.get("DVDs");
        try{
            List<String> lines = dvds.stream().map(DVD::toCSV).toList();
            Files.write(path, lines);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void addBooksFromFile() throws IOException {
        try (Scanner scanner = new Scanner(Paths.get("Books"))){
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splitLine = line.split(",");
                String title = splitLine[0];
                Integer quantity = Integer.valueOf(splitLine[1]);
                Integer publishedYear = Integer.valueOf(splitLine[2]);
                String genre = splitLine[3];
                String author = splitLine[4];
                Integer pages = Integer.valueOf(splitLine[5]);
                Book book = new Book(title, quantity, publishedYear, genre, author, pages);
                addBook(book);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void saveBooksToFile() throws IOException {
        Path path = Paths.get("Books");
        try{
            List<String> lines = books.stream().map(Book::toCSV).toList();
            Files.write(path, lines);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void addUsersFromFile() throws IOException {
        try (Scanner scanner = new Scanner(Paths.get("LibraryUsers"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splitLine = line.split(",");
                String firstName = splitLine[0];
                String lastName = splitLine[1];
                String email = splitLine[2];
                Integer passcode = Integer.valueOf(splitLine[3]);
                Boolean isAdmin = Boolean.valueOf(splitLine[4]);

                // Create a new LibraryUser object
                LibraryUser toAdd = new LibraryUser(firstName, lastName, email, passcode, isAdmin);

                // Load checked-out books
                if (splitLine.length > 5 && !splitLine[5].isEmpty()) {
                    String[] bookTitles = splitLine[5].split("\\|");
                    for (String title : bookTitles) {
                        for (Book book : LibraryStock.getBooks()) {
                            if (book.getTitle().equalsIgnoreCase(title)) {
                                toAdd.checkOutBook(book);
                            }
                        }
                    }
                }

                // Load checked-out DVDs
                if (splitLine.length > 6 && !splitLine[6].isEmpty()) {
                    String[] dvdTitles = splitLine[6].split("\\|");
                    for (String title : dvdTitles) {
                        for (DVD dvd : LibraryStock.getDVDs()) {
                            if (dvd.getTitle().equalsIgnoreCase(title)) {
                                toAdd.checkOutDVD(dvd);
                            }
                        }
                    }
                }

                addUser(toAdd);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUsersToFile() throws IOException {
        Path path = Paths.get("LibraryUsers");
        try {
            List<String> lines = users.stream().map(user -> {
                String books = user.getCheckedOutBooks().stream()
                        .map(Book::getTitle)
                        .reduce((b1, b2) -> b1 + "|" + b2)
                        .orElse(""); // Join book titles with '|'
                String dvds = user.getCheckedOutDVDs().stream()
                        .map(DVD::getTitle)
                        .reduce((d1, d2) -> d1 + "|" + d2)
                        .orElse(""); // Join DVD titles with '|'

                // Include user data along with checked-out items
                return String.format("%s,%s,%s,%d,%b,%s,%s",
                        user.getFirstName(), user.getLastName(),
                        user.getEmail(), user.getPassCode(),
                        user.getIsAdmin(), books, dvds);
            }).toList();

            Files.write(path, lines);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public void checkOutBooks(LibraryUser user, Book book) throws IOException {
        if (book.getQuantity() > 0) {
            user.checkOutBook(book);
            book.decreaseQuantity();
        }
        else{
            System.out.println("No copies available");
        }
    }

    public void checkOutDVD(LibraryUser user, DVD dvd) throws IOException {
        if (dvd.getQuantity() > 0) {
            user.checkOutDVD(dvd);
            dvd.decreaseQuantity();
        }
        else{
            System.out.println("No copies available");
        }
    }




}
