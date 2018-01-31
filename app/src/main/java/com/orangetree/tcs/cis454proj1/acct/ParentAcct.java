package com.orangetree.tcs.cis454proj1.acct;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * The ParentAcct class contains all the information of a parent user's account. Parent accounts
 * handle the business aspects of the system from the customers’ perspective. This includes viewing
 * schedules, cancelling appointments if unable to pay, registering a student for tutoring, and
 * paying the account balance. Parent accounts are tied to at least one Student account.
 *
 * @author Terry Weiss
 * @version 0.2.0
 * @since 29Jan2018
 */

public class ParentAcct extends Account {
    private ArrayList<StudentAcct> children;


    public ParentAcct (@NonNull final Name name, final int accountNumber) throws IllegalArgumentException {
        super(name, accountNumber, AccountType.PARENT);
        children = new ArrayList<>();
    }


    public void addChild(@NonNull final StudentAcct child) {
        children.add(child);
    }

    public ArrayList<StudentAcct> children() {
        return children;
    }
}
