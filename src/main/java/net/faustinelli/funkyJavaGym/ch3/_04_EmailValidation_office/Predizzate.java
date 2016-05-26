/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch3._04_EmailValidation_office;

import java.util.function.BooleanSupplier;
import java.util.function.Function;

/**
 * Created by Marco Faustinelli - Muzietto on 4/15/2016.
 */
public class Predizzate<T> implements BooleanSupplier {
    private T arg;
    private Function<T, Boolean>fun;

    public Predizzate(T arg, Function<T, Boolean> fun) {
        this.arg = arg;
        this.fun = fun;
    }

    @Override
    public boolean getAsBoolean() {
        return this.fun.apply(this.arg);
    }
}
