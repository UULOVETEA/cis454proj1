package com.orangetree.tcs.cis454proj1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by xcao07 on 2/7/2018.
 */

public class Activity_Message extends AppCompatActivity {

    private String username;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById((R.id.bottomNavView_Bar));
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_message:
                        item.setCheckable(true);
                        Intent intent1 = new Intent(Activity_Message.this, Activity_Message.class);
                        intent1.putExtra("username", username);
                        startActivity(intent1);
                        break;

                    case R.id.ic_notification:
                        Intent intent2 = new Intent(Activity_Message.this, Activity_Notification.class);
                        intent2.putExtra("username", username);
                        startActivity(intent2);
                        break;

                    case R.id.ic_tutors:
                        Intent intent3 = new Intent(Activity_Message.this, Activity_Tutors.class);
                        intent3.putExtra("username", username);
                        startActivity(intent3);
                        break;

                    case R.id.ic_appointment:
                        Intent intent4 = new Intent(Activity_Message.this, Activity_Appointment.class);
                        intent4.putExtra("username", username);
                        startActivity(intent4);
                        break;

                    case R.id.ic_myaccount:
                        Intent intent5 = new Intent(Activity_Message.this, Activity_Account.class);
                        intent5.putExtra("username", username);
                        startActivity(intent5);
                        break;
                }
                return false;
            }
        });

        TextView title = (TextView) findViewById(R.id.activityMessage);
        title.setText("No messages to display");
        title.setGravity(Gravity.CENTER);
        title.setTextSize(25);
    }
}
