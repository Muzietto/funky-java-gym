/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch3._05_CollectionsUtils;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marco Faustinelli (Muzietto) on 15/04/2016.
 */
public class CollectionsUtilsTest {
    @Test
    public void testSumWithFoldl() throws Exception {
        Function<Integer, Function<Integer, Integer>> curriedSum = acc -> x -> acc + x;
        List<Integer> xs = Arrays.asList(new Integer[]{1, 2, 3});
        assertEquals(new Integer(6), CollectionsUtils.foldl(xs, new Integer(0), curriedSum));
    }

    @Test
    public void testTotalLengthWithFoldl() throws Exception {
        Function<Integer, Function<String, Integer>> lunghezze = acc -> x -> acc + x.length();
        List<String> stringhe = Arrays.asList(new String[]{"ecco", "il", "treno"});
        assertEquals(new Integer(11), CollectionsUtils.foldl(stringhe, 0, lunghezze));
    }

    @Test
    public void testMap() throws Exception {
        Function<String, Integer> length = x -> x.length();
        List<String> stringhe = Arrays.asList(new String[]{"ecco", "il", "treno"});
        List<Integer> lunghezze = CollectionsUtils.map(stringhe, length);
        assertEquals(new Integer(5), lunghezze.get(2));
    }

    @Test
    public void testMapWithFoldr() throws Exception {
        Function<String, Integer> length = x -> x.length();
        List<String> stringhe = Arrays.asList(new String[]{"ecco", "il", "treno"});
        List<Integer> lengths = CollectionsUtils.mapWithFoldr(stringhe, length);
        assertEquals(3, lengths.size());
        assertEquals(new Integer(4), lengths.get(0));
        assertEquals(new Integer(5), lengths.get(2));
    }

    @Test
    public void testSumWithFoldr() throws Exception {
        Function<Integer, Function<Integer, Integer>> summ = x -> res -> x + res;
        List<Integer> xs = Arrays.asList(new Integer[]{1, 2, 3});
        assertEquals(new Integer(6), CollectionsUtils.foldr(xs, 0, summ));
    }

    @Test
    public void testTotalLengthWithFoldr() throws Exception {
        Function<String, Function<Integer, Integer>> lunghezze = x -> res -> res + x.length();
        List<String> stringhe = Arrays.asList(new String[]{"ecco", "il", "treno"});
        assertEquals(new Integer(11), CollectionsUtils.foldr(stringhe, 0, lunghezze));
    }

    @Test
    public void testReverseList() throws Exception {
        List<String> stringhe = Arrays.asList(new String[]{"ecco", "il", "treno", "lungo", "lungoo"});
        List<String> revva = CollectionsUtils.reverse(stringhe);
        assertEquals(5, revva.size());
        assertEquals("lungoo", revva.get(0));
        assertEquals("treno", revva.get(2));
    }

    @Test
    public void testReverseListWithFoldl() throws Exception {
        List<String> stringhe = Arrays.asList(new String[]{"ecco", "il", "treno", "lungo", "lungoo"});
        List<String> revva = CollectionsUtils.reverseWithFoldl(stringhe);
        assertEquals(5, revva.size());
        assertEquals("lungoo", revva.get(0));
        assertEquals("treno", revva.get(2));
    }

    @Test
    public void testReverseListWithFoldr() throws Exception {
        List<String> stringhe = Arrays.asList(new String[]{"ecco", "il", "treno", "lungo", "lungoo"});
        List<String> revva = CollectionsUtils.reverseWithFoldr(stringhe);
        assertEquals(5, revva.size());
        assertEquals("lungoo", revva.get(0));
        assertEquals("treno", revva.get(2));
    }

    @Test
    public void testRange() throws Exception {
        List<Integer> r1_6 = CollectionsUtils.range(1,6);
        assertEquals(6, r1_6.size());
        assertEquals(new Integer(1), r1_6.get(0));
        assertEquals(new Integer(4), r1_6.get(3));
    }

    @Test
    public void testUnfold() throws Exception {
        List<Integer> r1_6 = CollectionsUtils.unfold(1, x -> x + 1, x -> (x <= 6));
        assertEquals(6, r1_6.size());
        assertEquals(new Integer(1), r1_6.get(0));
        assertEquals(new Integer(4), r1_6.get(3));

    }

    @Test
    public void testIterateCorecursive() throws Exception {
        List<Integer> range = CollectionsUtils.iterateCorecursive(0, x -> x + 1, 100);
        assertEquals(100, range.size());
        assertEquals(new Integer(26), range.get(26));
    }
}
