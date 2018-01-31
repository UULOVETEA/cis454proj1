package com.orangetree.tcs.cis454proj1;

import android.content.Context;
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
