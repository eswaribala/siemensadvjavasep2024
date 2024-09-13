package com.rps.banking.utility;

import reactor.core.publisher.Flux;

public class FluxOperatorsDemo {
    public static void main(String[] args){
        Flux<Integer> numbers = Flux.just(1, 2, 3, 4, 5);

        Flux<Integer> doubledNumbers = numbers.handle((number, sink) -> {
            sink.next(number * 2);
        });
        doubledNumbers.subscribe(System.out::println);
    }
}
