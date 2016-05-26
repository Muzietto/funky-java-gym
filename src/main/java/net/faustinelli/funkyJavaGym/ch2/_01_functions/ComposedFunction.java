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
public class ComposedFunction {

    public static <A, B, C> F<A, C> compose(F<B, C> fbc, F<A, B> fab) {
        return a -> fbc.apply(fab.apply(a));
    }

    public static <A, B, C> F<A, C> andThen(F<A, B> fab, F<B, C> fbc) {
        return compose(fbc, fab);
    }
}
