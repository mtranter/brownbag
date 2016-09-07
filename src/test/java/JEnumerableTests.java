import org.judo.brownbag.JEnumerable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by mark on 07/09/16.
 */
public class JEnumerableTests {

    static void TestFilter(){

        ArrayList<String> elems = new ArrayList<String>(5);
        elems.add("a");
        elems.add("b");
        elems.add("c");
        elems.add("d");
        elems.add("e");

        JEnumerable<String> enumerable = new JEnumerable<String>(elems);
        JEnumerable<String> filtered = enumerable.where(s -> s == "b" || s == "e");

        assert filtered.size() == 2;
        assert filtered.get(0) == "b";
        assert filtered.get(1) == "e";
    }

    static void TestAggregate(){

        ArrayList<String> elems = new ArrayList<String>(5);
        elems.add("a");
        elems.add("b");
        elems.add("c");
        elems.add("d");
        elems.add("e");

        JEnumerable<String> enumerable = new JEnumerable<String>(elems);
        String contatted = enumerable.aggregate("", (t, s) -> t + s);

        assert contatted == "abcde";
    }
}
