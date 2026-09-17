package com.piyush.DigitalBanking.Service;


import com.piyush.DigitalBanking.Entity.Account;
import com.piyush.DigitalBanking.Entity.CIF;
import com.piyush.DigitalBanking.Entity.Customer;
import com.piyush.DigitalBanking.Reposetory.AccountReposetory;
import com.piyush.DigitalBanking.Reposetory.CIFReposetory;
import com.piyush.DigitalBanking.Reposetory.CustomerOnboardingReposetory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class AccountCreationService {

    private final AccountReposetory accountReposetory;
    private final CIFReposetory cifReposetory;
    private final CustomerOnboardingReposetory customerOnboardingReposetory;
    private final EmailService emailService;

    public AccountCreationService(
            AccountReposetory accountReposetory,
            CIFReposetory cifReposetory,
            CustomerOnboardingReposetory customerOnboardingReposetory,
            EmailService emailService) {

        this.accountReposetory = accountReposetory;
        this.cifReposetory = cifReposetory;
        this.customerOnboardingReposetory = customerOnboardingReposetory;
        this.emailService = emailService;
    }

    public Account createAccount(String customerId) {

        try {

            // Prevent duplicate account creation for the same customerId

            if (accountReposetory.existsByCustomerId(customerId)) {
                throw new IllegalArgumentException(
                        "Account already exists for customerId: " + customerId
                );
            }

            log.info("inside createAccount method for customerId: {}", customerId);
            log.info("Creating account for customerId: {}", customerId);
            // Check if an account already exists for the given customerId
//            if (accountReposetory.findById(customerId).isPresent()) {
//                log.warn("Account already exists for customerId: {}", customerId);
//                return null;
//            }

            String accountNumber = "ACC" + String.valueOf((long) (Math.random() * 1000000L));
            log.info("Generated Account Number: {}", accountNumber);

            CIF cif = cifReposetory.findByCustomerId(customerId);

            log.info("CIF object retrieved: {}", cif);

            if (cif == null) {
                throw new IllegalArgumentException(
                        "No CIF found for customerId: " + customerId
                );
            }

            String cifNumber = cif.getCifNumber();

            log.info("Retrieved CIF Number: {}", cifNumber);
            Customer customer = customerOnboardingReposetory.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + customerId));
            Enum<Customer.AccountType> accType = customer.getAccountType();
            log.info("Retrieved Customer Account Type: {}", customer.getAccountType());


            Account acc = new Account();
            acc.setAccountCreationDate(java.time.LocalDate.now());
            acc.setAccountNumber(accountNumber);
            acc.setCustomerId(customerId);
            acc.setCifNumber(cifNumber);
            acc.setAccountType(Account.AccountType.valueOf(String.valueOf(accType)));
            acc.setAccountStatus(Account.AccountStatus.valueOf("ACTIVE"));


            log.info("Creating account for customerId: {}, accountNumber: {}, cifNumber: {}, accountType: {}, accountStatus: {}",
                    customerId, accountNumber, cifNumber, acc.getAccountType(), acc.getAccountStatus());


            emailService.sendAccountCreationEmail(
                    "piyushkumar@newgensoft.com",
                    customer.getFirstName(),
                    acc.getAccountNumber(),
                    acc.getCifNumber()
            );
              return accountReposetory.save(acc);

        } catch (Exception e) {
            log.error("Error creating account for customerId: {}", customerId, e);
            throw e;
        }
        }

    }
