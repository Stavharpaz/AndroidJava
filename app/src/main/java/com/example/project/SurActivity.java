package com.example.project;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;


import com.example.project.Models.Survivor;
import com.example.project.Utilities.ImageLoader;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;


public class SurActivity extends AppCompatActivity {

    private ExtendedFloatingActionButton hotmeal_button;
    private ExtendedFloatingActionButton groceries_button;
    private ExtendedFloatingActionButton medications_button ;
    private ExtendedFloatingActionButton company_button;
    private ExtendedFloatingActionButton clothes_button;

    private DataManager dataManager;

    private Survivor survivor;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sur);

        findViews();
        ImageLoader.getInstance().loadImage(findViewById(R.id.IMG_background));

        survivor =(Survivor) getIntent().getSerializableExtra(Survivor.SURVIVOR);
        dataManager = new DataManager();

        hotmeal_button.setOnClickListener(v -> dataManager.addRequestToPerson(survivor, hotmeal_button.getText().toString()));


        groceries_button.setOnClickListener(v -> dataManager.addRequestToPerson(survivor, groceries_button.getText().toString()));


        medications_button.setOnClickListener(v -> dataManager.addRequestToPerson(survivor, medications_button.getText().toString()));


        company_button.setOnClickListener(v -> dataManager.addRequestToPerson(survivor, company_button.getText().toString()));


        clothes_button .setOnClickListener(v -> dataManager.addRequestToPerson(survivor, clothes_button.getText().toString()));




    }



    private void findViews() {
        hotmeal_button = findViewById(R.id.hotmeal_button);
        groceries_button = findViewById(R.id.groceries_button);
        medications_button = findViewById(R.id.medications_button);
        company_button  =findViewById(R.id.company_button);
        clothes_button =findViewById(R.id.clothes_button);

    }




}