/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch6._03_statements;

import net.faustinelli.funkyJavaGym.ch6._03_statements.model.Identifier;
import net.faustinelli.funkyJavaGym.ch6._03_statements.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Created by Marco Faustinelli (Muzietto) on 19/05/2016.
 */
public class EligibilityValidator {

    private static final String MSS_DEPOT = "MSS_DEPOT";
    private static final String MSAPL_DEPOT = "MSAPL_DEPOT";
    private static final String BOOKING_ENGINE = "BOOKING_ENGINE";
    private static final String FORWARD_BRIDGE = "FORWARD_BRIDGE";
    private static final String BACKWARD_BRIDGE = "BACKWARD_BRIDGE";

    public boolean isEligible(Task task) {
    /*        
        String depot = task.getDepot();
        return MSS_DEPOT.equals(depot) || MSAPL_DEPOT.equals(depot)
           || (task.getId() != null && (
                BOOKING_ENGINE.equals(task.getId().getSystem())
                || BACKWARD_BRIDGE.equals(task.getId().getSystem())
                || FORWARD_BRIDGE.equals(task.getId().getSystem())
        ));
    */

        Optional<String> depot = Optional.ofNullable(task.getDepot());
        Optional<String> sourceSystem = Optional.ofNullable(task.getId())
            .map(Identifier::getSystem);

        Boolean isValidDepot = new Statements<String>(
            MSS_DEPOT::equals,
            MSAPL_DEPOT::equals
        ).disjunction(depot);

        Boolean isValidSystem = new Statements<String>(
            BOOKING_ENGINE::equals,
            BACKWARD_BRIDGE::equals,
            FORWARD_BRIDGE::equals
        ).disjunction(sourceSystem);

        return isValidDepot || isValidSystem;
    }
}