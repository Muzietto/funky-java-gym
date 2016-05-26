/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch2._01_functions;

/**
 * Created by Marco Faustinelli (Muzietto) on 07/04/2016.
 */
public class Curry {
    public static <T, U, V> F<T, F<U, V>> curry2(F2<T, U, V> ftu_v) {
        return (T t) -> (U u) -> ftu_v.apply(t, u);
    }

    public static <A, B, C> F<B, C> partialA(F<A, F<B, C>> fabc, A a) {
        return fabc.apply(a);
    }

    public static <A, B, C> F<A, C> partialB(F<A, F<B,C>>fabc, B b) {
        return a -> fabc.apply(a).apply(b);
    }

    public static <A,B,C> F<B,F<A,C>>swapArgs(F<A,F<B,C>>fabc) {
        return b -> a -> fabc.apply(a).apply(b);
    }
}
