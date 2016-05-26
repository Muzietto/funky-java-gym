/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch4._02_TailCallLambdas;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Marco Faustinelli (Muzietto) on 17/04/2016.
 */
public class TailCallLambdasTest {
    private List<Integer> ints;
    private int size;

    @Before
    public void setUp(){
        this.ints = new ArrayList<>();
        this.size = 100000;
        for (int count = 1; count <= size; ++count) {
            this.ints.add(1);
        }
    }

    @Test
    public void testAddLambdaTCBestSyntax() throws Exception {
        Integer result = TailCallLambdas.addLambdaTCBest.apply(3).apply(100000000);
        assertEquals(new Integer(100000003), result);
    }

    @Test
    public void testSumList() throws Exception {
        try {
            Integer totall = TailCallLambdas.sumList(this.ints);
            assertTrue("sumList should overflow", false);
        } catch (Throwable t) {
            assertTrue(true);
        }
    }

    @Test
    public void testSumListTC() throws Exception {
        Integer totall = TailCallLambdas.sumListTC(this.ints);
        assertEquals(new Integer(this.size), totall);
    }

    @Test
    public void testSumListLambdaTC() throws Exception {
        Integer totall = TailCallLambdas.sumListLambdaTC.apply(this.ints);
        assertEquals(new Integer(this.size), totall);
    }
}
