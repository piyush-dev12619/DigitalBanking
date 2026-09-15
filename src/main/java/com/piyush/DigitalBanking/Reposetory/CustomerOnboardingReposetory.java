package com.piyush.DigitalBanking.Reposetory;

import com.piyush.DigitalBanking.Entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerOnboardingReposetory extends MongoRepository<Customer, String> {


    Optional<Customer> findByPanNumber(String panNumber);
}
