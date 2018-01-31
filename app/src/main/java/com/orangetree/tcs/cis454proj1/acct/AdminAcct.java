package com.orangetree.tcs.cis454proj1.acct;

/**
 * The AdminAcct class contains all the information of an admin user's account. Admin accounts
 * handle the business aspects of the system from the company’s perspective. This includes
 * scheduling or canceling appointments by request over customer service, viewing schedules,
 * adding courses and topics to the system, and giving refunds.
 *
 * @author Terry Weiss
 * @version 0.2.0
 * @since 29Jan2018
 */

public class AdminAcct extends Account {
    public AdminAcct(final String name, final int accountNumber) throws IllegalArgumentException {
        super(name, accountNumber, AccountType.ADMIN);
    }
}
