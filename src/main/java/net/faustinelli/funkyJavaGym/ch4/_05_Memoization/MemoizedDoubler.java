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
public class MemoizedDoubler {
    // cache should be private, of course...
    public static Map<Integer, Integer> cache = new ConcurrentHashMap<>();
    public static Function<Integer, Integer> doubler = x -> cache.computeIfAbsent(x, y -> 2 * y);
}
