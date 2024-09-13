package com.siemens.reactivespringapi.repositories;

import com.siemens.reactivespringapi.models.Trader;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
@Repository
public interface TraderRepository extends ReactiveMongoRepository<Trader, Integer> {
    @Query("{ 'name': ?0 }")
    Flux<Trader> findByName(final String name);
}