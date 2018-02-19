package com.orangetree.tcs.cis454proj1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by xcao07 on 2/7/2018.
 */

public class Activity_Appointment extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById((R.id.bottomNavView_Bar));
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.ic_message:
                        Intent intent1 = new Intent(Activity_Appointment.this, Activity_Message.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_notification:
                        Intent intent2 = new Intent(Activity_Appointment.this, Activity_Notification.class);
                        startActivity(intent2);
                        break;

                    case R.id.ic_tutors:
                        Intent intent3 = new Intent(Activity_Appointment.this, Activity_Tutors.class);
                        startActivity(intent3);
                        break;

                    case R.id.ic_appointment:
                        Intent intent4 = new Intent(Activity_Appointment.this, Activity_Appointment.class);
                        startActivity(intent4);
                        break;
                }
                return false;
            }
        });

        TextView title = (TextView) findViewById(R.id.activityAppointment);
        title.setText("No appointment to display");
        title.setGravity(Gravity.CENTER);
        title.setTextSize(25);
    }
}

