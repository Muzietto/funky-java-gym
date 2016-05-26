package net.faustinelli.funkyJavaGym.ch3._05_CollectionsUtils;


import net.faustinelli.funkyJavaGym.annotations.ReturnsImmutable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Marco Faustinelli (Muzietto) on 4/15/2016.
 */
public class CollectionsUtils {

    @ReturnsImmutable
    public static <A, B> List<B> map(List<A> list, Function<A, B> fab) {
        List<B> result = new ArrayList<>();
        for (A a : list) {
            result.add(fab.apply(a));
        }
        return Collections.unmodifiableList(result);
    }

    @ReturnsImmutable
    public static <A> List<A> list() {
        return Collections.emptyList();
    }

    @ReturnsImmutable
    public static <A> List<A> list(A a) {
        return Collections.singletonList(a);
    }

    @ReturnsImmutable
    public static <A> List<A> list(List<A> list) {
        return Collections.unmodifiableList(list);
    }

    @ReturnsImmutable
    @SafeVarargs
    public static <A> List<A> list(A... as) {
        return Collections.unmodifiableList(Arrays.asList(Arrays.copyOf(as, as.length)));
    }

    public static <A> A head(List<A> list) {
        return list.get(0);
    }

    public static <A> List<A> tail(List list) {
        List result = new ArrayList<>(list);
        result.remove(0);
        return Collections.unmodifiableList(result);
    }

    public static <A> List<A> cons(A a, List<A> as) {
        List result = new ArrayList<>(as);
        result.add(0, a);
        return Collections.unmodifiableList(result);
    }

    public static <A, X> A foldl(List<X> xs, A acc, Function<A, Function<X, A>> s) {
        A result = acc;
        for (X x : xs) {
            result = s.apply(result).apply(x);
        }
        return result;
    }

    public static <V, X> V foldr(List<X> xs, V v, Function<X, Function<V, V>> f) {
        if (xs.size() == 0) {
            return v;
        }
        return f.apply(head(xs)).apply(foldr(tail(xs), v, f));
    }

    public static <X, Y> List<Y> mapWithFoldr(List<X> xs, Function<X, Y> mapper) {
        return foldr(xs, Collections.emptyList(), x -> res -> cons(mapper.apply(x), res));
    }

    public static <A> List<A> append(A a, List<A> as) {
        List<A> result = new ArrayList<A>(as);
        result.add(a);
        return Collections.unmodifiableList(result);
    }

    public static <A> List<A> reverse(List<A> as) {
        return reverse(as, Collections.EMPTY_LIST);
    }

    private static <A> List<A> reverse(List<A> as, List<A> acc) {
        return as.isEmpty()
                ? acc
                : reverse(tail(as), cons(head(as), acc));
    }

    public static <A> List<A> reverseWithFoldl(List<A> as) {
        return foldl(as, Collections.EMPTY_LIST, acc -> a -> cons(a, acc));
    }

    public static <A> List<A> reverseWithFoldr(List<A> as) {
        return foldr(as, Collections.EMPTY_LIST, a -> res -> concat(res, list(a)));
    }

    private static <A> List<A> concat(List<A> lista, List<A> listb) {
        List<A> result = new ArrayList<>(lista);
        result.addAll(listb);
        return Collections.unmodifiableList(result);
    }

    public static <A> void forEach(List<A> as, Consumer<A> effect) {
        for (A a : as) {
            effect.accept(a);
        }
    }

    public static List<Integer> range(Integer min, Integer max) {
        return unfold(min, x -> x + 1, x -> (x <= max));
    }

    /**
     * As long as the predicate is happy, grind and append results to result list
     *
     * @param seed - initial input to predicate (and to grinder)
     * @param grinder - grinder(seed) gets appended to result
     * @param predicate - predicate(seed) tells whether to keep grinding
     * @return result list
     */
    public static <A> List<A> unfold(A seed, Function<A, A> grinder, Function<A, Boolean> predicate) {
        List<A> result = Collections.EMPTY_LIST;
        A counter = seed;
        while (predicate.apply(counter)) {
            result = append(counter, result);
            counter = grinder.apply(counter);
        }
        return Collections.unmodifiableList(result);
    }

    //    The method takes a seed, a function and an number n and iterate create a list of
    //    length n by applying the function to each element to compute the next one.
    public static <A> List<A> iterateCorecursive(A seed, Function<A, A> fun, Integer n) {
        List<A> acc = new ArrayList<>(n);
        acc.add(seed);
        for (int count = 0; count < n - 1; ++count) {
            A last = acc.get(count);
            acc.add(fun.apply(last));
        }
        return acc;
    }
}