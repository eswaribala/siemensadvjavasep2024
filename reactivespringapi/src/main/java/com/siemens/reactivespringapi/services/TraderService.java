package com.siemens.reactivespringapi.services;

import com.siemens.reactivespringapi.models.Trader;
import com.siemens.reactivespringapi.repositories.TraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class TraderService implements ITraderService {
    
   @Autowired
   TraderRepository traderRepo;

   public void create(Trader trader) {

       traderRepo.save(trader).subscribe();
   }

   public Mono<Trader> findById(Integer id) {

       return traderRepo.findById(id);
   }

   public Flux<Trader> findByName(String name) {

       return traderRepo.findByName(name);
   }

   public Flux<Trader> findAll() {
       return traderRepo.findAll();
   }

   public Mono<Trader> update(Trader trader) {
       return traderRepo.save(trader);
   }

   public Mono<Void> delete(Integer id) {
       return traderRepo.deleteById(id);
   }

}