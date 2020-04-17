package com.berkayhamarat.mobil;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {
    EditText userName,password;
    Button register;
    SQLiteDatabase userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userName = findViewById(R.id.registerUsername);
        password = findViewById(R.id.registerPassword);
        register = findViewById(R.id.tryRegister);
    }

    public void tryRegister(View view){
        String rUserName = userName.getText().toString();
        String rPassword = password.getText().toString();
        int fail = 0;
        try {
            userDatabase = this.openOrCreateDatabase("users",MODE_PRIVATE,null);
            Cursor cursor = userDatabase.rawQuery("SELECT * FROM users ", null);
            int userNameIx = cursor.getColumnIndex("userName");

            while (cursor.moveToNext()) {
                if (rUserName.equals(cursor.getString(userNameIx))) {
                    fail = 1;
                    AlertDialog.Builder alert = new AlertDialog.Builder(register.this);

                    alert.setTitle("Fail");
                    alert.setMessage("This username already taken please try another username");
                    alert.setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(register.this, "Lets try", Toast.LENGTH_LONG).show();
                            Intent tryAgain = new Intent(register.this, com.berkayhamarat.mobil.register.class);
                            startActivity(tryAgain);
                        }
                    });
                    alert.setNegativeButton("Close the app", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    alert.show();
                }
            }
            if (fail == 0) {
                String sqlString = "INSERT INTO users (userName,password) VALUES(?,?)";
                SQLiteStatement sqLiteStatement = userDatabase.compileStatement(sqlString);
                sqLiteStatement.bindString(1, rUserName);
                sqLiteStatement.bindString(2, rPassword);
                sqLiteStatement.execute();
                Toast.makeText(com.berkayhamarat.mobil.register.this, "You are a member of system!", Toast.LENGTH_LONG).show();
                Intent intentToLogin = new Intent(com.berkayhamarat.mobil.register.this, MainActivity.class);
                startActivity(intentToLogin);
            }
            cursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
