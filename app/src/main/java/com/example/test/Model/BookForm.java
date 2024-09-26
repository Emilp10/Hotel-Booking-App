package com.example.test.Model;

import java.util.Date;

public class BookForm {

    public String name;
    public String surname;
    public String contact;
    public String email;
    public String ticketType;

    public BookForm() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public BookForm(String name, String surname, String contact, String email, String ticketType) {

        this.name = name;
        this.surname = surname;
        this.contact = contact;
        this.email = email;
        this.ticketType = ticketType;
    }
}