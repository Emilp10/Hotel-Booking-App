package com.example.test.Model;

import java.util.ArrayList;

public class User {

    public String name, surname, email, password;
    public ArrayList<BookForm> bookForms;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.bookForms = new ArrayList<>();
}
}