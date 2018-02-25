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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by xcao07 on 2/7/2018.
 */

public class Activity_Appointment extends AppCompatActivity {
//<<<<<<< HEAD

    private String username;

//=======
    TextView text5;
    long index;
    SystemCalendar system;
    List<String> list;
    DatabaseHelper db_h;
    Spinner appointments_Spinner;
    ArrayAdapter<String> dataAdapter;
//>>>>>>> 168e2cd876ee49f74339b63ca9ab88979f54f741
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById((R.id.bottomNavView_Bar));
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_message:
                        item.setCheckable(true);
                        Intent intent1 = new Intent(Activity_Appointment.this, Activity_Message.class);
                        intent1.putExtra("username", username);
                        startActivity(intent1);
                        break;

                    case R.id.ic_notification:
                        Intent intent2 = new Intent(Activity_Appointment.this, Activity_Notification.class);
                        intent2.putExtra("username", username);
                        startActivity(intent2);
                        break;

                    case R.id.ic_tutors:
                        Intent intent3 = new Intent(Activity_Appointment.this, Activity_Tutors.class);
                        intent3.putExtra("username", username);
                        startActivity(intent3);
                        break;

                    case R.id.ic_appointment:
                        Intent intent4 = new Intent(Activity_Appointment.this, Activity_Appointment.class);
                        intent4.putExtra("username", username);
                        startActivity(intent4);
                        break;

                    case R.id.ic_myaccount:
                        Intent intent5 = new Intent(Activity_Appointment.this, Activity_Account.class);
                        intent5.putExtra("username", username);
                        startActivity(intent5);
                        break;
                }
                return false;
            }
        });


        TextView title = (TextView) findViewById(R.id.activityAppointment);
        title.setText("The appointment you are enrolled");
        title.setGravity(Gravity.CENTER);
        title.setTextSize(25);
        Calendar Calendar_temp = Calendar.getInstance();
        Calendar_temp.add(Calendar.DATE, +1);
        Date date_temp = Calendar_temp.getTime();
        String currentDateTimeString = new SimpleDateFormat("MM/dd").format(date_temp);
        appointments_Spinner = (Spinner) findViewById(R.id.spinner);
        list = new ArrayList<String>();
        list.add(currentDateTimeString);
        db_h = new DatabaseHelper(getApplicationContext());
        system = SystemCalendar.getInstance();
        list = system.getAppointments(db_h);
        dataAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, list);
        appointments_Spinner.setAdapter(dataAdapter);
        Button confirm_Button = (Button) findViewById(R.id.confirm_button);
        text5 = (TextView)findViewById(R.id.textView5);
        text5.setText(db_h.getAppWithAccount(Activity_Account.getUsername()));
        updateText();
        confirm_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = appointments_Spinner.getSelectedItemPosition();
                String name = Constant.getInstance().name;
                if (db_h.getAppWithAccount(Constant.getInstance().name) == "") {
                    switch ((int) index) {
                        case 0:
                            system.makeAppointment(name, db_h, 1, 0);
                            list = system.getAppointments(db_h);
                            updateAdapter(list);
                            updateText();
                            break;

                        case 1:
                            system.makeAppointment(name, db_h, 1, 1);
                            list = system.getAppointments(db_h);
                            updateAdapter(list);
                            updateText();
                            break;
                        case 2:
                            system.makeAppointment(name, db_h, 2, 0);
                            list = system.getAppointments(db_h);
                            updateAdapter(list);
                            updateText();
                            break;
                        case 3:
                            system.makeAppointment(name, db_h, 2, 1);
                            list = system.getAppointments(db_h);
                            updateAdapter(list);
                            updateText();
                            break;
                        case 4:
                            system.makeAppointment(name, db_h, 3, 0);
                            list = system.getAppointments(db_h);
                            updateAdapter(list);
                            updateText();
                            break;
                        case 5:
                            system.makeAppointment(name, db_h, 3, 1);
                            list = system.getAppointments(db_h);
                            updateAdapter(list);
                            updateText();
                            break;

                    }
                }
                else{
                    CharSequence text = "Sorry, you have already signed up for one appointment.";
                    Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

    }

    public void updateAdapter(List l){
        List<String> list = l;
        dataAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, list);
        dataAdapter.notifyDataSetChanged();
        appointments_Spinner.setAdapter(dataAdapter);

    }

    public void updateText(){
        text5.setText(db_h.getAppWithAccount(Constant.getInstance().name));
    }
}