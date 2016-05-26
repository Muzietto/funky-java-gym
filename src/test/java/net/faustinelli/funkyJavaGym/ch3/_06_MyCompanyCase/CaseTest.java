package com.jpmorgan.ib.cp.scpp.ame.cosmic.bridge.util;

import static com.jpmorgan.ib.amen.gateway.processor.BusinessProcessor.ProcessStatus.*;
import static com.jpmorgan.ib.amen.opstaskengine.model.AmeSubSystem.CORE;
import static com.jpmorgan.ib.cp.scpp.ame.cosmic.bridge.service.util.XmlPreprocessors.removeXmlHeader;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

import com.jpmorgan.ib.amen.gateway.processor.BusinessProcessor;
import com.jpmorgan.ib.amen.opstaskengine.AmeOpsTaskEngine;
import com.jpmorgan.ib.amen.opstaskengine.model.AmeOpsTask;
import com.jpmorgan.ib.amen.opstaskengine.model.AmeSubSystem;
import com.jpmorgan.ib.amen.opstaskengine.model.TaskReason;
import com.jpmorgan.ib.cp.scpp.ame.cosmic.bridge.communication.CosmicMessageSender;
import com.jpmorgan.ib.cp.scpp.ame.cosmic.bridge.service.util.AmeOpsTaskCosmicBridgeConjurer;
import com.jpmorgan.ib.cp.scpp.ame.cosmic.bridge.service.util.Case;
import com.jpmorgan.ib.scpp.sdi.dataobjects.Settlement;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CaseTest {

    AmeOpsTaskCosmicBridgeConjurer taskConjurer = null;
    Settlement.SettlementMission originalSettlementMission = null;
    AmeOpsTaskEngine opsTaskEngine = null;
    String transactionIdentifier = null;
    List<XXX> journaledTradeAllocs = new ArrayList<>();
    String topic = null;
    CosmicMessageSender matchedSettlementMissionToCosmicSender = null;
    Settlement.SettlementMission settlementMission = null;

    private static final String MATCHED_SETTLEMENT_MISSION_TAG = null;

    @Test
    public void testCaseForBackBridgeOneElementList() {
        journaledTradeAllocs.add(new XXX());
        BusinessProcessor.ProcessStatus result =
            getMatch(taskConjurer, originalSettlementMission, opsTaskEngine, transactionIdentifier, journaledTradeAllocs, topic,
                     matchedSettlementMissionToCosmicSender,
                     settlementMission);

        assertEquals(BusinessProcessor.ProcessStatus.SUCCESS, result);
    }

    @Test
    public void testCaseForBackBridgeTwoElementsList() {
        journaledTradeAllocs.add(new XXX());
        journaledTradeAllocs.add(new XXX());
        try {
            BusinessProcessor.ProcessStatus result =
                getMatch(taskConjurer, originalSettlementMission, opsTaskEngine, transactionIdentifier, journaledTradeAllocs, topic,
                         matchedSettlementMissionToCosmicSender,
                         settlementMission);
            assertFalse(true);
        } catch (IllegalStateException ex) {
            assertTrue(true);
        }
    }

    @Test
    public void testCaseForBackBridgeEmptyList() {

        BusinessProcessor.ProcessStatus result =
            getMatch(taskConjurer, originalSettlementMission, opsTaskEngine, transactionIdentifier, journaledTradeAllocs, topic,
                     matchedSettlementMissionToCosmicSender,
                     settlementMission);
        assertEquals(BusinessProcessor.ProcessStatus.FAIL, result);
    }

    private BusinessProcessor.ProcessStatus getMatch(AmeOpsTaskCosmicBridgeConjurer taskConjurer, Settlement.SettlementMission originalSettlementMission,
                                                     AmeOpsTaskEngine opsTaskEngine, String transactionIdentifier, List<XXX> journaledTradeAllocs,
                                                     String topic, CosmicMessageSender matchedSettlementMissionToCosmicSender,
                                                     Settlement.SettlementMission settlementMission) {
        return (BusinessProcessor.ProcessStatus) Case.match(
            new Case.DefaultCase(() -> {
//                matchedSettlementMissionToCosmicSender
//                    .sendMessageToCosmic(String.format("<%s>%n<topic>%s</topic>%n%s%n%s</%s>", MATCHED_SETTLEMENT_MISSION_TAG, topic,
//                                                       removeXmlHeader(settlementMission.toString()),
//                                                       removeXmlHeader(journaledTradeAllocs.get(0).getPayload()), MATCHED_SETTLEMENT_MISSION_TAG));
                return SUCCESS;
            }),
            new Case(x -> journaledTradeAllocs.size() > 1, () -> {
//                AmeSubSystem subSystem = CORE;
//                AmeOpsTask ameOpsTask = taskConjurer.createNewOpsTaskFromSDISettlementMission(
//                    originalSettlementMission,
//                    TaskReason.DUPLICATE_TRADE_ALLOC,
//                    subSystem);
//                opsTaskEngine.newTask(ameOpsTask);
//
                throw new IllegalStateException("More than one trade matching a SettlementMission transactionIdentifier=" + transactionIdentifier);
            }),
            new Case(x -> journaledTradeAllocs.isEmpty(), () -> {
//                storeReplayMessage(topic, new IllegalStateException("SecuritiesTransaction (Trade.alloc) " + transactionIdentifier + " not found."),
//                                   originalSettlementMission);
                return FAIL;
            })
        );
    }

    private void storeReplayMessage(String topic, IllegalStateException e, Settlement.SettlementMission originalSettlementMission) {
    }

    private class XXX {
        public String getPayload() {
            return null;
        }
    }
}
