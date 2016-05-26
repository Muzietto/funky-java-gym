/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch3._02_emailValidation;

import net.faustinelli.funkyJavaGym.ch2._01_functions.Tuple;
import net.faustinelli.funkyJavaGym.ch3._01_emailValidation.Result;

import java.util.function.Supplier;

/**
 * Created by Marco Faustinelli (Muzietto) on 08/04/2016.
 */
public class Case<T> extends Tuple<Supplier<Boolean>, Supplier<Result<T>>> {

    private Case(Supplier<Boolean> booleanSupplier, Supplier<Result<T>> resultSupplier) {
        super(booleanSupplier, resultSupplier);
    }

    public static <T> Case<T> matchCase(Supplier<Boolean> condition, Supplier<Result<T>> value) {
        return new Case<>(condition, value);
    }

    public static <T> DefaultCase<T> matchCase(Supplier<Result<T>> value) {
        // () -> true :: Supplier<Boolean>
        return new DefaultCase<>(() -> true, value);
    }

    // varargs must be LAST in the signature!!
    @SafeVarargs
    public static <T> Result<T> match(DefaultCase<T> defaultCase, Case<T>... matchers) {
        for (Case<T> aCase : matchers) {
            if (aCase._1.get()) return aCase._2.get();
        }
        return defaultCase._2.get();
    }

    private static class DefaultCase<T> extends Case<T> {
        private DefaultCase(Supplier<Boolean> booleanSupplier, Supplier<Result<T>> resultSupplier) {
            super(booleanSupplier, resultSupplier);
        }
    }
}
