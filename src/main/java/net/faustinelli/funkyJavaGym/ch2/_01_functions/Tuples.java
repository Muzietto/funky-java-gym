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
public class Tuples {

    public static double taxRate = 0.09;

    public static F<Tuple<Double, Double>, Double> addTax = tuple -> tuple._2 + tuple._2 * tuple._1;

    public static <A, B, C> F<A, F<B, C>> curryTupleFun(F<Tuple<A, B>, C> tuplefun) {

        return (A a) -> (B b) -> tuplefun.apply(new Tuple<A,B>(a, b));
    }
}
