package com.orangetree.tcs.cis454proj1;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by Qiwu Zou on 2018/1/30.
 */
// the class has bo be a singleton since we have to maintain there is only one calendar.
public class SystemCalendar {
    private static SystemCalendar instance;
    private SystemCalendar(){};
    public static HashMap<Calendar, Appointment> appCollection = new HashMap<Calendar, Appointment>();
    public ArrayList<Appointment> appList = new ArrayList();
    public static SystemCalendar getInstance(){
        if (instance == null){
            instance = new SystemCalendar();
        }

        return instance;
    }

}
