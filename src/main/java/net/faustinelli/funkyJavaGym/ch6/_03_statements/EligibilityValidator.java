/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch6._03_statements;

import net.faustinelli.funkyJavaGym.ch6._03_statements.model.Task;

/**
 * Created by Marco Faustinelli (Muzietto) on 15/06/2016.
 */
public interface EligibilityValidator<T> {

    static final String MSS_DEPOT = "MSS_DEPOT";
    static final String MSAPL_DEPOT = "MSAPL_DEPOT";
    static final String BOOKING_ENGINE = "BOOKING_ENGINE";
    static final String FORWARD_BRIDGE = "FORWARD_BRIDGE";
    static final String BACKWARD_BRIDGE = "BACKWARD_BRIDGE";
    static final Task.SourceSystem BOOKING_ENGINE_SYSTEM = Task.newBuilder().triggerId(BOOKING_ENGINE).build().getId().getSystem(Task.newBuilder().triggerId(BOOKING_ENGINE).build().getId());
    static final Task.SourceSystem BACKWARD_BRIDGE_SYSTEM = Task.newBuilder().triggerId(FORWARD_BRIDGE).build().getId().getSystem(Task.newBuilder().triggerId(FORWARD_BRIDGE).build().getId());
    static final Task.SourceSystem FORWARD_BRIDGE_SYSTEM = Task.newBuilder().triggerId(BACKWARD_BRIDGE).build().getId().getSystem(Task.newBuilder().triggerId(BACKWARD_BRIDGE).build().getId());

    Boolean isEligible(T task);
}
