/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch4._03_Fibonaccio;

import net.faustinelli.funkyJavaGym.annotations.IsTailCall;
import net.faustinelli.funkyJavaGym.annotations.NoTailCall;
import net.faustinelli.funkyJavaGym.annotations.NonRecursive;
import net.faustinelli.funkyJavaGym.annotations.Recursive;
import net.faustinelli.funkyJavaGym.ch4._01_TailCall.TailCall;

import java.math.BigInteger;

import static net.faustinelli.funkyJavaGym.ch4._01_TailCall.TailCall.*;

/**
 * Created by Marco Faustinelli (Muzietto) on 4/18/2016.
 */
public class Fibonaccio {

    @Recursive
    @NoTailCall
    public static Integer fibonacciNaive(int n) {
        if (n == 0) return 1;
        if (n == 1) return 1;
        return fibonacciNaive(n - 2) + fibonacciNaive(n - 1);
    }

    @Recursive
    @NoTailCall
    public static Integer fibonacciWithAccumulators(int n) {
        class FibonacciWithAccumulatorsHelper {
            private Integer fibonacciWithAccumulatorsHelper(int n, int fibnmin2, int fibnmin1) {
                if (n == 0) return fibnmin1;
                return fibonacciWithAccumulatorsHelper(n - 1, fibnmin1 + fibnmin2, fibnmin2);
            }
        }
        if (n == 0) return 1;
        if (n == 1) return 1;
        return new FibonacciWithAccumulatorsHelper().fibonacciWithAccumulatorsHelper(n, 1, 1);
    }

    @Recursive
    @IsTailCall
    public static BigInteger bigFibonacciWithAccumulators(BigInteger n) {
        class BigFibonacciWithAccumulatorsHelper {
            private BigInteger bigFibonacciWithAccumulatorsHelper(BigInteger _n, BigInteger _fibnmin2, BigInteger _fibnmin1) {
                if (_n == BigInteger.ZERO) return _fibnmin1;
                return bigFibonacciWithAccumulatorsHelper(_n.subtract(BigInteger.ONE), _fibnmin1.add(_fibnmin2), _fibnmin2);
            }
        }
        if (n == BigInteger.ZERO) return BigInteger.ONE;
        if (n == BigInteger.ONE) return BigInteger.ONE;
        return new BigFibonacciWithAccumulatorsHelper().bigFibonacciWithAccumulatorsHelper(n, BigInteger.ONE, BigInteger.ONE);
    }

    @NonRecursive
    @IsTailCall
    public static BigInteger bigFibonacciTailCall(BigInteger n) {
        class BigFibonacciTailCallHelper {
            private TailCall<BigInteger> bigFibonacciTailCallHelper(BigInteger _n, BigInteger _fibnmin2, BigInteger _fibnmin1) {
                if (_n == BigInteger.ZERO) return ret(_fibnmin1);
                return sus(() -> bigFibonacciTailCallHelper(_n.subtract(BigInteger.ONE), _fibnmin1.add(_fibnmin2), _fibnmin2));
            }
        }
        if (n == BigInteger.ZERO) return BigInteger.ONE;
        if (n == BigInteger.ONE) return BigInteger.ONE;
        return new BigFibonacciTailCallHelper().bigFibonacciTailCallHelper(n, BigInteger.ONE, BigInteger.ONE).eval();
    }
}
