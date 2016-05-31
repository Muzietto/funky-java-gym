/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch6._03_statements;

import net.faustinelli.funkyJavaGym.ch6._03_statements.model.Task;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertNull;

/**
 * Created by Marco Faustinelli (Muzietto) on 31/05/2016.
 */
public class TaskNullableStuffTest {

    @Test
    public void testGetNullId() throws Exception {
        Task nullIdTask = Task.newBuilder().depot("whatever").build();
        assertNull(nullIdTask.getId());
    }

    @Test
    public void testGetNullDepot() throws Exception {
        Task nullIdTask = Task.newBuilder().triggerId("whatever").build();
        assertNull(nullIdTask.getDepot());
    }

    @Test
    public void testGetNullSourceSystemIsImpossible() throws Exception {
        Task nullIdTask = Task.newBuilder().depot("whatever").build();
        try {
            assertNull(Task.TriggerId.getSystem(nullIdTask.getId()));
            assertTrue(false);
        } catch (NullPointerException npe) {
            assertTrue(true);
        }
    }
}