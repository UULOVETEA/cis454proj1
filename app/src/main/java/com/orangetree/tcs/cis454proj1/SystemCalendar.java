package com.orangetree.tcs.cis454proj1;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

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

    public static SystemCalendar getInstance(){
        if (instance == null){
            instance = new SystemCalendar();
        }

        return instance;
    }

    /**
     * To get the collection of all appointments
     * @return An array list of appointments
     */
    public ArrayList<Appointment> getAppointments (){
        ArrayList<Appointment> appList = new ArrayList();
        Iterator it = appCollection.entrySet().iterator();
        while (it.hasNext()){
            appList.add((Appointment)it.next());
        }
        return appList;
    }

    public void clearAppoinment (Calendar timeStamp){
        appCollection.remove(timeStamp);
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
