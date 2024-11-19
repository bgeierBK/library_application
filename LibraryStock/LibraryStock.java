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




}
