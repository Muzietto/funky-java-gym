/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch4._02_TailCallLambdas;

import net.faustinelli.funkyJavaGym.annotations.NoTailCall;
import net.faustinelli.funkyJavaGym.annotations.IsTailCall;
import net.faustinelli.funkyJavaGym.annotations.NonRecursive;
import net.faustinelli.funkyJavaGym.annotations.Recursive;
import net.faustinelli.funkyJavaGym.ch4._01_TailCall.TailCall;

import java.util.List;
import java.util.function.Function;

import static net.faustinelli.funkyJavaGym.ch3._05_CollectionsUtils.CollectionsUtils.*;
import static net.faustinelli.funkyJavaGym.ch4._01_TailCall.TailCall.*;

/**
 * Created by Marco Faustinelli (Muzietto) on 17/04/2016.
 */
public class TailCallLambdas {

    /*
     * REM: the archetype of them all
     */
    @Recursive
    @IsTailCall
    public static Integer add(int x, int y) {
        return (y == 0)
                ? x
                : add(++x, --y);
    }

    /*
     * REM: how to use a helper function to hide TC.eval()
     * NB: you can use an inner class inside the main lambda to hide the helper
     */
    @NonRecursive
    @IsTailCall
    public static Integer addTCBest(final int x, final int y) {
        return addTCBestHelper(x , y).eval();
    }
    private static TailCall<Integer> addTCBestHelper(final int x, final int y) {
        return (y == 0)
                ? ret(x)
                : sus(() -> addTCBestHelper(x + 1, y - 1));
    }

    /*
     * how to use helper functions in the case of lambdas
     * NB: inner classes are MANDATORY here!
     */
    @NonRecursive
    @IsTailCall
    public static Function<Integer, Function<Integer, Integer>> addLambdaTCBest = x -> y -> {
        class AddLambdaTCBestInner {
            Function<Integer, Function<Integer, TailCall<Integer>>> addLambdaTCBestInner
                    = _x -> _y -> (_y == 0)
                    ? ret(_x)
                    : sus(() -> this.addLambdaTCBestInner.apply(_x + 1).apply(_y - 1));
        }
        return new AddLambdaTCBestInner().addLambdaTCBestInner.apply(x).apply(y).eval();
    };

    /*
     * REM: the archetype of them all
     */
    @Recursive
    @NoTailCall
    public static Integer sumList(List<Integer> xs) {
        return (xs.isEmpty())
                ? 0
                : head(xs) + sumList(tail(xs));
    }

    @NonRecursive
    @IsTailCall
    public static Integer sumListTC(List<Integer> xs) {
        class SumListHelper {
            private TailCall<Integer> sumListHelper(List<Integer> _xs, Integer _acc) {
                return _xs.isEmpty()
                        ? ret(_acc)
                        : sus(() -> sumListHelper(tail(_xs), _acc + head(_xs)));
            }
        }
        return new SumListHelper().sumListHelper(xs, 0).eval();
    }

    @NonRecursive
    @IsTailCall
    public static Function<List<Integer>,Integer> sumListLambdaTC = xs -> {
        class SumListLambdaTCHelper { // inner class mandatory in this case!
            public Function<List<Integer>,Function<Integer,TailCall<Integer>>> sumListLambdaTCHelper = _xs -> _acc ->
                _xs.isEmpty()
                    ? ret(_acc)
                    : sus(() -> this.sumListLambdaTCHelper.apply(tail(_xs)).apply(_acc + head(_xs)));
        }
        return new SumListLambdaTCHelper().sumListLambdaTCHelper.apply(xs).apply(0).eval();
    };

    /*
     * a collection of fruitless attempts, which work with methods but not with functions

    public static Function<Integer,Function<Integer,Integer>> addLambdaStatic;
    static {
        addLambdaStatic  = x -> y -> (y == 0)
                ? new Return<Integer>(x)
                : new Suspend<Integer>(() -> TailCallLambdas.addLambdaStatic.apply(x + 1).apply(y - 1));
    }

    public Function<Integer,Function<Integer,Integer>> addLambdaInstance;
    {
        addLambdaInstance  = x -> y -> (y == 0)
                ? new Return<Integer>(x)
                : new Suspend<Integer>(() -> this.addLambdaInstance.apply(x + 1).apply(y - 1));
    }
     */
}
