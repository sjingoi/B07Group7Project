package com.example.b07group7project;

public class Account {
    String email;
    String accounttype;

    public Account() {

    }

    public Account(String email, String accounttype) {
        this.accounttype = accounttype;
        this.email = upTo(email);


    }

    public String upTo(String email) {
        if (email.contains("@")) {
            return email.substring(0, email.indexOf("@"));
        }
        return email;
    }
}
