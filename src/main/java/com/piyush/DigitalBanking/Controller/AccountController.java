package com.piyush.DigitalBanking.Controller;


import com.piyush.DigitalBanking.Entity.Account;
import com.piyush.DigitalBanking.Service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@Tag(name = "Account Controller", description = "APIs for Account Creation")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/createAccount")
    public ResponseEntity<?> createAccount(
            @RequestParam String customerId) {
        try {
            Account createdAccount =
                    accountService.createAccount(customerId);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(createdAccount);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("" + e.getMessage());
        }

    }

@GetMapping("customerId/{customerId}")

    public ResponseEntity<?> getAccountByCustomerId(@PathVariable String customerId) {
        try {
            Account account = accountService.getAccountByCustomerId(customerId);
            if (account == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Account not found for customerId: " + customerId);
            }
            return ResponseEntity.ok(account);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while retrieving the account.");
        }
    }

}

//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
//        return ResponseEntity.status(HttpStatus.CONFLICT)
//                .body(e.getMessage());
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleException(Exception e) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body("An error occurred while creating the account.");
//    }
//}
