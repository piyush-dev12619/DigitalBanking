package com.piyush.DigitalBanking.Controller;


import com.piyush.DigitalBanking.Entity.CIF;
import com.piyush.DigitalBanking.Entity.Customer;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.piyush.DigitalBanking.Service.CustomerOnboardingService;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/api/cif")
@Tag(name = "CIF Controller", description = "APIs for Customer Information File (CIF) Management")
public class CIFController {

     private final CustomerOnboardingService customerOnboardingService;
    private final com.piyush.DigitalBanking.Service.CIFService cifService;

    public CIFController(CustomerOnboardingService customerOnboardingService, com.piyush.DigitalBanking.Service.CIFService cifService) {
        this.customerOnboardingService = customerOnboardingService;
        this.cifService = cifService;
    }

  @PostMapping
  public ResponseEntity<CIF> generateCIFNumber(@RequestBody String customerId) {

try{

    log.info("inside CIFController");



    CIF cif = cifService.generateCIFNumber(customerId);
    // log.info("CIF generated successfully: {}", cif.getCifId());
    return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED).body(cif);

}
   catch (Exception e) {
    log.error("Error occurred while generating CIF number", e);
    return ResponseEntity.status(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR).build();
   }

    }

@GetMapping("/panNumber")

    public ResponseEntity<?>  getCIFByPanNumber(@RequestParam String panNumber) {

       try {
           if (panNumber == null || panNumber.trim().isEmpty()) {
               throw new IllegalArgumentException("PAN Number cannot be null or empty");

           }

           Customer customer = customerOnboardingService.getCustomerBypanNumber(panNumber);
           log.info("inside CIFController getCIFByPanNumber method");
           log.info(customer.getPanNumber());


           if (panNumber.equals(customer.getPanNumber())) {

               CIF cif = cifService.generateCIFNumber(customer.getCustomerId());
               Map<String, Object> response = new HashMap<>();
               response.put("status","exist");
               response.put("message", "Customer already exist with PAN Number: " + panNumber);
               response.put("panNumber","panNumber");
               return ResponseEntity.status(org.springframework.http.HttpStatus.OK).body(response);
           } else {
               log.warn("Customer not found with PAN Number: {}", panNumber);
               Map<String, Object> response = new HashMap<>();
               response.put("status","Failed");
               response.put("message", "Customer not found with PAN Number: " + panNumber);
             response.put("panNumber","panNumber");
             return ResponseEntity.status(org.springframework.http.HttpStatus.NOT_FOUND).body(response);

           }

       }

       catch (Exception e) {
           log.error("Error occurred while retrieving CIF by PAN number", e);
           return ResponseEntity.status(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR).build();
       }


    }

}
