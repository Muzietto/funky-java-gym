/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch6._03_statements;

import net.faustinelli.funkyJavaGym.ch6._03_statements.model.MyTask;

import java.util.Optional;

/**
 * Created by Marco Faustinelli (Muzietto) on 01/06/2016.
 */
public class WrappingEligibilityValidator implements EligibilityValidator<MyTask> {
    private NullProneEligibilityValidator validator = new NullProneEligibilityValidator();

    public Boolean isEligible(MyTask myTask) {
        return Optional.ofNullable(myTask)
                .map(mt -> validator.isEligible(mt.task()))
                .orElse(Boolean.FALSE);
    }
}