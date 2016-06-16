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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Created by Marco Faustinelli (Muzietto) on 01/06/2016.
 */
public class MyTaskEligibilityValidator implements EligibilityValidator<MyTask> {
    private final List<Predicate<String>> depotMatchers;
    private final List<Predicate<Task.SourceSystem>> systemMatchers;

    public MyTaskEligibilityValidator() {
        this.depotMatchers = depotMatchers(
                MSS_DEPOT::equals,
                MSAPL_DEPOT::equals
        );
        this.systemMatchers = systemMatchers(
                BOOKING_ENGINE_SYSTEM::equals,
                BACKWARD_BRIDGE_SYSTEM::equals,
                FORWARD_BRIDGE_SYSTEM::equals
        );
    }

    private List<Predicate<String>> depotMatchers(Predicate<String>... predicates) { return Arrays.asList(predicates); }
    private List<Predicate<Task.SourceSystem>> systemMatchers(Predicate<Task.SourceSystem>... predicates) { return Arrays.asList(predicates); }

    /*
        MyTask contains:
        - Optional<String> myTask.getDepot()
        - Optional<Task.TriggerId> myTask.getId()

        Some<Task.TriggerId> contains:
        - Optional<Task.SourceSystem> MyTask.getSystem(Optional<Task.TriggerId> id)
     */
    @Override
    public Boolean isEligible(MyTask myTask) {
        return Optional.ofNullable(myTask)
                .map(mt ->
                        depotMatchers
                                .stream()
                                .map(p -> mt.getDepot().map(d -> p.test(d)).orElse(Boolean.FALSE))
                                .reduce((a, b) -> a || b).get()
                ).orElse(Boolean.FALSE)
                || Optional.ofNullable(myTask)
                .map(mt ->
                        systemMatchers
                                .stream()
                                .map(p -> MyTask.getSystem(mt.getId()).map(s -> p.test(s)).orElse(Boolean.FALSE))
                                .reduce((a, b) -> a || b).get()
                ).orElse(Boolean.FALSE);
    }
}