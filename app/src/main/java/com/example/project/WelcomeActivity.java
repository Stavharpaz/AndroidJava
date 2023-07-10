package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.example.project.Utilities.ImageLoader;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class WelcomeActivity extends AppCompatActivity {

    private ExtendedFloatingActionButton volunteersButton;
    private ExtendedFloatingActionButton survivorsButton;


    private ExtendedFloatingActionButton addSurvivorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        findViews();
        ImageLoader.getInstance().loadImage(findViewById(R.id.IMG_background));

        addSurvivorButton.setOnClickListener(view -> clicked(0));

        volunteersButton.setOnClickListener(view -> clicked(1));

        survivorsButton.setOnClickListener(view -> clicked(2));
    }



    private void findViews() {
        survivorsButton = findViewById(R.id.survivors_button);
        volunteersButton = findViewById(R.id.volunteers_button);
        addSurvivorButton = findViewById(R.id.add_surviver_button);
    }


    public void clicked(int isVol) {
        Intent intent;
        if(isVol==1)
            intent = new Intent(this, LoginVolActivity.class);
        else if(isVol==2)
            intent = new Intent(this, LoginActivity.class);
        else
            intent = new Intent(this, AddSurActivity.class);
        startActivity(intent);
        finish();
    }


}