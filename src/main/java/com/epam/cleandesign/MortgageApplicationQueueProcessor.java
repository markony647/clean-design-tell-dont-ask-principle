package com.epam.cleandesign;

import com.epam.cleandesign.domain.Customer;
import com.epam.cleandesign.domain.CustomerRepository;
import com.epam.cleandesign.exceptions.NotEligibleForMortgageException;
import com.epam.cleandesign.exceptions.WrongDataException;

public class MortgageApplicationQueueProcessor {

    private static final String MESSAGE_INVALID_CUSTOMER = "Customer not found!";

    private final CustomerRepository customerRepository;

    public MortgageApplicationQueueProcessor(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void processRequest(int customerId, Double amountRequested) {
        Customer customer = customerRepository.get(customerId);

        if (customer == null) {
            throw new WrongDataException(MESSAGE_INVALID_CUSTOMER);
        }

        if (isEligibleForMortgage(customer, amountRequested)) {
            customer.updateBalance(amountRequested);
        } else {
            throw new NotEligibleForMortgageException();
        }
    }

    private boolean isEligibleForMortgage(Customer customer, Double amountRequested) {
        boolean isEligibleForMortgage = false;

        if (customer.getBadCreditHistoryCount() == 0 && customer.getBalance() > 0) {
            isEligibleForMortgage = customer.getBalance() * 2 >= amountRequested;
        }

        return isEligibleForMortgage;
    }

}
