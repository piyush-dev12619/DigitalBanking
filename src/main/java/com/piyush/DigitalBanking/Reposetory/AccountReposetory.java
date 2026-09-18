package com.piyush.DigitalBanking.Reposetory;


import com.piyush.DigitalBanking.Entity.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountReposetory extends org.springframework.data.mongodb.repository.MongoRepository<com.piyush.DigitalBanking.Entity.Account, String> {

    boolean existsByCustomerId(String customerId);


    Account findByCustomerId(String customerId);
}
