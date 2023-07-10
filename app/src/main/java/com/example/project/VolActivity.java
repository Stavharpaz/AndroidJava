package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.project.Fragment.PersonalLstFragment;
import com.example.project.Fragment.RequestLstFragment;
import com.example.project.Models.Request;


public class VolActivity extends AppCompatActivity {

    private RequestLstFragment requestLstFragment;
    private PersonalLstFragment personalLstFragment;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vol);


        initFragments();
        beginTransactions();

    }

    private void initFragments(){
       requestLstFragment = new RequestLstFragment();
       personalLstFragment =new PersonalLstFragment();

    }

    private void beginTransactions() {

        getSupportFragmentManager().beginTransaction().add(R.id.FRAME_requestLst, requestLstFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.FRAME_My_requestLst, personalLstFragment).commit();

        requestLstFragment.getRequestAdapter().setRequestCallback(request -> {
            personalLstFragment.addRequest(request);
            requestLstFragment.getDataManager().requestWasTaken(request);
            requestLstFragment.getRequestAdapter().removeRequest(request);
            requestLstFragment.getDataManager().removeRequest(Request.REQUESTS, request.getRequest(), request.getid());

        });
    }



}