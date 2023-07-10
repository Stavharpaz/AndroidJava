package com.example.project;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.example.project.Models.Survivor;
import com.example.project.Utilities.ImageLoader;
import com.example.project.Utilities.SignalGenerator;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class LoginActivity extends AppCompatActivity {

private final  String ASSOCIATION_PASSWORD = "496351111";
    private AppCompatEditText id;
    private ExtendedFloatingActionButton loginButton;

    private DataManager dataManager;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sur);


        dataManager = new DataManager();
        findViews();
        ImageLoader.getInstance().loadImage(findViewById(R.id.IMG_background));


        loginButton.setOnClickListener(v -> {

            if(id==null || id.getText()==null|| id.length()!=9 || !TextUtils.isDigitsOnly(id.getText().toString()))
                SignalGenerator.getInstance().toast(SignalGenerator.FILL_TUB+": "+ Survivor.ID+ ", "+ SignalGenerator.NINE_DIGIT, Toast.LENGTH_SHORT);

            else {
                String checkId = id.getText().toString().trim();

                if (checkId.equals(ASSOCIATION_PASSWORD))
                    moveNextActivity(null);

                else{
                    dataManager.checkPersonExistence(checkId, (exists, survivor) -> {
                        if (exists)
                            moveNextActivity(survivor);
                        else
                            SignalGenerator.getInstance().toast(SignalGenerator.ID_NOT_EXIST, Toast.LENGTH_SHORT);
                    });
                }
            }

        });
    }

    private void findViews() {
        id = findViewById(R.id.id_text);
        loginButton = findViewById(R.id.next_button);
    }

    public void moveNextActivity(Survivor survivor) {
        Intent intent;
        if(survivor==null)
            intent = new Intent(getApplicationContext(), AssociationActivity.class);
        else {
            intent = new Intent(getApplicationContext(), SurActivity.class);
            intent.putExtra(Survivor.SURVIVOR, survivor);

        }
        startActivity(intent);
        finish();
    }

}