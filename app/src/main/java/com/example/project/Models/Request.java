package com.example.project.Models;



public class Request {
    private String request = "";

    private String id = "";

    private Survivor survivor;

    private String user;

    public static final String VOL_REQUESTS = "VOL_REQUESTS";

    public static final String REQUESTS = "REQUESTS";
    public static final String HOT_MEAL = "HOT MEAL";
    public static final String GROCERIES = "GROCERIES";
    public static final String MEDICATION = "MEDICATION";
    public static final String COMPANY = "COMPANY";
    public static final String CLOTHES = "CLOTHES";



    public Request setRequest(String request) {
        this.request = request;
        return this;
    }


    public Survivor getSurvivor() {
        return survivor;
    }

    public Request setSurvivor(Survivor survivor) {
        this.survivor = survivor;
        return this;
    }

    public String getRequest() {
        return request;
    }

    public String getid() {
        return id;
    }

    public Request setId(String id) {
        this.id = id;
        return this;
    }


    public String getUser() {
        return user;
    }

    public Request setUser(String user) {
        this.user = user;
        return this;
    }
}
