package com.piyush.DigitalBanking.Controller;


import com.piyush.DigitalBanking.Entity.Customer;
import com.piyush.DigitalBanking.Reposetory.CustomerOnboardingReposetory;
import com.piyush.DigitalBanking.Service.CustomerOnboardingService;
import com.sun.net.httpserver.HttpServer;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@Slf4j
public class CustomerController {

private final CustomerOnboardingService customerOnboardingService;

    public CustomerController(CustomerOnboardingService customerOnboardingService, CustomerOnboardingReposetory CustomerOnboardingRepository) {
        this.customerOnboardingService = customerOnboardingService;
    }




    @PostMapping()
    public ResponseEntity<Customer> onboardcustomer(@Valid @RequestBody Customer customer){

         log.info("inside CustomerController onboardcustomer method");

        Customer savedcustomer = customerOnboardingService.saveCustomerDetails(customer);

        log.info("Customer onboarded successfully: {}", savedcustomer.getCustomerId());

        return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED).body(savedcustomer);


    }


    @GetMapping("/customerId/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String customerId) {
        log.info("inside CustomerController getCustomerById method");
        Customer customer = customerOnboardingService.getCustomerById(customerId);
        if (customer == null) {
            log.warn("Customer not found with ID: {}", customerId);
            return ResponseEntity.notFound().build();
        }
        log.info("Customer found with ID: {}", customerId);
        return ResponseEntity.ok(customer);
    }
}
