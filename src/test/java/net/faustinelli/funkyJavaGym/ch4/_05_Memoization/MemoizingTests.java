/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch4._05_Memoization;

import net.faustinelli.funkyJavaGym.ch2._01_functions.Tuple;
import org.junit.Test;

import java.math.BigInteger;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Marco Faustinelli (Muzietto) on 5/3/2016.
 */
public class MemoizingTests {
    @Test
    public void testMemoizingFibboMixingConcerns() {
        assertTrue(MemoizingFibbo.fibboTCWithAllConcernsInOneSingleMess.apply(120).indexOf("8670007398507948658051921") != -1);
    }

    @Test
    public void testMemoizingFibboProducingTheList() {
        List<BigInteger> fibbies = MemoizingFibbo.fibboTCList.apply(120);
        assertTrue(fibbies.get(fibbies.size() - 1).compareTo(new BigInteger("8670007398507948658051921")) == 0);
    }

    @Test
    public void testMemoizingFibboWithSeparatedConcerns() {
        assertTrue(MemoizingFibbo.fibboTCWithSeparationOfConcerns.apply(120).indexOf("8670007398507948658051921") != -1);
    }

    @Test
    public void testMemoizingFibboUsingTriples() {
        assertTrue(MemoizingFibbo.memoizedFibboTCUsingTriples.apply(120).compareTo(new BigInteger("8670007398507948658051921")) == 0);
    }

    @Test
    public void testFibboTupler() throws Exception {
        Integer sizze = 120;
        List<Tuple<BigInteger, BigInteger>> fibboTupleds = MemoizingFibbo.fibboTupleds(sizze);
        BigInteger fibbo120 = fibboTupleds.get(sizze - 1)._1;
        assertEquals(new BigInteger("5358359254990966640871840"), fibbo120);
    }

    @Test
    public void testFibboTupledStringer() throws Exception {
        assertEquals("1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,", MemoizingFibbo.fibboTupledsStringed(16));
    }

    @Test
    public void testMemoizedDoubler() throws Exception {
        assertTrue(MemoizedDoubler.cache.size() == 0);
        assertEquals(new Integer(12), MemoizedDoubler.doubler.apply(6));
        assertTrue(MemoizedDoubler.cache.size() == 1);
        assertEquals(new Integer(12), MemoizedDoubler.doubler.apply(6));
        assertTrue(MemoizedDoubler.cache.size() == 1);
    }

    @Test
    public void testMemoizzer1() throws Exception {
        Function<Integer, Integer> memoizzedDoubler = Memoizzer.memoizze(x -> x * 2);
        assertEquals(new Integer(12), memoizzedDoubler.apply(6));
        assertEquals(new Integer(12), memoizzedDoubler.apply(6));
        // cannot verify the cache though...
    }

    @Test
    public void testMemoizzer2() throws Exception {
        Function<Integer, Function<Integer,Integer>> memoizzedDoubler = Memoizzer.memoizze(x -> y -> (x * 2) + y);
        Function<Integer, Function<Integer,Integer>> memoizzedAdderToDoubler = Memoizzer.memoizze(memoizzedDoubler);
        assertEquals(new Integer(13), memoizzedAdderToDoubler.apply(6).apply(1));
        assertEquals(new Integer(14), memoizzedAdderToDoubler.apply(6).apply(2));
        assertEquals(new Integer(14), memoizzedAdderToDoubler.apply(1).apply(12));
        assertEquals(new Integer(4), memoizzedAdderToDoubler.apply(1).apply(2));
        // cannot verify the cache though...
    }
}
