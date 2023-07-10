package com.example.project.Interfaces;

import com.example.project.Models.Request;

import java.util.ArrayList;

public interface LoadDataCallback {

    void onRequestsLoaded(ArrayList<Request> requestsList);
    void onRequestsLoadError(String errorMessage);}
