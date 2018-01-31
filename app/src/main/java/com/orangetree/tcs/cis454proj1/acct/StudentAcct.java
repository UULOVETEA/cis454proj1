package com.orangetree.tcs.cis454proj1.acct;

import java.util.ArrayList;

/**
 * The StudentAcct class contains all the information of a student user's account. Student accounts
 * handle all use cases specific to the students’ learning from the customers’ perspective. This
 * includes setting the students’ class enrollments and profiles, requesting and cancelling
 * appointments, referring friends, viewing schedules, and submitting feedback on a tutor’s
 * performance during a session.
 *
 * @author Terry Weiss
 * @version 0.2.0
 * @since 29Jan2018
 */

public class StudentAcct extends Account {
    private boolean financialFrozen;     // Whether account has been frozen for non-payment

    private ArrayList<String> coursesTaken; // List of courses the student is taking

    public StudentAcct (final String name, final int accountNumber)
            throws IllegalArgumentException {
        super(name, accountNumber, AccountType.STUDENT);
    }
}
