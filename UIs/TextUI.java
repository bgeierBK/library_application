package UIs;
import Items.Book;
import Items.DVD;
import LibraryStock.LibraryStock;
import Users.LibraryUser;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;


public class TextUI {
    private String name;
    private LibraryStock libraryStock;
    private Scanner scanner;
    LibraryUser activeUser;

    public TextUI(String name) {
        this.name = name;
        libraryStock = new LibraryStock();
        scanner = new Scanner(System.in);
        LibraryUser activeUser = null;
    }

    public void display() throws IOException {
        libraryStock.addDVDsFromFile();
        libraryStock.addBooksFromFile();
        libraryStock.addUsersFromFile();

        System.out.println("Welcome to the " + name + " user interface. This program will let you complete all library functions");
            boolean authenticated = false;
            while (!authenticated) {
                System.out.println("Enter your e-mail address");
                String email = scanner.nextLine();
                System.out.println("Enter your passcode");
                Integer passcode = Integer.valueOf(scanner.nextLine());
                for (LibraryUser user : libraryStock.getUsers()) {
                    if (user.getEmail().equalsIgnoreCase(email) && Objects.equals(user.getPassCode(), passcode)) {
                        activeUser = user;
                        System.out.println("Welcome " + activeUser.getFirstName() + "!");
                        authenticated = true;
                        break;
                    }
                }
                if (!authenticated) {
                    System.out.println("That is not a valid user. Please try again");
                }
            }

        assert activeUser != null;
        if (activeUser.getIsAdmin()) {
            while (true) {

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
                System.out.println("10. List my checked out books");
                System.out.println("11. List my checked out DVDs");
                System.out.println("12. Exit");
                Integer input = Integer.parseInt(scanner.nextLine());
                if (input == 12) {
                    this.close();
                    return;
                } else if (input == 1) {
                    System.out.println("Name of book?");
                    String title = scanner.nextLine();
                    boolean bookExists = false;
                    for (Book book : LibraryStock.getBooks()) {
                        if (book.getTitle().equalsIgnoreCase(title)) {

                            System.out.println("A book with that title already exists. Would you like to add another copy? Yes/No");
                            String response = scanner.nextLine();
                            if (response.equalsIgnoreCase("yes")) {
                                bookExists = true;
                                System.out.println("Thanks! Returning to main menu.");
                                book.increaseQuantity();
                            } else if (response.equalsIgnoreCase("no")) {
                                System.out.println("Ok. Returning ot main menu");
                                bookExists = true;

                            } else {
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
                    for (DVD dvd : libraryStock.getDVDs()) {
                        if (dvd.getTitle().equalsIgnoreCase(title)) {
                            System.out.println("A dvd with that title already exists. Would you like to add another copy? Yes/No");
                            String reply = scanner.nextLine();
                            if (reply.equalsIgnoreCase("yes")) {
                                dvdExists = true;
                                System.out.println("Thanks! Returning to main menu.");
                                dvd.increaseQuantity();
                            } else if (reply.equalsIgnoreCase("no")) {
                                System.out.println("Ok. Returning to main menu");
                                dvdExists = true;
                            } else {
                                System.out.println("That is not a valid option. Returning to main menu.");
                            }
                            break;
                        }
                        if (!dvdExists) {
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
                } else if (input == 3) {
                    System.out.println("User's first name?");
                    String firstName = scanner.nextLine();
                    System.out.println("User's last name?");
                    String lastName = scanner.nextLine();
                    System.out.println("User's email?");
                    String email = scanner.nextLine();
                    boolean userExists = false;
                    for (LibraryUser user : libraryStock.getUsers()) {
                        if (user.getEmail().equalsIgnoreCase(email)) {
                            userExists = true;
                            System.out.println("A user with that email already exists. Returning you to the main menu");
                            break;
                        }
                    }
                    if (!userExists) {
                        System.out.println("Enter a 4-digit pin code");
                        Integer passcode = Integer.parseInt(scanner.nextLine());
                        libraryStock.addUser(new LibraryUser(firstName, lastName, email, passcode, false));
                        System.out.println("User added successfully");
                    }
                    System.out.println("You will be returned to the main menu");
                    continue;
                } else if (input == 4) {
                    libraryStock.printBooks();
                } else if (input == 5) {
                    libraryStock.printDVDs();
                } else if (input == 6) {
                    this.checkOutBook();
                }else if(input == 7) {
                   this.checkOutDVD();
                }else if (input == 8) {
                    this.returnBook();
                }else if (input == 9) {
                    this.returnDVD();
                } else if (input == 10) {
                    this.listBooks();
                }else if (input == 11) {
                   this.listDVDs();
                }
            }
        }else if(!activeUser.getIsAdmin()){
            while (true){
            System.out.println("This is the main menu. Select which action you want to perform.");
            System.out.println("1. View all Books");
            System.out.println("2. View all DVDs");
            System.out.println("3. Checkout Book");
            System.out.println("4. Checkout DVD");
            System.out.println("5. Return Book");
            System.out.println("6. Return DVD");
            System.out.println("7. List my checked out books");
            System.out.println("8. List my checked out DVDs");
            System.out.println("9. Exit");
            Integer input = Integer.valueOf(scanner.nextLine());
            if (input == 9) {
                this.close();
                return;
            }else if (input == 1) {
                libraryStock.printBooks();
            }else if (input == 2) {
                libraryStock.printDVDs();
            }else if (input == 3) {
                this.checkOutBook();
            }else if (input == 4) {
                this.checkOutDVD();
            }else if (input == 5) {
                this.returnBook();
            }else if (input == 6) {
                this.returnDVD();
            }else if (input == 7) {
                this.listBooks();
            }else if(input == 8){
                this.listDVDs();
            }
            }
        }
        }

        public void close() throws IOException {
            libraryStock.saveBooksToFile();
            libraryStock.saveDVDsToFile();
            libraryStock.saveUsersToFile();
        }

        public void checkOutBook() throws IOException {
            System.out.println("Enter the title of the book");
            String title = scanner.nextLine();
            boolean bookExists = false;
            Book bookToCheckOut = null;


            while (true) {

                for (Book book : LibraryStock.getBooks()) {
                    if (book.getTitle().equalsIgnoreCase(title)) {
                        bookExists = true;
                        bookToCheckOut = book;
                        break;
                    }
                }


                if (bookExists) {
                    if (bookToCheckOut.getQuantity() > 0) {
                        libraryStock.checkOutBooks(activeUser, bookToCheckOut);
                        System.out.println("You have successfully checked out: " + bookToCheckOut.getTitle());
                        break;
                    }else{
                        System.out.println("There are no copies of that book currently available. Try searching for another book");
                        title = scanner.nextLine();
                    }
                } else {
                    System.out.println("That book is not in the library. Please try again.");
                    title = scanner.nextLine();
                }
            }
        }

        public void checkOutDVD() throws IOException {
            System.out.println("Enter the title of the DVD");
            String title = scanner.nextLine();
            boolean DVDExists = false;
            DVD DVDToCheckOut = null;


            while (true) {

                for (DVD dvd : LibraryStock.getDVDs()) {
                    if (dvd.getTitle().equalsIgnoreCase(title)) {
                        DVDExists = true;
                        DVDToCheckOut = dvd;
                        break;
                    }
                }


                if (DVDExists) {
                    if (DVDToCheckOut.getQuantity() > 0) {
                        libraryStock.checkOutDVD(activeUser, DVDToCheckOut);
                        System.out.println("You have successfully checked out: " + DVDToCheckOut.getTitle());
                        break;
                    }else{
                        System.out.println("There are no copies of that DVD currently available. Try searching for another DVD");
                        title = scanner.nextLine();
                    }
                } else {
                    System.out.println("That DVD is not in the library. Please try again.");
                    title = scanner.nextLine();
                }
            }
        }

        public void returnBook() throws IOException {
            System.out.println("Enter the title of the Book you are returning");
            String title = scanner.nextLine();
            boolean bookExists = false;
            Book bookToCheckIn = null;
            while (!bookExists) {
                for (Book book : activeUser.getCheckedOutBooks()) {
                    if (book.getTitle().equalsIgnoreCase(title)) {
                        bookExists = true;
                        bookToCheckIn = book;
                        break;
                    }

                }
                if (!bookExists) {
                    System.out.println("You don't have that book checked out. Try again");
                    title = scanner.nextLine();
                }
            }
            activeUser.returnBook(bookToCheckIn);
            System.out.println("Book returned successfully");
        }

        public void returnDVD() throws IOException {
            System.out.println("Enter the title of the DVD you are returning");
            String title = scanner.nextLine();
            boolean DVDExists = false;
            DVD DVDToCheckIn = null;
            while (!DVDExists) {
                for (DVD DVD : activeUser.getCheckedOutDVDs()) {
                    if (DVD.getTitle().equalsIgnoreCase(title)) {
                        DVDExists = true;
                        DVDToCheckIn = DVD;
                        break;
                    }

                }
                if (!DVDExists) {
                    System.out.println("You don't have that DVD checked out. Try again");
                    title = scanner.nextLine();
                }
            }
            activeUser.returnDVD(DVDToCheckIn);
            System.out.println("DVD returned successfully");
        }

        public void listBooks() throws IOException {
            if (activeUser.getCheckedOutBooks().isEmpty()) {
                System.out.println("You have no checked out books");
            }else {
                System.out.println("Here are your checked out Books");
                activeUser.printCheckedOutBooks();
            }
        }

        public void listDVDs() throws IOException {
            if (activeUser.getCheckedOutDVDs().isEmpty()) {
                System.out.println("You have no checked out DVDs");
            }else {
                System.out.println("Here are your checked out DVDs");
                activeUser.printCheckedOutDVDs();
            }
            }



    }





