/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch4._04_StackSafe;

import net.faustinelli.funkyJavaGym.ch3._05_CollectionsUtils.CollectionsUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static net.faustinelli.funkyJavaGym.ch4._04_StackSafe.StackSafe.foldLeft;
import static net.faustinelli.funkyJavaGym.ch4._04_StackSafe.StackSafe.foldLeftTC;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marco Faustinelli (Muzietto) on 19/04/2016.
 */
public class StackSafeTest {
    private int hundred_millions = 100000000;
    private List<Integer> hundredMillionInts = new ArrayList<>();
    private int one_million = 100000;
    private List<Integer> oneMillionInts = new ArrayList<>();
    private int ten_thousand = 10000;
    private List<Integer> tenThousandInts = new ArrayList<>();
    private int one_thousand = 1000;
    private List<Integer> oneThousandInts = new ArrayList<>();
    private int five = 5;
    private List<Integer> fiveInts = new ArrayList<>();

    {
        for (int counter = 1; counter <= this.hundred_millions; ++counter) {
            this.hundredMillionInts.add(1);
        }
    }

    {
        for (int counter = 1; counter <= this.one_million; ++counter) {
            this.oneMillionInts.add(1);
        }
    }

    {
        for (int counter = 1; counter <= this.ten_thousand; ++counter) {
            this.tenThousandInts.add(1);
        }
    }

    {
        for (int counter = 1; counter <= this.one_thousand; ++counter) {
            this.oneThousandInts.add(1);
        }
    }

    {
        for (int counter = 1; counter <= this.five; ++counter) {
            this.fiveInts.add(counter);
        }
    }

    @Test
    public void testCorecursiveFoldLeft() throws Exception {
        assertEquals(new Integer(this.hundred_millions), CollectionsUtils.foldl(this.hundredMillionInts, 0, x -> y -> x + y));
    }

    @Test
    public void testFoldLeft() throws Exception {
        try {
            final Integer actual = foldLeft(this.oneMillionInts, 0, x -> y -> x + y);
            assertTrue("foldLeft should throw", false);
        } catch (Throwable e) {
            assertTrue(true);
        }
    }

    @Test
    public void testFoldLeftTC() throws Exception {
        assertEquals(new Integer(this.one_million), foldLeftTC(this.oneMillionInts, 0, x -> y -> x + y));
    }

    @Test
    public void testCorecursiveRange() throws Exception {
        int sizze = 10000;
        List<Integer> rrr = CollectionsUtils.range(1, sizze);
        assertEquals(new Integer(sizze), new Integer(rrr.size()));
    }

    @Test
    public void testRecursiveRange() throws Exception {
        int sizze = 10000;
        try {
            List<Integer> rrr = StackSafe.range(1, sizze);
            assertTrue("recursive range should throw", false);
        } catch (Throwable e) {
            assertTrue(true);
        }
    }

    @Test
    public void testRangeTC() throws Exception {
        int sizze = 10000;
        List<Integer> rrr = StackSafe.rangeTC(sizze, 2 * sizze);
        assertEquals(new Integer(sizze), new Integer(rrr.size()));
        assertEquals(new Integer(sizze), rrr.get(0));
    }

    @Test
    public void testTotalSizeWithRecursiveFoldRight() throws Exception {
        Function<Integer, Function<Integer, Integer>> lunghezze = x -> res -> res + 1;
        try {
            Integer actual = StackSafe.foldRight(this.tenThousandInts, 0, lunghezze);
            assertTrue("recursive fold right should throw", false);
        } catch (Throwable t) {
            assertTrue(true);
        }
    }

    @Test
    public void testTotalSizeWithNaiveTailRecursiveFoldRight() throws Exception {
        Function<Integer, Function<Integer, Integer>> lunghezze = x -> res -> res + 1;
        try {
            Integer actual = StackSafe.foldRightXXX(this.tenThousandInts, 0, lunghezze);
            assertTrue("recursive fold right should throw", false);
        } catch (Throwable t) {
            assertTrue(true);
        }
    }

    @Test
    public void testTotalSizeWithLazyTailRecursiveFoldRight() throws Exception {
        Function<Integer, Function<Integer, Integer>> lunghezze = x -> res -> res + 1;
        assertEquals(new Integer(this.ten_thousand), StackSafe.foldRightTC(this.tenThousandInts, 0, lunghezze));
    }

    @Test
    public void testPaintTheFoldRight() throws Exception {
        Function<Integer,Function<String,String>> prettyPrint = x -> res -> { return "{" + x + " + " + res + "}";  };
        System.out.println(StackSafe.foldRightTC(this.fiveInts, "0", prettyPrint));
    }


    @Test
    public void testComposeAllFunctions() throws Exception {
        List<Function<Integer, Integer>> funs = new ArrayList<>();
        for (int count = 0; count < this.five + 1; ++count) {
            funs.add(x -> x + 2);
        }
        Function<Integer, Integer> actual = StackSafe.foldRight(funs, x -> x, f -> res -> d -> f.apply(res.apply(d)));
        assertEquals(new Integer(12), actual.apply(0));
    }

}
