package com.berkayhamarat.mobil;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.KeyStore;

public class MainActivity extends AppCompatActivity {
    EditText userNameText,passwordText;
    Button logIn;
    SQLiteDatabase userDatabase;
    int fail = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameText = findViewById(R.id.userNameText);
        passwordText = findViewById(R.id.passwordText);
        logIn = findViewById(R.id.logIn);

        userDatabase = this.openOrCreateDatabase("users",MODE_PRIVATE,null);
        userDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY,userName VARCHAR, password VARCHAR)");
        //userDatabase.execSQL("INSERT INTO users (userName,password) values('berkay','admin')");
    }

    public void logIN(View view){
        String userName = userNameText.getText().toString();
        String password = passwordText.getText().toString();
        int flag = 1;

        try {
            userDatabase = this.openOrCreateDatabase("users", MODE_PRIVATE, null);

            Cursor cursor = userDatabase.rawQuery("SELECT * FROM users ", null);
            int trueUserNameIx = cursor.getColumnIndex("userName");
            int truePasswordIx = cursor.getColumnIndex("password");
            while (cursor.moveToNext()) {
                if (userName.equals(cursor.getString(trueUserNameIx))) {
                    if (password.equals(cursor.getString(truePasswordIx))) {
                        Toast.makeText(MainActivity.this,"Log in success", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, Menu.class);
                        startActivity(intent);
                        flag = 0;
                    }
                }
            }
            cursor.close();
            if (flag != 0) {
                fail++;

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

                alert.setTitle("Fail");
                alert.setMessage("Your fail : " + fail + "please enter correct user name and password");
                alert.setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "if you fail third times app will close!", Toast.LENGTH_LONG).show();
                    }
                });
                alert.setNegativeButton("Close the app", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                alert.show();
                if (fail == 3) {
                    Toast.makeText(MainActivity.this, "App is closing !!!", Toast.LENGTH_LONG).show();
                    finish();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void register(View view){
        Intent intentToRegister = new Intent(MainActivity.this,register.class);
        startActivity(intentToRegister);
    }
}
