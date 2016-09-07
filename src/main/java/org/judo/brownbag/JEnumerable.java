package org.judo.brownbag;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mark on 07/09/16.
 */
public class JEnumerable<T> {
    private List<T> elements;

    public JEnumerable(List<T> elements){
        this.elements = elements;
    }

    public JEnumerable<T> where(Filter<T> filter){
        ArrayList<T> filtered = new ArrayList<T>();
        for(T t: elements){
            if(filter.filter(t)){
                filtered.add(t);
            }
        }
        return new JEnumerable<T>(filtered);
    }

    public <To> JEnumerable<To> select(Mapper<T,To> mapper){
        ArrayList<To> mapped = new ArrayList<To>(elements.size());
        for(T t: elements){
            mapped.add(mapper.map(t));
        }
        return new JEnumerable<To>(mapped);
    }

    public <Target> Target aggregate(Target initial, Aggregator<T, Target> aggregator){
        Target accumulator = initial;
        for(T t: elements){
            accumulator = aggregator.aggregate(accumulator, t);
        }
        return accumulator;
    }

    public T get(int index){
        return elements.get(index);
    }

    public int size(){
        return elements.size();
    }
}

