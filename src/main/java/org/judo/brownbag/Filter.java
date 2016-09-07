package org.judo.brownbag;

public interface Filter<T> {
    boolean filter(T t);
}
