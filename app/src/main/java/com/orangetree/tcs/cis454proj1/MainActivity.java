package com.orangetree.tcs.cis454proj1;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById((R.id.bottomNavView_Bar));
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        menu.getItem(0).setCheckable(false);
        
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_message:
                        item.setCheckable(true);
                        Intent intent1 = new Intent(MainActivity.this, Activity_Message.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_notification:
                        Intent intent2 = new Intent(MainActivity.this, Activity_Notification.class);
                        startActivity(intent2);
                        break;

                    case R.id.ic_tutors:
                        Intent intent3 = new Intent(MainActivity.this, Activity_Tutors.class);
                        startActivity(intent3);
                        break;

                    case R.id.ic_appointment:
                        Intent intent4 = new Intent(MainActivity.this, Activity_Appointment.class);
                        startActivity(intent4);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectedItemID = item.getItemId();
        Context context = MainActivity.this;
        int duration = Toast.LENGTH_LONG;
        switch (selectedItemID) {
            case R.id.action_open_account:
                Toast.makeText(context, "Opens account info", duration).show();
                return true;
            case R.id.action_schedule:
                Toast.makeText(context, "Opens user's schedule", duration).show();;
                return true;
            case R.id.action_my_class_list:
                Toast.makeText(context, "Opens user's class list", duration).show();
                return true;
            case R.id.action_tutor_list:
                Toast.makeText(context, "Opens tutors list", duration).show();;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
