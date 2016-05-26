/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch4._01_TailCall;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Marco Faustinelli (Muzietto) on 16/04/2016.
 */
public class TailCallsTest {

    @Test
    public void testRawRicorsiveAdd() throws Exception {
        assertEquals(new Integer(20000003), TailCalls.add(20000000, 3));
        try {
            assertEquals(new Integer(100003), TailCalls.add(3, 100000));
            assertFalse("should overflow", true);
        } catch (Throwable e) {
            assertTrue(true);
        }
    }

    @Test
    public void testTailCallAdd() throws Exception {
        TailCall<Integer> tailCall = TailCalls.addTC(2, 3);
        while (tailCall.isSuspend()) {
            tailCall = tailCall.resume();
        }
        assertEquals(new Integer(5), tailCall.eval());
        TailCall<Integer> tailCall2 = TailCalls.addTC(3, 100000000);
        while (tailCall2.isSuspend()) {
            tailCall2 = tailCall2.resume();
        }
        assertEquals(new Integer(100000003), tailCall2.eval());
    }

    @Test
    public void testTailCallBetterSyntax() throws Exception {
        Integer result = TailCalls.addTC(3, 100000000).eval();
        assertEquals(new Integer(100000003), result);
    }

    @Test
    public void testTailCallBestSyntax() throws Exception {
        Integer result = TailCalls.addTCBest(3, 100000000);
        assertEquals(new Integer(100000003), result);
    }

    @Test
    public void testTailCallBestSyntaxWithInnerClass() throws Exception {
        Integer result = TailCalls.addTCBestWithInnerClass(3, 100000000);
        assertEquals(new Integer(100000003), result);
    }
}