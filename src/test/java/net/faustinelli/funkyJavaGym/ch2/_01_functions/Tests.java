/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch2._01_functions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marco Faustinelli (Muzietto) on 06/04/2016.
 */
public class Tests {
    @Test
    public void testComposeFunctions() throws Exception {
        F<Integer, Integer> triple = new F<Integer, Integer>() {
            @Override
            public Integer apply(Integer arg) {
                return arg * 3;
            }
        };

        F<Integer, Integer> tripleLambda = arg -> arg * 3;

        F<Integer, Integer> square = new F<Integer, Integer>() {
            @Override
            public Integer apply(Integer arg) {
                return arg * arg;
            }
        };

        F<Integer, Integer> squareLambda = arg -> arg * arg;
        F<Integer, String> euros = amount -> amount.toString() + "/EUR";
        F<Double, Integer> truncate = doub -> doub.intValue();

        F<Integer, Integer> tripleSquare = new Main().composeInteger(square, triple);
        F<Integer, Integer> tripleSquareLambda = new Main().composeIntegerLambdas(squareLambda, tripleLambda);
        F<Integer, Integer> tripleSquareHO = new Main().composeHOIntegerLambdas.apply(squareLambda).apply(tripleLambda);

        assertEquals(new Integer(81), tripleSquare.apply(new Integer(3)));
        assertEquals(new Integer(81), tripleSquareLambda.apply(new Integer(3)));
        assertEquals(new Integer(81), tripleSquareHO.apply(new Integer(3)));

        assertEquals(new Integer(5), new Main().add.apply(3).apply(2));
        assertEquals(new Integer(5), new Main().addOp.apply(2).apply(3));

        F<Integer, Integer> squaredTripleHOComposeGenericStatic = Main.<Integer, Integer, Integer>composeHOGenericStatic().apply(square).apply(triple);
        F<Integer, Integer> squaredTripleHOComposeGenericPublic = new Main().<Integer, Integer, Integer>composeHOGenericPublic().apply(squareLambda).apply(triple);
        assertEquals(new Integer(36), squaredTripleHOComposeGenericStatic.apply(new Integer(2)));
        assertEquals(new Integer(144), squaredTripleHOComposeGenericPublic.apply(new Integer(4)));

        F<Integer, Integer> tripledSquare = new Main().<Integer, Integer, Integer>composeHOGenericPublic().apply(triple).apply(squareLambda);
        assertEquals(new Integer(12), tripledSquare.apply(new Integer(2)));

        assertEquals("123/EUR", new Main().currencyValue.apply(123).apply("euros"));

        F<Integer, String> tripleEuros = new Main().<Integer, Integer, String>composeHOGenericPublic().apply(euros).apply(triple);
        assertEquals("33/EUR", tripleEuros.apply(11));

        assertEquals("123/EUR", ComposedFunction.compose(euros, truncate).apply(123.123));
        assertEquals("123/EUR", ComposedFunction.compose(euros, truncate).apply(123.923));
        assertEquals("123/EUR", ComposedFunction.andThen(truncate, euros).apply(123.123));
        assertEquals("123/EUR", ComposedFunction.andThen(truncate, euros).apply(123.923));
    }

    @Test
    public void testTuples() throws Exception {
        assertEquals(new Double(13.08), Tuples.addTax.apply(new Tuple<>(Tuples.taxRate, 12.0)));

        F2<Double, Double, Double> addTaxa = (taxRate, price) -> price + price * Tuples.taxRate;
        assertEquals(new Double(13.08), addTaxa.apply(Tuples.taxRate, 12.0));

    }

    @Test
    public void testCurrying() throws Exception {
        F<Double, F<Double, Double>> curriedTupledTaxes = Tuples.curryTupleFun(Tuples.addTax);

        assertEquals(new Double(13.08), curriedTupledTaxes.apply(Tuples.taxRate).apply(12.0));
        assertEquals(new Double(13.08), Curry.swapArgs(curriedTupledTaxes).apply(12.0).apply(Tuples.taxRate));
        //F<Double, F<Double, Double>> xxx = Curry.curry2(Tuples.addTax);
    }

    @Test
    public void testPartialApplication() throws Exception {
        F<Integer, F<Integer, Integer>> divide = x -> y -> x / y;

        F<Integer, Integer> divide34 = Curry.partialA(divide, 34);
        assertEquals(new Integer(17), divide34.apply(2));

        F<Integer, Integer> divideBy34 = Curry.partialB(divide, 34);
        assertEquals(new Integer(2), divideBy34.apply(68));
    }

    @Test
    public void testComposedByInterfaceDefaults() throws Exception {
        F<Integer, String> euros = amount -> amount.toString() + "/EUR";
        F<Double, Integer> truncate = doub -> doub.intValue();
        assertEquals("123/EUR", euros.compose(truncate).apply(123.123));
        assertEquals("123/EUR", euros.compose(truncate).apply(123.923));
        assertEquals("123/EUR", truncate.andThen(euros).apply(123.123));
        assertEquals("123/EUR", truncate.andThen(euros).apply(123.923));

    }
}
