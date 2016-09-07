package org.judo.brownbag;

public interface Mapper<From, To>{
    To map(From from);
}
