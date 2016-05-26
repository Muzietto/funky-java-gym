/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch3._05_CollectionsUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marco Faustinelli (Muzietto) on 15/04/2016.
 */
public class CollectionsUtilsEffectTest {
    private OutputStream refOut;
    private OutputStream refErr;
    private OutputStream out;
    private OutputStream err;

    @Before
    public void setupBaos() {
        out = new ByteArrayOutputStream();
        err = new ByteArrayOutputStream();
        refOut = System.out;
        refErr = System.err;
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    @After
    public void teardownBaos() {
        System.setOut(new PrintStream(refOut));
        System.setErr(new PrintStream(refErr));
    }

    @Test
    public void testPlainForEach() throws Exception {
        CollectionsUtils.forEach(Arrays.asList(new String[]{"a", "b", "c"}), (s) -> System.out.print(s));
        assertEquals("abc", out.toString());
    }

    @Test
    public void testFoldLotsOfEffects() throws Exception {
        Runnable one = () -> System.out.print("a");
        Runnable two = () -> System.out.print("b");
        Runnable three = () -> System.out.print("c");
        Runnable four = () -> System.out.print("d");
        Runnable five = () -> System.out.print("e");
        List<Runnable> runnables = Arrays.asList(new Runnable[]{one, two, three, four, five});
        Function<Runnable, Function<Runnable, Runnable>> ffolder = r1 -> r2 -> () -> { r1.run(); r2.run(); };
        Runnable allOfThem = CollectionsUtils.foldl(runnables, () -> {}, ffolder);

        allOfThem.run();
        assertEquals("abcde", out.toString());
    }

    @Test
    public void testEffectsComposition() throws Exception {
        Runnable one = () -> System.out.print("a");
        Runnable two = () -> System.out.print("b");
        Function<Runnable, Function<Runnable, Runnable>> compose = r1 -> r2 -> () -> {
            r1.run();
            r2.run();
        };

        assertEquals("", out.toString());
        Runnable composed = compose.apply(one).apply(two);
        compose.apply(composed).apply(composed).run();
        assertEquals("abab", out.toString());
    }

    @Test
    public void testMultiplePlainEffects() throws Exception {
        Runnable one = () -> System.out.print("a");
        Runnable two = () -> System.out.print("b");

        assertEquals("", out.toString());
        one.run();
        two.run();
        assertEquals("ab", out.toString());
    }

}
