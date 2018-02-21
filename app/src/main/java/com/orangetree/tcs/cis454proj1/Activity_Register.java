package com.orangetree.tcs.cis454proj1;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Activity_Register extends AppCompatActivity {

    private String name, email, password;
    private EditText etUserName, etEmail, etPassword;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUserName = (EditText) findViewById(R.id.etUserName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        textView = (TextView) findViewById(R.id.textView);

        final Button btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intialize();

                DatabaseHelper helper = new DatabaseHelper(getApplicationContext());

                if (validateEmpty()) {
                    if (helper.insertAccountAndPassword(name, password)) {
                        Intent backLogin = new Intent(Activity_Register.this, Activity_Login.class);
                        startActivity(backLogin);
                    } else {
                        textView.setText("It looks like you're already a member");
                    }
                }
            }
        });
    }

    public boolean validateEmpty() {
        boolean valid = true;

        if (name.isEmpty()) {
            etUserName.setError("Please enter a username");
            valid = false;
        }
        if (email.isEmpty()) {
            etEmail.setError("Please enter a email");
            valid = false;
        }
        if (password.isEmpty()) {
            etPassword.setError("Please enter a password");
            valid = false;
        }

        return  valid;
    }

    public void intialize() {
        name = etUserName.getText().toString();
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
    }

    protected boolean validateEmail(String email) {
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    protected boolean validatePassword(String password) {
        if (password!=null && password.length()>6) {
            return true;
        } else {
            return false;
        }
    }
}


