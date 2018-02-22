package com.orangetree.tcs.cis454proj1;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class Activity_Welcome extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    private String name, message;
    private TextView tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Intent intent = getIntent();
        name = intent.getStringExtra("username");

        message = "Hi, " + "<b>" + name + "</b>" + "!";
        tvWelcome = (TextView) findViewById(R.id.tvWelcome);
        tvWelcome.setText(Html.fromHtml(message,1));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent loginIntent = new Intent(Activity_Welcome.this, MainActivity.class);
                startActivity(loginIntent);
            }
        },SPLASH_TIME_OUT);
    }
}
