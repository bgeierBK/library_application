import Items.Book;
import Items.DVD;
import Items.Item;
import Users.LibraryUser;
import LibraryStock.LibraryStock;
import UIs.TextUI;

import java.io.IOException;




public class Main {
    public static void main(String[] args) throws IOException {
        TextUI textUI = new TextUI("Ben's Library");

        textUI.display();

    }

}
