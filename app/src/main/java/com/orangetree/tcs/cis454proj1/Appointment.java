package com.orangetree.tcs.cis454proj1;

import android.provider.CalendarContract;

import com.orangetree.tcs.cis454proj1.acct.AdminAcct;
import com.orangetree.tcs.cis454proj1.acct.StudentAcct;
import com.orangetree.tcs.cis454proj1.acct.TutorAcct;

import java.util.Calendar;

/**
 * Created by Qiwu Zou on 2018/1/23.
 */

/**
 * The abstract class of appointment that holds information about the meeting
 */
public class Appointment {
    public int duration;
    public AdminAcct adminAccount;
    public StudentAcct studentAccount;
    public TutorAcct tutorAccount;
    public int ID;
    public int session;
    public int offset;

    public Calendar date = Calendar.getInstance();
    /**
     *  construct a appointment object given the account information of
     *  student, admin and tutor, and the start time and duration.
      */

    Appointment(int input_ID, int input_session, int input_offset){
        ID = input_ID;
        session = input_session;
        offset = input_offset;
    }
}
