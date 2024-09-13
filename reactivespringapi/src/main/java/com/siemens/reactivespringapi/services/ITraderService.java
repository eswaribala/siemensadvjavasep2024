package com.siemens.reactivespringapi.services;


import com.siemens.reactivespringapi.models.Trader;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITraderService {

	void create(Trader trader);
    
    Mono<Trader> findById(Integer id);
 
    Flux<Trader> findByName(String name);
 
    Flux<Trader> findAll();
 
    Mono<Trader> update(Trader trader);
 
    Mono<Void> delete(Integer id);
}
