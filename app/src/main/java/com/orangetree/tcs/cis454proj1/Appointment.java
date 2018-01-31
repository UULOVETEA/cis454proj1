package com.orangetree.tcs.cis454proj1;

import android.provider.CalendarContract;

import java.util.Calendar;

/**
 * Created by Qiwu Zou on 2018/1/23.
 */
// the abstract class of appointment that holds information about the meeting
public class Appointment {
    public int duration;
    public String student_name, admin_name, tutor_name;
    public boolean availability;
    public Calendar date = Calendar.getInstance();
    // constructor
    Appointment(int year, int month, int day, int hour, int minute, int second, String student, String admin, String tutor, int dur_time){
        date.set(year,month,day,hour,minute,second);
        student_name = student;
        admin_name = admin;
        tutor_name = tutor;
        duration = dur_time;

    }
}
