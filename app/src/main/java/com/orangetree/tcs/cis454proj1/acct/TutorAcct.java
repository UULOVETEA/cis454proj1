package com.orangetree.tcs.cis454proj1.acct;

import java.util.ArrayList;

/**
 * The TutorAcct class contains all the information of a tutor user's account. Tutor accounts
 * handle all use cases that are specific to the teaching aspects of the system, or what the
 * tutor user would be able to do. This includes setting the tutors’ profiles and schedules,
 * accepting an appointment, and submitting feedback on a student’s performance during a session.
 *
 * @author Terry Weiss
 * @version 0.2.0
 * @since 29Jan2018
 */

public class TutorAcct extends Account {
    private ArrayList<StudentAcct> studentsTutored;


    public TutorAcct(final String name, final int accountNumber) throws IllegalArgumentException {
        super(name, accountNumber, AccountType.TUTOR);
        studentsTutored = new ArrayList<>();
    }


    public void addStudent(final StudentAcct student) {
        studentsTutored.add(student);
    }

    public int studentsTaught() {
        return studentsTutored.size();
    }
}
