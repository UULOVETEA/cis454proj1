package com.orangetree.tcs.cis454proj1.acct;

/**
 * The AccountType enum lists the different types available for user accounts. Account types
 * dictate permissions and available features and layouts in the app.
 *
 * @author Terry Weiss
 * @version 0.2.0
 * @since 29Jan2018
 */

public enum AccountType {
    ADMIN,      // Handles the business aspects of the system from the company’s perspective
    TUTOR,      // Handles all use cases that are specific to the teaching aspects of the system
    STUDENT,    // Handles all use cases specific to the students’ learning from the customer side
    PARENT;     // Handles the business aspects of the system from the customer side

    /**
     * The account types are represented as a string using the type name in Name Case.
     *
     * @return Account type name as a string
     */
    @Override
    public String toString() {
        final String name = this.name();
        return name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
    }

    /**
     * Returns whether the account type is an employee account.
     *
     * @return True if the account type is an employee account.
     */
    public boolean isEmployeeAcct() {
        return (this == ADMIN) || (this == TUTOR);
    }

    /**
     * Returns whether the account type is a customer account.
     *
     * @return True if the account type is a customer account.
     */
    public boolean isCustomerAcct() {
        return (this == STUDENT) || (this == PARENT);
    }
}
