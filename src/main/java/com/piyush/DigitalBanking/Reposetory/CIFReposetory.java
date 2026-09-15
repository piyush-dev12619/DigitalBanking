package com.piyush.DigitalBanking.Reposetory;

import com.piyush.DigitalBanking.Entity.CIF;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CIFReposetory extends MongoRepository<CIF, String> {

    CIF findByCustomerId(String customerId);

}
