package com.orangetree.tcs.cis454proj1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.PatternSyntaxException;

public class Activity_Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etUserName = (EditText) findViewById(R.id.etUserName);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final Button btnRegister = (Button) findViewById(R.id.btnRegister);

        //Database database = Database.getInstance(getApplicationContext());

        final int counter;

//        DatabaseHelper helper = new DatabaseHelper(getApplicationContext());
//            String message = "";
//        if (helper.insertAccountAndPassword("TEST2", "123456")){
//            message = "ok";
//        }
//        else {
//            message = "The account exist, so didn't insert (which is supposed to be)";
//            String password = helper.getPassword("TEST2");
//            TextView text = findViewById(R.id.textView);
//            text.setText("PASSWORD from database: " + password + message);
//        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int counter=0;


                String name = etUserName.getText().toString();
                String password = etPassword.getText().toString();

                if (name.equals("admin") && password.equals("password")) {
                    Intent loginIntent = new Intent(Activity_Login.this, MainActivity.class);
                    startActivity(loginIntent);
                } else { // right now, it doesn;t work
                    counter+=1;

                    if (counter == 3) {
                        btnLogin.setEnabled(false);
                    }
                }

//                DatabaseHelper helper = new DatabaseHelper(getApplicationContext());
//                String message = "";
//                if (helper.insertAccountAndPassword("TEST2", "123456")) {
//                    message = "ok";
//                } else {
//                    message = "The account exist, so didn't insert (which is supposed to be)";
//                    String password = helper.getPassword("TEST2");
//                    TextView text = findViewById(R.id.textView);
//                    text.setText("PASSWORD from database: " + password + message);
//
//                }
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Activity_Login.this, Activity_Register.class);
                startActivity(registerIntent);
            }
        });
    }
}
