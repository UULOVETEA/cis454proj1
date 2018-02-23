package com.orangetree.tcs.cis454proj1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Activity_Register extends AppCompatActivity {

    private String name, password, checkPassowrd, email, phone;
    private EditText etUserName, etPassword,etConfirmPassword, etEmail, etPhone;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPhone = (EditText) findViewById(R.id.etPhone);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intialize();

                DatabaseHelper helper = new DatabaseHelper(getApplicationContext());

                if (validation()) {
                    if (helper.insertAccountAndPassword(name, password)) {
                        helper.updatePhoneAndEmail(name, phone, email);
                        Intent backLogin = new Intent(Activity_Register.this, Activity_Login.class);
                        startActivity(backLogin);
                    } else {
                        etUserName.setError("It looks like you're already a member");
                        etUserName.requestFocus();
                    }
                }
            }
        });
    }

    public boolean validation() {
        boolean valid = true;

        if (name.isEmpty()) {
            etUserName.setError("Please enter a username");
            valid = false;
        }
        if (!validatePassword(password)) {
            etPassword.setError("Password must be at least 6 characters");
            valid = false;
        }
        if (checkPassowrd.isEmpty()) {
            etConfirmPassword.setError("Please enter your password again");
            valid = false;
        }
        if (!checkPassowrd.equals(password)) {
            etConfirmPassword.setError("Passwords does not match");
            valid = false;
        }
        if (!validateEmail(email)) {
            etEmail.setError("Please enter a valid email");
            valid = false;
        }
        if (!validatePhone(phone) || phone.length() != 10) {
            etPhone.setError("Please enter a valid phone number");
        }
        return  valid;
    }

    public void intialize() {
        name = etUserName.getText().toString();
        password = etPassword.getText().toString();
        checkPassowrd = etConfirmPassword.getText().toString();
        email = etEmail.getText().toString();
        phone = etPhone.getText().toString();
    }

    public boolean validatePassword(String password) {
        if (password!=null && password.length()>5) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateEmail(String email) {
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public boolean validatePhone(String phone) {
        String phonePattern = "\\d*\\.?\\d+";

        Pattern pattern = Pattern.compile(phonePattern);
        Matcher matcher = pattern.matcher(phone);

        return matcher.matches();
    }
}
