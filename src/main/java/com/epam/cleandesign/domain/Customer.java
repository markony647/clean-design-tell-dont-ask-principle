package com.epam.cleandesign.domain;

import com.epam.cleandesign.exceptions.NotEligibleForMortgageException;

public class Customer {

    private int id;

    private String firstName;

    private String lastName;

    private Double balance;

    private byte badCreditHistoryCount;

    public Customer(int id, String firstName, String lastName, Double balance, byte badCreditHistoryCount) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.badCreditHistoryCount = badCreditHistoryCount;
    }

    public void updateBalance(Double amount) {
        validateEligibilityForMortgage(amount);
        balance = balance + amount;
    }

    public void validateEligibilityForMortgage(Double amountRequested) {
        if (hasBadCreditHistory() || isRequestedAmountTooBig(amountRequested) || hasNegativeBalance()) {
            throw new NotEligibleForMortgageException();
        }
    }

    private boolean hasBadCreditHistory() {
        return getBadCreditHistoryCount() > 0;
    }

    private boolean hasNegativeBalance() {
        return getBalance() <= 0;
    }

    private boolean isRequestedAmountTooBig(Double amountRequested) {
        return getBalance() * 2 < amountRequested;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Double getBalance() {
        return balance;
    }

    public byte getBadCreditHistoryCount() {
        return badCreditHistoryCount;
    }


}
