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
    ArrayList<Book> books;
    ArrayList<DVD> dvds;
    ArrayList<LibraryUser> users;


    public LibraryStock() {
        books = new ArrayList<>();
        dvds = new ArrayList<>();
        users = new ArrayList<>();
    }

    public ArrayList<Book> getBooks() {
        return books;
    }
    public ArrayList<DVD> getDvds() {
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
        try (Scanner scanner = new Scanner(Paths.get("LibraryUsers"))){
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splitLine = line.split(",");
                String firstName = splitLine[0];
                String lastName = splitLine[1];
                String email = splitLine[2];
                Integer passcode = Integer.valueOf(splitLine[3]);
                LibraryUser toAdd = new LibraryUser(firstName, lastName, email, passcode);
                addUser(toAdd);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void saveUsersToFile() throws IOException {
        Path path = Paths.get("LibraryUsers");
        try{
            List<String> lines = users.stream().map(LibraryUser::toCSV).toList();
            Files.write(path, lines);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void checkOutBooks(LibraryUser user, Book book) throws IOException {
        if (book.getQuantity() > 0) {
            user.checkOutItem(book);
            book.decreaseQuantity();
        }
        else{
            System.out.println("No copies available");
        }
    }

    public void checkOutDVD(LibraryUser user, DVD dvd) throws IOException {
        if (dvd.getQuantity() > 0) {
            user.checkOutItem(dvd);
            dvd.decreaseQuantity();
        }
        else{
            System.out.println("No copies available");
        }
    }




}
