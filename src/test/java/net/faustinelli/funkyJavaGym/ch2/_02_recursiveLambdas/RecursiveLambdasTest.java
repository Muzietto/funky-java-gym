/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch2._02_recursiveLambdas;

import net.faustinelli.funkyJavaGym.ch2._02_recursiveLambdas.RecursiveLambdas;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marco Faustinelli (Muzietto) on 16/04/2016.
 */
public class RecursiveLambdasTest {

    @Test
    public void testRecursiveFactorialLambda() throws Exception {
        assertEquals(new Integer(24), new RecursiveLambdas().factorialInstance.apply(4));
        assertEquals(new Integer(24), new RecursiveLambdas().factorialInstanceFinal.apply(4));
        assertEquals(new Integer(24), RecursiveLambdas.factorialStatic.apply(4));
        assertEquals(new Integer(24), RecursiveLambdas.factorialStaticFinal.apply(4));
    }

    @Test
    public void testRecursiveAddLambda() throws Exception {
        assertEquals(new Integer(24), new RecursiveLambdas().addInstanceFinal.apply(4).apply(20));
        assertEquals(new Integer(24), new RecursiveLambdas().addStaticFinal.apply(19).apply(5));
    }

}
