package com.orangetree.tcs.cis454proj1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById((R.id.bottomNavView_Bar));

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_message:
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

        Database db = Database.getInstance(getApplicationContext());
        SQLiteDatabase sqliteDB_W = db.getWritableDatabase();
        //sqliteDB_W.insert("INFO", null, db.insertIntInfo("ID", 12345688));
        //sqliteDB_W.close();
        SQLiteDatabase sqliteDB_R = db.getReadableDatabase();
        Cursor cursor = sqliteDB_R.query("INFO", null, null, null,null, null, null);
        try{
            while (cursor.moveToNext()) {
                int ID = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
                TextView text = findViewById(R.id.textView);
                text.setText("ID from database: " + ID);

            }
            cursor.close();}


        catch (Exception e){
            System.out.println(e);
        }
        sqliteDB_R.close();


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
