package com.siemens.utils;


import com.siemens.exceptions.ThrowingConsumer;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamException {
    public static void main(String[] args){
        List<Integer> integers = Arrays.asList(3, 9, 7, 10, 20);
        integers.forEach(lambdaWrapper(i -> System.out.println(50 / i)));
        List<String> names=Arrays.asList("Param","bala","viki","ram",null);
        names.forEach(throwingConsumerWrapper(i -> System.out.println(i.length()<=3)));

    }
    static Consumer<Integer> lambdaWrapper(Consumer<Integer> consumer) {
        return i -> {
            try {
                consumer.accept(i);
            } catch (ArithmeticException e) {
                System.err.println(
                        "Arithmetic Exception occured : " + e.getMessage());
            }
        };
    }

    static <T> Consumer<T> throwingConsumerWrapper(ThrowingConsumer<T, Exception> throwingConsumer) {
        return i -> {
            try {
               throwingConsumer.accept(i);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
