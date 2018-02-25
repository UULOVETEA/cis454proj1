package com.orangetree.tcs.cis454proj1;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Qiwu Zou on 2018/1/30.
 */

/**
 * The class has bo be a singleton since we have to maintain there is only one calendar.
 * The singleton is implemented by "getInstance" method.
 */
public class SystemCalendar {
    private static SystemCalendar instance;
    private SystemCalendar(){};
    /**
     * Use HashMap to map the time stamp to the appointment object.
     */
    public static HashMap<Calendar, Appointment> appCollection = new HashMap<Calendar, Appointment>();
    public static ArrayList<Appointment> appList = new ArrayList<>();
    public static SystemCalendar getInstance(){
        if (instance == null){
            instance = new SystemCalendar();
        }

        return instance;
    }

    public void clearAppoinment (Calendar timeStamp){
        appCollection.remove(timeStamp);
    }

    public List<String> getAppointments(DatabaseHelper db_h){
        if (db_h.getAppNum() == 0) {
            Calendar Calendar_temp = Calendar.getInstance();
            Date date_temp;
            String dateTimeString;
            List<String> temp = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                if (i % 2 == 0) {
                    Calendar_temp.add(Calendar.DATE, +1);
                }
                date_temp = Calendar_temp.getTime();
                dateTimeString = new SimpleDateFormat("MM/dd").format(date_temp);
                if (i % 2 == 0) {
                    dateTimeString = dateTimeString + "   9:00 AM";
                } else {
                    dateTimeString = dateTimeString + "   2:00 PM";
                }
                int id = db_h.insertAppointment(i/2+1, i % 2 == 0 ? 0 : 1);
                Appointment appoint = new Appointment(id, i / 2 + 1, i % 2 == 0 ? 0 : 1);
                appList.add(appoint);
                temp.add(dateTimeString);
            }
            return temp;
        }
        else{
            List<String> temp = new ArrayList<>();
            temp = db_h.getAllApp();
            return temp;
        }

    }

    public void makeAppointment(String name, DatabaseHelper db_h, int session, int offset){
        int rowid = db_h.updateAppintment(session, offset, 0);
        db_h.bindAccountWithApp(name, rowid);

    }
    /**
     * Add an appointment to the Calendar
     * @param timeStamp
     * @param appointmentInformation
     * @return If there was one appointment in that time, then return false.
     * Otherwise return true
     */
    public boolean addAppointment(Calendar timeStamp, Appointment appointmentInformation){
        if (appCollection.containsKey(timeStamp)){
            return false;
        }
        appCollection.put(timeStamp,appointmentInformation);
        return true;
    }

}
