package com.orangetree.tcs.cis454proj1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity_Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etUserName = (EditText) findViewById(R.id.etUserName);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final TextView tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int counter = 3;
                String name = etUserName.getText().toString();
                String password = etPassword.getText().toString();

                if (name.equals("admin") && password.equals("password")) {
                    Intent loginIntent = new Intent(Activity_Login.this, MainActivity.class);
                    startActivity(loginIntent);
                } else { // right now, it doesn;t work
                    counter--;

                    if (counter == 0) {
                        btnLogin.setEnabled(false);
                    }
                }

            }
        });


        tvRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Activity_Login.this, Activity_Register.class);
                startActivity(registerIntent);
            }
        });
    }
}
