package com.piyush.DigitalBanking.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    private String accountId;

    private String customerId;
    private String cifNumber;

    private String accountNumber;

    private AccountType accountType;

    private AccountStatus accountStatus;

    private LocalDate accountCreationDate;

    public enum AccountType {
        SAVINGS,
        CURRENT,
        FIXED_DEPOSIT
    }

    public enum AccountStatus {
        CREATED,
        ACTIVE,
        BLOCKED,
        CLOSED
    }
}
