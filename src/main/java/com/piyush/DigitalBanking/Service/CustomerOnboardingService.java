package com.piyush.DigitalBanking.Service;

import com.piyush.DigitalBanking.Entity.Customer;
import com.piyush.DigitalBanking.Reposetory.CustomerOnboardingReposetory;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Service
public class CustomerOnboardingService {

    private final CustomerOnboardingReposetory customerOnboardingReposetory;

    public CustomerOnboardingService(CustomerOnboardingReposetory customerOnboardingReposetory) {
        this.customerOnboardingReposetory = customerOnboardingReposetory;
    }


    public Customer saveCustomerDetails(Customer customer) {

        if(customer == null){
            throw new IllegalArgumentException("Customer data is null");
        }

        customerOnboardingReposetory.save(customer);
        log.info("Customer onboarded successfully: {}", customer.getCustomerId());
        return customer;
    }

    public Customer getCustomerById(String customerId) {
        log.info("inside CustomerOnboardingService getCustomerById method");
        Customer customer = customerOnboardingReposetory.findById(customerId).orElse(null);
        if (customer == null) {
            log.warn("Customer not found with ID: {}", customerId);
            return null;
        }
        log.info("Customer found with ID: {}", customerId);
        return customer;
    }

@GetMapping("/panNumber")
        public Customer getCustomerBypanNumber(String panNumber) {
        log.info("inside CustomerOnboardingService getCustomerBypanNumber method");
        Customer customer = customerOnboardingReposetory.findByPanNumber(panNumber).orElse(null);
        if (customer == null) {
            log.warn("Customer not found with PAN Number: {}", panNumber);
            return null;
        }
        log.info("Customer found with PAN Number: {}", panNumber);
        return customer;
    }



}
