package com.piyush.DigitalBanking.Service;


import com.piyush.DigitalBanking.Entity.CIF;
import com.piyush.DigitalBanking.Reposetory.CIFReposetory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Random;
import java.util.UUID;


@Slf4j
@Service
public class CIFService {

private final CIFReposetory cifReposetory;

public CIFService(CIFReposetory cifReposetory) {
        this.cifReposetory = cifReposetory;
    }

    public CIF generateCIFNumber(String customerId) {
        // Generate a random 10-digit CIF number

        try {


            CIF existingCif =

                    cifReposetory.findByCustomerId(customerId);

            if (existingCif != null) {
                log.info("CIF already exists: {}", existingCif.getCifNumber());
                return existingCif;
            }


            String cifNumber = "cif" + String.valueOf((long) (Math.random() * 10000000000L));

            log.info("Generated CIF Number: {}", cifNumber);

            // Create a new CIF object and set its properties
            CIF cif= new CIF();
            cif.setCifNumber(cifNumber);
            cif.setCustomerId(customerId);
            cif.setCifStatus("Active");
            cif.setCifCreationDate(java.time.LocalDate.now());

            //Random random = new Random();
            UUID uuid = UUID.randomUUID();
            cif.setCifId(String.valueOf(uuid));


            // Save the CIF object to the database
            cifReposetory.save(cif);
            log.info("CIF saved successfully: {}", cif.getCifId());
            return cif;



        }
        catch (Exception e) {
            log.error("Unable to save CIF", e);
            throw e; // Rethrow the exception to be handled by the exception handler
        }

    }


    @ExceptionHandler(Exception.class)
    public void handleException(Exception e) {
        log.error("Error occurred while generating CIF number", e);
    }
}
