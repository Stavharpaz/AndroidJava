package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatEditText;

import com.example.project.Models.Survivor;
import com.example.project.Utilities.ImageLoader;
import com.example.project.Utilities.SignalGenerator;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;


public class AddSurActivity extends AppCompatActivity {
    private AppCompatEditText name;
    private AppCompatEditText address;
    private AppCompatEditText phone;
    private AppCompatEditText email;

    private AppCompatEditText id;

    private ExtendedFloatingActionButton button;

    private DataManager dataManager;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sur);

        findViews();
        ImageLoader.getInstance().loadImage(findViewById(R.id.IMG_background));
        dataManager = new DataManager();

        button.setOnClickListener(v -> {
            if(address ==null || address.getText() ==null || address.getText().toString().equals(""))
                SignalGenerator.getInstance().toast(SignalGenerator.FILL_TUB+": "+ Survivor.ADDRESS, Toast.LENGTH_SHORT);

            else if(email ==null || email.getText() ==null || email.getText().toString().equals(""))
                SignalGenerator.getInstance().toast(SignalGenerator.FILL_TUB+": "+ Survivor.EMAIL, Toast.LENGTH_SHORT);

            else if(name==null|| name.getText() ==null ||name.getText().toString().equals(""))
                SignalGenerator.getInstance().toast(SignalGenerator.FILL_TUB+": "+ Survivor.NAME, Toast.LENGTH_SHORT);

            else if(phone==null || phone.getText() ==null ||phone.length()!=10 || !TextUtils.isDigitsOnly(phone.getText()))
                SignalGenerator.getInstance().toast(SignalGenerator.FILL_TUB+": "+ Survivor.PHONE+ " "+SignalGenerator.TEN_DIGITS, Toast.LENGTH_SHORT);


            else if(id==null || id.getText() ==null  || id.length()!=9 || !TextUtils.isDigitsOnly(id.getText())) //check id.getText()
                    SignalGenerator.getInstance().toast(SignalGenerator.FILL_TUB + ": " + Survivor.ID +" "+ SignalGenerator.NINE_DIGIT, Toast.LENGTH_SHORT);

            else
                dataManager.checkPersonExistence(id.getText().toString().trim(), (exists, survivor) -> {
                    if(exists)
                        SignalGenerator.getInstance().toast(Survivor.ID +" "+SignalGenerator.EXIST, Toast.LENGTH_SHORT);
                    else
                        insertSurvivorData();

                });

        });


    }

    private void findViews() {
        name = findViewById(R.id.name_text);
        phone = findViewById(R.id.phone_text);
        email = findViewById(R.id.mail_text);
        address = findViewById(R.id.address_text);
        button = findViewById(R.id.next_button);
        id = findViewById(R.id.id_text);

    }

    private void insertSurvivorData(){

        if(address.getText()!=null && email.getText()!=null && name.getText()!=null && phone.getText()!=null && id.getText()!=null){
            Survivor survivor = new Survivor()
                    .setAddress(address.getText().toString())
                    .setMail(email.getText().toString())
                    .setName(name.getText().toString())
                    .setPhone(phone.getText().toString())
                    .setId(id.getText().toString());

           dataManager.insertNewSurvivor(survivor);
            moveNextView();

        }
    }

    private void moveNextView() {
        Intent intent  = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}