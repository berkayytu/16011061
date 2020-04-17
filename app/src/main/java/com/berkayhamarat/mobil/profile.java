package com.berkayhamarat.mobil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class profile extends AppCompatActivity {

    EditText nameText,weightText,heightText,ageText;
    RadioGroup radioGroup;
    RadioButton manButton,womanButton;
    Button profileSave;
    Switch modeSwitch;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nameText = findViewById(R.id.nameText);
        weightText = findViewById(R.id.weightText);
        heightText = findViewById(R.id.heightText);
        ageText = findViewById(R.id.ageText);
        manButton = findViewById(R.id.radioButtonMan);
        radioGroup = findViewById(R.id.radioGroup);
        womanButton = findViewById(R.id.radioButtonWoman);
        modeSwitch = findViewById(R.id.modeSwitch);
        profileSave = findViewById(R.id.profileSave);

        sharedPreferences = this.getSharedPreferences("com.berkayhamarat.mobil", Context.MODE_PRIVATE);
        String storedName = sharedPreferences.getString("storedName","");
        if (storedName !=""){
            nameText.setText(storedName);
        }
        int storedWeight = sharedPreferences.getInt("storedWeight",0);
        if (storedWeight != 0){
            weightText.setText(String.valueOf(storedWeight));
        }
        int storedHeight = sharedPreferences.getInt("storedHeight",0);
        if (storedHeight != 0){
            heightText.setText(String.valueOf(storedHeight));
        }
        int storedAge = sharedPreferences.getInt("storedAge",0);
        if (storedAge != 0){
            ageText.setText(String.valueOf(storedAge));
        }
        boolean storedGenderMan = sharedPreferences.getBoolean("storedGenderMan",false);
        manButton.setChecked(storedGenderMan);
        boolean storedGenderWoman = sharedPreferences.getBoolean("storedGenderWoman",false);
        womanButton.setChecked(storedGenderWoman);

    }

    public void profileSave(View view){
        if(!nameText.getText().toString().matches("")){
            String userName = nameText.getText().toString();
            sharedPreferences.edit().putString("storedName",userName).apply();
        }
        if(!weightText.getText().toString().matches("")){
            int userWeight = Integer.parseInt(weightText.getText().toString());
            sharedPreferences.edit().putInt("storedWeight",userWeight).apply();
        }
        if(!heightText.getText().toString().matches("")){
            int userHeight = Integer.parseInt(heightText.getText().toString());
            sharedPreferences.edit().putInt("storedHeight",userHeight).apply();
        }
        if(!ageText.getText().toString().matches("")){
            int userAge = Integer.parseInt(ageText.getText().toString());
            sharedPreferences.edit().putInt("storedAge",userAge).apply();
        }
        if (manButton.isChecked()){
            sharedPreferences.edit().putBoolean("storedGenderMan",true).apply();
            sharedPreferences.edit().putBoolean("storedGenderWoman",false).apply();
        }
        if (womanButton.isChecked()){
            sharedPreferences.edit().putBoolean("storedGenderWoman",true).apply();
            sharedPreferences.edit().putBoolean("storedGenderMan",false).apply();
        }


    }


}
