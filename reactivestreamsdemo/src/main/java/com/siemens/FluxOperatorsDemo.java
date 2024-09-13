package com.siemens;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class FluxOperatorsDemo {
    public static void main(String[] args) {
        // Create a Flux that emits numbers from 1 to 5
        Flux<Integer> numbers = Flux.range(1, 5);

        // Demonstrate various Flux operators
        System.out.println("Initial Numbers:");
        numbers.subscribe(System.out::println);

        // Use transform to modify the stream
        System.out.println("\nUsing Transform to Square Each Number:");
        numbers.transform(flux -> flux.map(n -> n * n))
                .subscribe(System.out::println);

        // Use filter to keep only even numbers
        System.out.println("\nUsing Filter to Keep Even Numbers:");
        numbers.filter(n -> n % 2 == 0)
                .subscribe(System.out::println);

        // Use map to convert numbers to their string representation
        System.out.println("\nUsing Map to Convert to String:");
        numbers.map(n -> "Number: " + n)
                .subscribe(System.out::println);

        // Use zip to combine two Fluxes
        System.out.println("\nUsing Zip to Combine Two Fluxes:");
        Flux<String> letters = Flux.just("A", "B", "C", "D", "E");
        Flux.zip(numbers.map(String::valueOf), letters, (num, letter) -> num + letter)
                .subscribe(System.out::println);

        // Use delayElements to simulate an asynchronous stream
        System.out.println("\nSimulating Asynchronous Stream with Delay:");
        Flux<Integer> delayedNumbers = Flux.range(1, 5).delayElements(Duration.ofSeconds(1));
        delayedNumbers.subscribe(System.out::println);

        // Keep the main thread alive to observe delayed values
        try {
            Thread.sleep(7000); // Sleep for 7 seconds to see all delayed outputs
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}