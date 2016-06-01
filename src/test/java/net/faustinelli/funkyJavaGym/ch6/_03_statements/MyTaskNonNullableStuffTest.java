/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch6._03_statements;

import net.faustinelli.funkyJavaGym.ch6._03_statements.model.MyTask;
import net.faustinelli.funkyJavaGym.ch6._03_statements.model.Task;
import org.junit.Test;

import java.util.Optional;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

/**
 * Created by Marco Faustinelli (Muzietto) on 31/05/2016.
 */
public class MyTaskNonNullableStuffTest {

    @Test
    public void testGetNullId() throws Exception {
        MyTask nullIdTask = MyTask.newBuilder().depot("whatever").build();
        assertNotNull(nullIdTask.getId());
        assertFalse(nullIdTask.getId().isPresent());
    }

    @Test
    public void testGetNullDepot() throws Exception {
        MyTask nullIdTask = MyTask.newBuilder().triggerId("whatever").build();
        assertNotNull(nullIdTask.getDepot());
        assertFalse(nullIdTask.getDepot().isPresent());
    }

    @Test
    public void testGetSourceSystemIsPossible() throws Exception {
        MyTask nullIdTask = MyTask.newBuilder().depot("whatever").build();
        final Optional<Task.SourceSystem> sourceSystem = MyTask.getSystem(nullIdTask.getId());
        assertNotNull(sourceSystem);
        assertFalse(sourceSystem.isPresent());
    }
}