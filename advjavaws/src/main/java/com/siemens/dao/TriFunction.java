package com.siemens.dao;

@FunctionalInterface
public interface TriFunction<R,S,T,V> {
    V apply(R r,T t, S s);
}
