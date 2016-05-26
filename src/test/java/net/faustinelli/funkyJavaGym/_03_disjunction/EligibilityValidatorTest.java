package com.jpmorgan.ib.amen.opstaskengine.validation;

import static org.junit.Assert.*;

import com.jpmorgan.ib.amen.opstaskengine.model.AmeOpsTask;

import org.junit.Test;
import org.mockito.Mockito;

public class OpsTaskEligibilityValidatorTest {

    OpsTaskEligibilityValidator testObj = new OpsTaskEligibilityValidator();

    @Test
    public void onlySingaporeDepotsAreEligible() throws Exception {
        AmeOpsTask sgTask = AmeOpsTask.newBuilder().depot("0G1").build();

        assertTrue(testObj.isEligible(sgTask));

        sgTask = sgTask.update().depot("0G3").build();

        assertTrue(testObj.isEligible(sgTask));
    }

    @Test
    public void nonSingaporeDepotsAreInEligible() throws Exception {
        AmeOpsTask sgTask = AmeOpsTask.newBuilder().depot("00P").build();

        assertFalse(testObj.isEligible(sgTask));
    }

    @Test
    public void tasksWhereSourceSystemIsTBEAreEligible() throws Exception {
        AmeOpsTask sgTask = Mockito.mock(AmeOpsTask.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(sgTask.getTriggerId().getSourceSystem()).thenReturn("TRADE_BOOKING_ENGINE");

        assertTrue(testObj.isEligible(sgTask));
    }

    @Test
    public void tasksWhereSourceSystemIsCosmicBridgeAreEligible_1() throws Exception {
        AmeOpsTask sgTask = Mockito.mock(AmeOpsTask.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(sgTask.getTriggerId().getSourceSystem()).thenReturn("COSMIC_FORWARD_BRIDGE");

        assertTrue(testObj.isEligible(sgTask));
    }

    @Test
    public void tasksWhereSourceSystemIsCosmicBridgeAreEligible_2() throws Exception {
        AmeOpsTask sgTask = Mockito.mock(AmeOpsTask.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(sgTask.getTriggerId().getSourceSystem()).thenReturn("COSMIC_BACKWARD_BRIDGE");

        assertTrue(testObj.isEligible(sgTask));
    }

    @Test
    public void tasksWhereSourceSystemIsNotCosmicBridgeAreIneligible() throws Exception {
        AmeOpsTask sgTask = Mockito.mock(AmeOpsTask.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(sgTask.getTriggerId().getSourceSystem()).thenReturn("COSMIC_THIRD_BRIDGE");

        assertFalse(testObj.isEligible(sgTask));
    }

    @Test
    public void tasksWhereSourceSystemIsNotTBEAndDepotIsNonSingaporeAreIneligible() throws Exception {
        AmeOpsTask sgTask = Mockito.mock(AmeOpsTask.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(sgTask.getTriggerId().getSourceSystem()).thenReturn("OTHER_SYSTEM");
        Mockito.when(sgTask.getDepot()).thenReturn("OTHER_DEPOT");

        assertFalse(testObj.isEligible(sgTask));
    }
}
