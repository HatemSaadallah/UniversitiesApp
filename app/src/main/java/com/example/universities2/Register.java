package com.example.universities2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.universities2.ui.login.LoginActivity;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Register.this, LoginActivity.class);
                startActivity(i);
            }
        });
        final FeedReaderContract.FeedReaderDbHelper dbHelper = new FeedReaderContract.FeedReaderDbHelper(this);
// Create a new map of values, where column names are the keys
        final Button register = findViewById(R.id.register);


        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText usrname = findViewById(R.id.username);
                EditText pwd = findViewById(R.id.password);
//                Intent i = new Intent(Register.this, LoginActivity.class);
//                startActivity(i);
//                 Gets the data repository in write mode
                if( TextUtils.isEmpty(usrname.getText())){
                    /**
                     *   You can Toast a message here that the Username is Empty
                     **/
                    usrname.setError( "First name is required!" );

                } else{
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();

                    try {
                        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_USERNAME, usrname.getText().toString());
                        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_PASSWORD, pwd.getText().toString());
//                 Insert the new row, returning the primary key value of the new row
                        long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
                        Toast.makeText(getApplicationContext(), "تم تسجيل الحساب بنجاح", Toast.LENGTH_LONG).show();
                        Intent i=new Intent(getBaseContext(), LoginActivity.class);
                        startActivity(i);
                        finish();
                    }
                    catch(android.database.sqlite.SQLiteConstraintException e) {
                        Toast.makeText(getApplicationContext(), "عذرا، اسم الحساب مستخدم مسبقا", Toast.LENGTH_LONG).show();
                    }
                }


            }
        });




    }
}
