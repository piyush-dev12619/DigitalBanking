package com.piyush.DigitalBanking.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cifTable")
public class CIF {

    @Id
     private String cifId;
     private String customerId;
     private String cifStatus;
     private LocalDate cifCreationDate;
     private String cifNumber;
}
