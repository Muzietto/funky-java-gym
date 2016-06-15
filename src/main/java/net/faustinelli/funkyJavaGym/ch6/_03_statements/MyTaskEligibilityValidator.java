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

import java.util.Optional;

/**
 * Created by Marco Faustinelli (Muzietto) on 01/06/2016.
 */
public class MyTaskEligibilityValidator implements EligibilityValidator<MyTask> {

//    String depot = task.getDepot();
//    return MSS_DEPOT.equals(depot) || MSAPL_DEPOT.equals(depot)
//            || (task.getId() != null && (
//            BOOKING_ENGINE_SYSTEM.equals(task.getId().getSystem(task.getId()))
//            || BACKWARD_BRIDGE_SYSTEM.equals(task.getId().getSystem(task.getId()))
//            || FORWARD_BRIDGE_SYSTEM.equals(task.getId().getSystem(task.getId()))
//            ));

    @Override
    public Boolean isEligible(MyTask myTask) {
        return Optional.ofNullable(myTask)
                .map(mt -> validator.isEligible(mt.task()))
                .orElse(Boolean.FALSE);
    }
}