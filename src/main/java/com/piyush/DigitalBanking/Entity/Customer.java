package com.piyush.DigitalBanking.Entity;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Document(collection = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
 private String customerId;
 private String firstName;
 private String lastName;
 @Email
 private String email;
    @NotNull
    private String phoneNumber;
 private String address;
 private LocalDate dateOfBirth;
 @NotNull
 private String panNumber;
 private String gender;
 @NotNull
 private AccountType accountType;


 public enum AccountType {
  SAVINGS,
  CURRENT,
  FIXED_DEPOSIT
 }

}
