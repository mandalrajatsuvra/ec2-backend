package com.maliantala.club.users.maliantalaclubbackend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String imageurl;
    private String phoneno;
    private String address;
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
    public Integer getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public String getImageurl() {
        return imageurl;
    }
    public String getPhoneno() {
        return phoneno;
    }
    public User(){}
    public User(Integer id, String username, String password,
                String firstname, String lastname, String imageurl, String phoneno, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.imageurl = imageurl;
        this.phoneno = phoneno;
        this.address = address;
    }
}
