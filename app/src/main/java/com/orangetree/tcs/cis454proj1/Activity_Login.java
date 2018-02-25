package com.orangetree.tcs.cis454proj1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity_Login extends AppCompatActivity {

    private String name, password;
    private EditText etUserName, etPassword;
    private Button btnLogin, btnRegister;
    private TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        tvMessage = (TextView) findViewById(R.id.tvMessage);

        /*
        String boo;
        DatabaseHelper db_h = new DatabaseHelper(getApplicationContext());
        if(db_h.insertPhoneAndEmail("zouqiwu", "123456", "dfs@126.com")){
            boo = "True";
        }
        else {
            boo = "False";
        }
        String test_output = db_h.getPhone("zouqiwu");
        tvMessage.setText(test_output + boo);



        */
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper helper = new DatabaseHelper(getApplicationContext());

                name = etUserName.getText().toString();
                password = etPassword.getText().toString();

                if (validateEmpty()) {
                    Boolean checkUserName = helper.databaseContains(name);
                    String checkPassword = helper.getPassword(name);

                    if (name.equals("admin") && password.equals("password")) {
                        helper.insertAccountAndPassword("admin", "password");
                        helper.updatePhoneAndEmail("admin", "4544548888", "CIS454-group8@syr.edu");
                        Intent loginIntent = new Intent(Activity_Login.this, Activity_Welcome.class);
                        loginIntent.putExtra("username", name);
                        startActivity(loginIntent);
                    }
                    else if (checkUserName == true && checkPassword.equals(password)) {
                        Intent loginIntent = new Intent(Activity_Login.this, Activity_Welcome.class);
                        loginIntent.putExtra("username", name);
                        startActivity(loginIntent);
                    }
                    else if (checkPassword.equals("ACCOUNT DOES NOT EXIST")) {
                        tvMessage.setText("The account " + "'"+ name + "'" + " is not registered for CTS");
                    }
                    else {
                        tvMessage.setText("Your username or password were entered incorrectly");
                    }
                }
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

    public boolean validateEmpty() {
        boolean valid = true;

        if (name.isEmpty()) {
            etUserName.setError("Please enter a valid username");
            valid = false;
        }
        if (password.isEmpty()) {
            etPassword.setError("Please enter a password");
            valid = false;
        }
        return valid;
    }
}
