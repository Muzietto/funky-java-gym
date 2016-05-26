/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch4._05_Memoization;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Created by Marco Faustinelli (Muzietto) on 06/05/2016.
 */
public class Memoizzer<A, B> {
    private final Map<A, B> cache = new ConcurrentHashMap<A, B>();
    private Function<Function<A, B>, Function<A, B>> memoizzeInnard
            = funz -> a -> this.cache.computeIfAbsent(a, funz);

    private Memoizzer() {
    }

    public static <A, B> Function<A, B> memoizze(Function<A, B> fun) {
        return new Memoizzer<A, B>().memoizzeInnard.apply(fun);
    }
}