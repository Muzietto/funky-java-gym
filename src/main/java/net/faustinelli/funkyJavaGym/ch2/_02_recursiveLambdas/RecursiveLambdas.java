/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch2._02_recursiveLambdas;

import net.faustinelli.funkyJavaGym.annotations.Recursive;

import java.util.function.Function;

/**
 * Created by Marco Faustinelli (Muzietto) on 16/04/2016.
 */
public class RecursiveLambdas {
    @Recursive
    public static final Function<Integer, Integer> factorialStaticFinal
            = n -> (n <= 1) ? 1 : n * RecursiveLambdas.factorialStaticFinal.apply(n - 1);
    @Recursive
    public static final Function<Integer, Function<Integer, Integer>> addStaticFinal
            = x -> y -> (y == 0) ? x : RecursiveLambdas.addStaticFinal.apply(x + 1).apply(y - 1);
    @Recursive
    public static Function<Integer, Integer> factorialStatic;
    static {
        factorialStatic = n -> (n <= 1) ? 1 : n * factorialStatic.apply(n - 1);
    }

    @Recursive
    public final Function<Integer, Integer> factorialInstanceFinal
            = n -> (n <= 1) ? 1 : n * this.factorialInstanceFinal.apply(n - 1);
    @Recursive
    public final Function<Integer, Function<Integer, Integer>> addInstanceFinal
            = x -> y -> (y == 0) ? x : this.addInstanceFinal.apply(x + 1).apply(y - 1);
    @Recursive
    public Function<Integer, Integer> factorialInstance;
    {
        factorialInstance = n -> (n <= 1) ? 1 : n * factorialInstance.apply(n - 1);
    }
}