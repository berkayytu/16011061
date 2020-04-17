package com.berkayhamarat.mobil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void profile(View view){
        Intent intentToProfile = new Intent(Menu.this,profile.class);
        startActivity(intentToProfile);
    }
    public void email(View view){
        Intent intentToEmail = new Intent(Menu.this,sendE.class);
        startActivity(intentToEmail);
    }

}
