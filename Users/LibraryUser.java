package Users;
import java.util.ArrayList;
import Items.Item;

import java.util.List;
import java.util.Random;

public class LibraryUser {
    private String firstName;
    private String lastName;
    private String email;
    private Integer passCode;
    private ArrayList<Item> checkedOutItems;

    public LibraryUser(String firstName, String lastName, String email, Integer passCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passCode = passCode;
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



}
