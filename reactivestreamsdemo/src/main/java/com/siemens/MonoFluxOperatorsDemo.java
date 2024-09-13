package com.siemens;
import com.siemens.models.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
public class MonoFluxOperatorsDemo {
    public static void main(String[] args) {
        // Create a Mono that emits a single value
        Mono<String> monoValue = Mono.just("Hello");

        // Using map to transform the value of Mono
        System.out.println("Using Mono with map:");
        monoValue.map(String::toUpperCase)
                .subscribe(System.out::println);

        // Create a Flux from a List
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        // Create a Flux from the List
        Flux<Integer> fluxNumbers = Flux.fromIterable(numbers);

        // Using map to square each number
        System.out.println("\nUsing Flux with map to square each number:");
        fluxNumbers.map(n -> n * n)
                .subscribe(System.out::println);

        // Using filter to keep only even numbers
        System.out.println("\nUsing Flux with filter to keep only even numbers:");
        fluxNumbers.filter(n -> n % 2 == 0)
                .subscribe(System.out::println);

        // Using flatMap to transform data and flatten the result
        System.out.println("\nUsing Flux with flatMap to transform and flatten:");
        Flux<String> fluxOfStrings = fluxNumbers.flatMap(n -> Mono.just("Number: " + n));
        fluxOfStrings.subscribe(System.out::println);

        // Demonstrating Mono with flatMap
        System.out.println("\nUsing Mono with flatMap to get a transformed Reactive type:");
        Mono<String> transformedMono = monoValue.flatMap(value -> Mono.just(value + " World!"));
        transformedMono.subscribe(System.out::println);


        // Create a mock user repository
        List<User> users = Arrays.asList(
                new User(1, "Alice", "alice@example.com"),
                new User(2, "Bob", "bob@example.com"),
                new User(3, "Charlie", "charlie@example.com")
        );

        // Create a mock order service
        OrderService orderService = new OrderService();

        // Using Flux to simulate fetching users asynchronously
        Flux.fromIterable(users)
                .flatMap(user -> getUserWithOrders(user, orderService))
                .subscribe(result -> System.out.println(result));
    }
    private static Mono<String> getUserWithOrders(User user, OrderService orderService) {
        // Fetch orders for each user asynchronously
        return orderService.getOrders(user.getId())
                .collectList() // Collect the orders into a List
                .map(orders -> user.getName() + " (" + user.getEmail() + ") has orders: " + orders);
    }

    // Simulated Order Service
    static class OrderService {
        public Flux<String> getOrders(int userId) {
            // Mock orders based on userId
            switch (userId) {
                case 1:
                    return Flux.just("Order1", "Order2");
                case 2:
                    return Flux.just("Order3");
                case 3:
                    return Flux.just("Order4", "Order5", "Order6");
                default:
                    return Flux.empty();
            }
        }
    }
}
