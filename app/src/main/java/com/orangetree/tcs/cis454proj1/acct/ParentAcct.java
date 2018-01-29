package com.orangetree.tcs.cis454proj1.acct;

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
    public ParentAcct (final String name, final int accountNumber) throws IllegalArgumentException {
        super(name, accountNumber, AccountType.PARENT);
    }
}
