package com.example.project.Models;

import java.io.Serializable;


public class Survivor implements Serializable {

    private String name="";
    private String address="";
    private String phone="";
    private String mail="";

    private String id="";


    public static final String SURVIVORS = "SURVIVORS";
    public static final String SURVIVOR = "SURVIVOR";



    //details field
    public static final String NAME = "NAME";
    public static final String ADDRESS = "ADDRESS";
    public static final String PHONE = "PHONE";
    public static final String EMAIL = "EMAIL";
    public static final String ID = "ID";





    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }

    public String getId() {
        return id;
    }


    public Survivor setName(String name) {
        this.name = name;
        return this;
    }

    public Survivor setAddress(String address) {
        this.address = address;
        return this;
    }

    public Survivor setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Survivor setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public Survivor setId(String id) {
        this.id = id;
        return this;
    }




}
