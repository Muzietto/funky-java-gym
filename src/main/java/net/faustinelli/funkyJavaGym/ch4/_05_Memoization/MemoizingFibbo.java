/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch4._05_Memoization;

import net.faustinelli.funkyJavaGym.ch2._01_functions.Tuple;
import net.faustinelli.funkyJavaGym.ch3._05_CollectionsUtils.CollectionsUtils;
import net.faustinelli.funkyJavaGym.ch4._01_TailCall.TailCall;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static net.faustinelli.funkyJavaGym.ch4._01_TailCall.TailCall.ret;
import static net.faustinelli.funkyJavaGym.ch4._01_TailCall.TailCall.sus;

/**
 * Created by Marco Faustinelli (Muzietto) on 5/3/2016.
 */
public class MemoizingFibbo {

    public static Function<Integer, String> fibboTCWithAllConcernsInOneSingleMess = n -> {
        StringBuilder result = new StringBuilder("0,\n1");
        class FibboTCHelper {
            private TailCall<StringBuilder> fibboTCHelper(Integer nn, BigInteger fibnmin2, BigInteger fibnmin1, StringBuilder acc) {
                return (nn.equals(1))
                        ? ret(acc)
                        : sus(() -> fibboTCHelper(
                        nn - 1,
                        fibnmin1.add(fibnmin2),
                        fibnmin2,
                        acc.append(",\n").append(fibnmin1.add(fibnmin2))));
            }
        }
        return new FibboTCHelper().fibboTCHelper(n, BigInteger.ONE, BigInteger.ONE, result).eval().toString();
    };

    public static Function<Integer, List<BigInteger>> fibboTCList = n -> {
        class FibboTCListHelper {
            private TailCall<List<BigInteger>> fibboTCListHelper(Integer nn, BigInteger fibnmin2, BigInteger fibnmin1, List<BigInteger> acc) {
                return (nn.equals(1))
                        ? ret(acc)
                        : sus(() -> {
                    acc.add(fibnmin1.add(fibnmin2));
                    return fibboTCListHelper(
                            nn - 1,
                            fibnmin1.add(fibnmin2),
                            fibnmin2,
                            acc);
                });
            }
        }

        ArrayList<BigInteger> fibbies = new ArrayList<>();
        fibbies.add(BigInteger.ZERO);
        fibbies.add(BigInteger.ONE);
        List<BigInteger> items = new FibboTCListHelper().fibboTCListHelper(n, BigInteger.ONE, BigInteger.ONE, fibbies).eval();
        return items;
    };

    public static Function<Integer, String> fibboTCWithSeparationOfConcerns = n -> {
        StringBuilder result = new StringBuilder("0,\n1");
        ArrayList<BigInteger> fibbies = new ArrayList<>();
        fibbies.add(BigInteger.ZERO);
        fibbies.add(BigInteger.ONE);
        List<BigInteger> items = fibboTCList.apply(n);
        return fibboListPacker(items, new StringBuilder()).eval().toString();
    };
    public static Function<Integer, BigInteger> memoizedFibboTCUsingTriples = n -> {
        class Triple<A, B, C> {
            private A a;
            private B b;
            private C c;

            private Triple(A a, B b, C c) {
                this.a = a;
                this.b = b;
                this.c = c;
            }
        }

        class memoizedFibboTCUsingTriplesHelper {
            private TailCall<Triple<Integer, BigInteger, BigInteger>> memoizedFibboTCUsingTripleHelper(Triple<Integer, BigInteger, BigInteger> input) {
                Integer nn = input.a;
                BigInteger fibnmin1 = input.c;
                BigInteger fibnmin2 = input.b;
                return (nn == 0)
                        ? ret(input)
                        : sus(() -> memoizedFibboTCUsingTripleHelper(new Triple<Integer, BigInteger, BigInteger>(nn - 1, fibnmin1.add(fibnmin2), fibnmin2)));
            }
        }
        return new memoizedFibboTCUsingTriplesHelper().memoizedFibboTCUsingTripleHelper(new Triple<>(n, BigInteger.ONE, BigInteger.ONE)).eval().c;
    };

    public static TailCall<StringBuilder> fibboListPacker(List<BigInteger> items, StringBuilder acc) {
        return (items.size() == 0)
                ? ret(acc)
                : sus(() -> {
            BigInteger item = items.remove(0);
            return fibboListPacker(items, acc.append(item).append(",\n"));
        });
    }

    public static Function<Tuple<BigInteger, BigInteger>, Tuple<BigInteger, BigInteger>> fibboTupler
            = x -> new Tuple<>(x._2, x._1.add(x._2));

    public static List<Tuple<BigInteger,BigInteger>> fibboTupleds(Integer sizze) {
        return CollectionsUtils.iterateCorecursive(
                new Tuple<BigInteger,BigInteger>(BigInteger.ONE, BigInteger.ONE),
                MemoizingFibbo.fibboTupler,
                sizze);
    }

    public static String fibboTupledsStringed(Integer sizze) {
        return CollectionsUtils.foldl(fibboTupleds(sizze), new StringBuilder(), acc -> a -> {
           acc.append(a._1).append(",");
            return acc;
        }).toString();
    }
}
