/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch6._02_trainWreck;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Marco Faustinelli (Muzietto) on 09/05/2016.
 */
public class DinastyTest {

    @Test
    public void testDinastyWithoutNullPointers() throws Exception {
        Grandpa grandpa = new Grandpa(new Grandpa.Father(new Grandpa.Father.Son(new Grandpa.Father.Son.GrandSon("gigino"))));

        assertEquals("gigino", Grandpa.grandSonName(grandpa));
    }

    @Test
    public void testNoFather() throws Exception {
        Grandpa grandpa = new Grandpa(null);

        assertEquals("no descendant", Grandpa.grandSonName(grandpa));
    }

    @Test
    public void testNoSon() throws Exception {
        Grandpa grandpa = new Grandpa(new Grandpa.Father(null));

        assertEquals("no descendant", Grandpa.grandSonName(grandpa));
    }

    @Test
    public void testNoGrandSon() throws Exception {
        Grandpa grandpa = new Grandpa(new Grandpa.Father(new Grandpa.Father.Son(null)));
        String actual = "no descendant";
        try {
            actual = Grandpa.grandSonName(grandpa);
            fail();
        } catch (Exception e) {
            assertEquals("no descendant", actual);
            assertTrue(true);
        }
    }
}
