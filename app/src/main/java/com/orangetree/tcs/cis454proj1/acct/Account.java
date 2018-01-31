package com.orangetree.tcs.cis454proj1.acct;

import android.support.annotation.NonNull;

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

    private String bio;

    /**
     * Maximum number of characters of a user's bio.
     */
    public static final int MAX_BIO_LENGTH = 2500;


    Account(final String name, final int accountNumber, final AccountType accountType)
            throws IllegalArgumentException {
        this.name = new Name(name);
        acctNumber = accountNumber;
        acctType = accountType;
    }

    Account(@NonNull final Name name, final int accountNumber, final AccountType accountType) {
        this.name = name;
        acctNumber = accountNumber;
        acctType = accountType;
    }


    /**
     * Gets the account user's name
     *
     * @return Name of the account user
     */
    public Name name() {
        return name;
    }

    /**
     * Gets the account user's first name
     *
     * @return First name of the account user
     */
    public String firstName() {
        return name.firstName();
    }


    /**
     * Gets the account number
     *
     * @return The account number
     */
    public int accountNumber() {
        return acctNumber;
    }

    /** Gets the account type
     *
     * @return The account type
     */
    public AccountType accountType() {
        return acctType;
    }


    /**
     * Changes a user's bio if it's different and not longer than the maximum length.
     *
     * @param bio New bio string.
     * @return True if the bio was successfully changed
     */
    public boolean editBio(final String bio) {
        if (!bio.equals(this.bio) && !bio.isEmpty() && bio.length() <= MAX_BIO_LENGTH) {
            this.bio = bio;
            return true;
        } else {
            return false;
        }
    }
}
