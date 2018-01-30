package com.orangetree.tcs.cis454proj1;
import java.util.ArrayList;

/**
 * Created by Qiwu Zou on 2018/1/30.
 */
// the class has bo be a singleton since we have to maintain there is only one calendar.
public class SystemCalendar {
    private static SystemCalendar instance;
    private SystemCalendar(){};
    public ArrayList<Student> students = new ArrayList();
    public ArrayList<Tutor> tutors = new ArrayList();
    public static SystemCalendar getInstance(){
        if (instance == null){
            instance = new SystemCalendar();
        }

        return instance;
    }

    public void addStudent(Student addedStudent){
        students.add(addedStudent);
    }

    public void addTutor(Tutor addedTutor){
        tutors.add(addedTutor);
    }
}
