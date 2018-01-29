package com.orangetree.tcs.cis454proj1.acct;

import android.support.annotation.NonNull;

/**
 * The Name class allows for various utilities based on an account user's name.
 *
 * @author Terry Weiss
 * @version 0.2.0
 * @since 29Jan2018
 */

public class Name implements Comparable<Name> {
    /**
     * Minimum number of names for a full name. Currently 2.
     */
    public static final int MIN_NAMES = 2;

    private final String[] names;

    /**
     * Creates a name given a user's first and last name.
     *
     * @param firstName the user's first name
     * @param lastName the user's last name
     */
    public Name(final String firstName, final String lastName) {
        names = new String[] { firstName, lastName };
    }

    /**
     * Creates a name given a single string. If at least a first and last name isn't given,
     * an error will be thrown.
     *
     * @param fullName the user's full name in one string (requires first and last name)
     * @throws IllegalArgumentException If two names aren't provided
     */
    public Name(final String fullName) throws IllegalArgumentException {
        String[] names = fullName.split(" ");
        if (names.length < MIN_NAMES) {
            throw new IllegalArgumentException("Illegal name \"" + fullName +
                    "\". Must include first and last name.");
        }

        this.names = names;
    }


    /**
     * Returns the first name.
     *
     * @return first name
     */
    public String firstName() {
        return names[0];
    }

    /**
     * Returns the last name.
     *
     * @return last name
     */
    public String lastName() {
        return names[names.length - 1];
    }


    /**
     * Compares names in lexigraphical order by last name first. All comparisons are
     * case insensitive.
     *
     * @param name2  second Name object to compare against
     * @return a negative integer, zero, or a positive integer as this name is before, equal to, or after the specified name.
     */
    @Override
    public int compareTo(@NonNull Name name2) {
        final String name1Str = (this.lastName() + this.toString()).toLowerCase();
        final String name2Str = (name2.lastName() + this.toString()).toLowerCase();
        return name1Str.compareTo(name2Str);
    }

    /**
     * Returns a string representation of the Name object.
     *
     * @return String representing the full name
     */
    @Override
    public String toString() {
        StringBuilder fullName = new StringBuilder(firstName());

        for (int name = 1; name < names.length; name++) {
            // Add a space before all names after the first.
            fullName.append(' ').append(names[name]);
        }

        return fullName.toString();
    }
}
