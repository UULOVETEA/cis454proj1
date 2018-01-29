package com.orangetree.tcs.cis454proj1.acct;

/**
 * The Account class contains all the general information of a user's account.
 *
 * @author Terry Weiss
 * @version 0.2.0
 * @since 29Jan2018
 */

abstract class Account {
    private final Name name;
    private final int acctNumber;
    private final AccountType acctType;


    Account(final String name, final int accountNumber, final AccountType accountType)
            throws IllegalArgumentException {
        this.name = new Name(name);
        acctNumber = accountNumber;
        acctType = accountType;
    }
}
