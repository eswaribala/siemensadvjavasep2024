package com.siemens.reactivespringapi.controllers;

import com.siemens.reactivespringapi.models.Trader;
import com.siemens.reactivespringapi.services.TraderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/traders")
public class TraderController {
	@Autowired
    private TraderService traderService;
 
    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody Trader trader) {
        traderService.create(trader);
    }
 
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Mono<Trader>> findById(@PathVariable("id") Integer id) {
        Mono<Trader> trader = traderService.findById(id);
        HttpStatus status = trader != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Trader>>(trader, status);
    }
 
    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Trader> findByName(@PathVariable("name") String name) {

        return traderService.findByName(name);
    }
 
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Trader> findAll() {
        Flux<Trader> emps = traderService.findAll();
        return emps;
    }
 
    @PutMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)

    public Mono<Trader> update(@RequestBody Trader trader) {

        return traderService.update(trader);
    }
 
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)

    public void delete(@PathVariable("id") Integer id) {

        traderService.delete(id).subscribe();
    }
}
