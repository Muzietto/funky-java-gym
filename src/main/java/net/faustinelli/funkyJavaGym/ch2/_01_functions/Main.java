/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch2._01_functions;

/**
 * Created by Marco Faustinelli (Muzietto) on 06/04/2016.
 */
public class Main {

    public F<Integer, F<Integer, Integer>> add = arg1 -> arg2 -> arg1 + arg2;
    public BinaryIntegerOperator addOp = arg1 -> arg2 -> arg1 + arg2;
    // 123 -> "euros" -> "123/EUR"
    public BinaryIntegerStringOperator currencyValue = (Integer amount) -> (String currency) -> amount.toString() + "/" + currency.substring(0, 3).toUpperCase();
    public F<F<Integer, Integer>,
            F<F<Integer, Integer>,
                    F<Integer, Integer>>> composeHOIntegerLambdas = fab -> fbc -> intVal -> fab.apply(fbc.apply(intVal));

    // fbc -> fab -> fac
    static <A, B, C> F<F<B, C>,
            F<F<A, B>,
                    F<A, C>>> composeHOGenericStatic() {
        return (F<B, C> fbc) -> (F<A, B> fab) -> (A a) -> fbc.apply(fab.apply(a));
    }

    public static void main(String[] args) {

        final F<Integer, Integer> triple = new F<Integer, Integer>() {
            @Override
            public Integer apply(Integer arg) {
                return 3 * arg;
            }
        };

        final F<Integer, Integer> square = new F<Integer, Integer>() {
            @Override
            public Integer apply(Integer arg) {
                return arg * arg;
            }
        };
        F<Integer, Integer> tripleSquare = new F<Integer, Integer>() {
            @Override
            public Integer apply(Integer arg) {
                return triple.apply(square.apply(arg));
            }
        };
    }

    public F<Integer, Integer> composeInteger(final F<Integer, Integer> f1, final F<Integer, Integer> f2) {
        return new F<Integer, Integer>() {
            @Override
            public Integer apply(Integer arg) {
                return f1.apply(f2.apply(arg));
            }
        };
    }

    public F<Integer, Integer> composeIntegerLambdas(final F<Integer, Integer> f1, final F<Integer, Integer> f2) {
        return arg -> f1.apply(f2.apply(arg));
    }

    public <A, B, C> F<F<B, C>,
            F<F<A, B>,
                    F<A, C>>> composeHOGenericPublic() {
        return fbc -> fab -> a -> fbc.apply(fab.apply(a));
    }
}
