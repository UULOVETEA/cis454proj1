package com.orangetree.tcs.cis454proj1;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUserName = (EditText) findViewById(R.id.etUserName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        final Button btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1 = etUserName.getText().toString();
                String email1 = etEmail.getText().toString();
                String password1 = etPassword.getText().toString();
                //register();
                if (!validateEmail(email1)) {
                    etEmail.setError("Invalid Email");
                    etEmail.requestFocus();
                }
                if (!validatePassword(password1)) {
                    etPassword.setError("Invalid Password");
                    etPassword.requestFocus();
                }
//                Database database = Database.getInstance(getApplicationContext());
//                DatabaseHelper helper = new DatabaseHelper(getApplicationContext());
//                String message = "";
//                if (helper.insertAccountAndPassword(name1, password1)) {
//                    message = "Ok, Register successfully";
//                } else {
//                    message = "The account exist, so didn't insert (which is supposed to be)";
//                    //String password = helper.getPassword("TEST2");
//                    TextView text = findViewById(R.id.textView);
//                    text.setText("PASSWORD from database: " + password1 + message);
//
//                }

                //Toast.makeText(getApplicationContext(), "Register successfully", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void register() {
        intialize();
        if (!validate()) {
            Toast.makeText(this, "SignUp has Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            onSignupSccess();
        }
    }

    public void onSignupSccess() {

    }

    public boolean validate() {
        boolean valid = true;

        if (name.isEmpty()) {
            etUserName.setError("Please enter user name");
            valid = false;
        }
        if (email.isEmpty()) {
            etEmail.setError("Please enter email");
            valid = false;
        }
        if (password.isEmpty()) {
            etPassword.setError("Please enter password");
            valid = false;
        }

        return  valid;
    }

    public void intialize() {
        name = etUserName.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();
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


