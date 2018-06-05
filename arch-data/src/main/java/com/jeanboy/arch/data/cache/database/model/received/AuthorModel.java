package com.jeanboy.arch.data.cache.database.model.received;

/**
 * Created by 乔晓松 on 2018/6/5 15:27
 */
public class AuthorModel {

    private String email;
    private String name;

    public AuthorModel() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AuthorModel{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
