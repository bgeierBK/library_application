package UIs;
import Items.Book;
import Items.DVD;
import LibraryStock.LibraryStock;
import Users.LibraryUser;

import java.io.IOException;
import java.util.Scanner;


public class TextUI {
    private String name;
    private LibraryStock libraryStock;
    private Scanner scanner;

    public TextUI(String name) {
        this.name = name;
        libraryStock = new LibraryStock();
        scanner = new Scanner(System.in);
    }

    public void display() throws IOException {
        libraryStock.addDVDsFromFile();
        libraryStock.addBooksFromFile();
        libraryStock.addUsersFromFile();
        while(true) {
            System.out.println("Welcome to the " + name + " user interface. This program will let you complete all library functions");
            System.out.println("This is the main menu. Select which action you want to perform.");
            System.out.println("1. Add Book");
            System.out.println("2. Add DVD");
            System.out.println("3. Add User");
            System.out.println("4. View all Books");
            System.out.println("5. View all DVDs");
            System.out.println("6. Checkout Book");
            System.out.println("7. Checkout DVD");
            System.out.println("8. Return Book");
            System.out.println("9. Return DVD");
            System.out.println("10. Exit");
            Integer input = Integer.parseInt(scanner.nextLine());
            if(input == 10) {
                libraryStock.saveBooksToFile();
                libraryStock.saveDVDsToFile();
                libraryStock.saveUsersToFile();
                return;
            } else if (input == 1) {
                System.out.println("Name of book?");
                String title = scanner.nextLine();
                boolean bookExists = false;
                for (Book book : libraryStock.getBooks()) {
                    if (book.getTitle().equalsIgnoreCase(title)){

                        System.out.println("A book with that title already exists. Would you like to add another copy? Yes/No");
                        String answer = scanner.nextLine();
                        if (answer.equalsIgnoreCase("yes")) {
                            bookExists = true;
                            System.out.println("Thanks! Returning to main menu.");
                            book.increaseQuantity();
                        } else if (answer.equalsIgnoreCase("no")) {
                            System.out.println("Ok. Returning ot main menu");
                            bookExists = true;

                        }else {
                            System.out.println("That is not a valid option. Returning to main menu.");
                            bookExists = true;

                        }
                        break;

                    }
                }
                if (!bookExists) {
                    System.out.println("Enter the year of publication");
                    Integer publishedYear = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter the genre");
                    String genre = scanner.nextLine();
                    System.out.println("Enter the author");
                    String author = scanner.nextLine();
                    System.out.println("Enter the number of pages.");
                    Integer pages = Integer.parseInt(scanner.nextLine());
                    libraryStock.addBook(new Book(title, 1, publishedYear, genre, author, pages));
                    System.out.println("Book added successfully");
                }
                System.out.println("You will be returned to the main menu");
                continue;


            } else if (input == 2) {
                System.out.println("Name of DVD?");
                String title = scanner.nextLine();
                boolean dvdExists = false;
                for(DVD dvd: libraryStock.getDvds()){
                    if(dvd.getTitle().equalsIgnoreCase(title)){
                        System.out.println("A dvd with that title already exists. Would you like to add another copy? Yes/No");
                        String answer = scanner.nextLine();
                        if (answer.equalsIgnoreCase("yes")) {
                            dvdExists = true;
                            System.out.println("Thanks! Returning to main menu.");
                            dvd.increaseQuantity();
                        }else if(answer.equalsIgnoreCase("no")) {
                            System.out.println("Ok. Returning ot main menu");
                            dvdExists = true;
                        }else{
                            System.out.println("That is not a valid option. Returning to main menu.");
                        }
                        break;
                    }
                    if(!dvdExists) {
                        System.out.println("Enter the year of publication");
                        Integer publishedYear = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter the genre");
                        String genre = scanner.nextLine();
                        System.out.println("Enter the director");
                        String director = scanner.nextLine();
                        System.out.println("Enter the length in minutes");
                        Integer lengthInMinutes = Integer.parseInt(scanner.nextLine());
                        libraryStock.addDVD(new DVD(title, 1, publishedYear, genre, director, lengthInMinutes));
                        System.out.println("DVD added successfully");
                    }
                    System.out.println("You will be returned to the main menu");
                    continue;
                }
            }else if(input == 3) {
                System.out.println("User's first name?");
                String firstName = scanner.nextLine();
                System.out.println("User's last name?");
                String lastName = scanner.nextLine();
                System.out.println("User's email?");
                String email = scanner.nextLine();
                boolean userExists = false;
                for (LibraryUser user: libraryStock.getUsers()){
                    if (user.getEmail().equalsIgnoreCase(email)){
                        userExists = true;
                        System.out.println("A user with that email already exists. Returning you to the main menu");
                        break;
                    }
                }
                if(!userExists) {
                    System.out.println("Enter a 4-digit pin code");
                    Integer passcode = Integer.parseInt(scanner.nextLine());
                    libraryStock.addUser(new LibraryUser(firstName, lastName, email, passcode));
                    System.out.println("User added successfully");
                }
                System.out.println("You will be returned to the main menu");
                continue;
            }
        }
    }
}
