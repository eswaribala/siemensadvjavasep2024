package com.siemens.utils;

import com.siemens.models.Product;
import com.siemens.models.ProductAggregation;
import com.siemens.models.ProductV2;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ProductCustomCollector implements Collector<ProductV2, ProductAggregation,ProductAggregation> {
    @Override
    public Supplier<ProductAggregation> supplier() {
        return ()->new ProductAggregation();
    }

    @Override
    public BiConsumer<ProductAggregation, ProductV2> accumulator() {
        return (pa,p)->{
            pa.setTotalCost(p.getCost().add(pa.getTotalCost()));
        };
    }

    @Override
    public BinaryOperator<ProductAggregation> combiner() {
        return null;
    }

    @Override
    public Function<ProductAggregation, ProductAggregation> finisher() {
        return (productAggregation ) ->{
            return productAggregation;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return  Collections.emptySet();
    }
}
