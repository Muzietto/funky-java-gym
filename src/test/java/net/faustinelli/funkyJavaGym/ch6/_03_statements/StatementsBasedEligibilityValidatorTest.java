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

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Marco Faustinelli (Muzietto) on 31/05/2016.
 */
public class StatementsBasedEligibilityValidatorTest {

    StatementsBasedEligibilityValidator validator = new StatementsBasedEligibilityValidator();

    @Test
    public void onlySingaporeDepotsAreEligible() throws Exception {
        Task sgTask = Task.newBuilder().depot("MSS_DEPOT").build();
        assertTrue(validator.isEligible(sgTask));

        sgTask = sgTask.update().depot("MSAPL_DEPOT").build();
        assertTrue(validator.isEligible(sgTask));
    }

    @Test
    public void nonSingaporeDepotsAreInEligible() throws Exception {
        Task sgTask = Task.newBuilder().depot("OTHER_DEPOT").build();

        assertFalse(validator.isEligible(sgTask));
    }

    @Test
    public void tasksWhereSourceSystemIsTBEAreEligible() throws Exception {
        Task sgTask = Task.newBuilder().triggerId("BOOKING_ENGINE").build();

        assertTrue(validator.isEligible(sgTask));
    }

    @Test
    public void tasksWhereSourceSystemIsCosmicBridgeAreEligible_1() throws Exception {
        Task sgTask = Task.newBuilder().triggerId("FORWARD_BRIDGE").build();

        assertTrue(validator.isEligible(sgTask));
    }

    @Test
    public void tasksWhereSourceSystemIsCosmicBridgeAreEligible_2() throws Exception {
        Task sgTask = Task.newBuilder().triggerId("BACKWARD_BRIDGE").build();

        assertTrue(validator.isEligible(sgTask));
    }

    @Test
    public void tasksWhereSourceSystemIsNotCosmicBridgeAreIneligible() throws Exception {
        Task sgTask = Task.newBuilder().triggerId("THIRD_BRIDGE").build();

        assertFalse(validator.isEligible(sgTask));
    }

    @Test
    public void tasksWhereSourceSystemIsNotTBEAndDepotIsNonSingaporeAreIneligible() throws Exception {
        Task sgTask = Task.newBuilder().depot("OTHER_DEPOT").triggerId("OTHER_SYSTEM").build();

        assertFalse(validator.isEligible(sgTask));
    }
}