package org.judo.brownbag;

public interface Aggregator<Source, Target>{
    Target aggregate(Target target, Source source);
}
