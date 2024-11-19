import Items.Book;
import Items.DVD;
import Items.Item;
import LibraryStock.LibraryStock;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        LibraryStock libraryStock = new LibraryStock();
        libraryStock.addDVDsFromFile();
        DVD toAdd = new DVD("Inception", 2, 2010, "Sci-Fi", "Christopher Nolan", 195 );
        libraryStock.addDVD(toAdd);
        libraryStock.saveDVDsToFile();
        System.out.println(libraryStock.getDvds());

    }

}
