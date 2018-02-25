package com.orangetree.tcs.cis454proj1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Activity_Account extends AppCompatActivity {

    private String name, password, email, phone;
    private TextView tvUserName;
    private EditText etPassword, etEmail, etPhone;
    private Button btnUpdate, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        tvUserName = (TextView) findViewById(R.id.tvUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPhone = (EditText) findViewById(R.id.etPhone);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnLogout = (Button) findViewById(R.id.btnLogout);

        Intent intent = getIntent();
        name = intent.getStringExtra("username");
        tvUserName.setText("Hi, " + name + "!");

        DatabaseHelper getInfo = new DatabaseHelper(getApplicationContext());
        etPassword.setText(getInfo.getPassword(name));
        etEmail.setText(getInfo.geteEmail(name));
        etPhone.setText(getInfo.getPhone(name));

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById((R.id.bottomNavView_Bar));
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(4);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_message:
                        Intent intent1 = new Intent(Activity_Account.this, Activity_Message.class);
                        intent1.putExtra("username", name);
                        startActivity(intent1);
                        break;

                    case R.id.ic_notification:
                        Intent intent2 = new Intent(Activity_Account.this, Activity_Notification.class);
                        intent2.putExtra("username", name);
                        startActivity(intent2);
                        break;

                    case R.id.ic_tutors:
                        Intent intent3 = new Intent(Activity_Account.this, Activity_Tutors.class);
                        intent3.putExtra("username", name);
                        startActivity(intent3);
                        break;

                    case R.id.ic_appointment:
                        Intent intent4 = new Intent(Activity_Account.this, Activity_Appointment.class);
                        intent4.putExtra("username", name);
                        startActivity(intent4);
                        break;

                    case R.id.ic_myaccount:
                        Intent intent5 = new Intent(Activity_Account.this, Activity_Account.class);
                        intent5.putExtra("username", name);
                        startActivity(intent5);
                        break;
                }
                return false;
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper helper = new DatabaseHelper(getApplicationContext());

                intialize();

                if (validation()) {
                    helper.updatePhoneAndEmail(name, phone, email);

                    DatabaseHelper getInfo = new DatabaseHelper(getApplicationContext());
                    etPassword.setText(getInfo.getPassword(name));
                    etEmail.setText(getInfo.geteEmail(name));
                    etPhone.setText(getInfo.getPhone(name));
                }
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logoutIntent = new Intent(Activity_Account.this, Activity_Login.class);
                startActivity(logoutIntent);
            }
        });

    }

    public void intialize() {
        password = etPassword.getText().toString();
        email = etEmail.getText().toString();
        phone = etPhone.getText().toString();
    }

    public boolean validation() {
        boolean valid = true;

        if (!validatePassword(password)) {
            etPassword.setError("Password must be at least 6 characters");
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
