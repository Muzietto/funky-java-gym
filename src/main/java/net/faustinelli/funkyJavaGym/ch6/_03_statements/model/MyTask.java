/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch6._03_statements.model;

import java.util.Optional;

/**
 * Created by Marco Faustinelli (Muzietto) on 01/06/2016.
 */
public class MyTask {

    private final Task task;

    public static MyTask.MyBuilder newBuilder() {
        return new MyBuilder();
    }

    private MyTask(Task task) {
        this.task = task;
    }

    public Optional<String> getDepot() {
        return Optional.ofNullable(this.task.getDepot());
    }

    public Optional<Task.TriggerId> getId() {
        return Optional.ofNullable(this.task.getId());
    }

    public static Optional<Task.SourceSystem> getSystem(Optional<Task.TriggerId> id) {
        return id.map(Task.TriggerId::getSystem);
    }

    public static class MyBuilder {
        private Task.Builder theBuilder = Task.newBuilder();

        private MyBuilder() { }

        public MyBuilder(Task task) {
            this.theBuilder
                    .depot(task.getDepot())
                    .triggerId(Task.SourceSystem
                            .getCode(Task.TriggerId
                                    .getSystem(task.getId())));;
        }

        public MyBuilder depot(String depot) {
            this.theBuilder.depot(depot);
            return this;
        }

        public MyTask build() {
            return new MyTask(theBuilder.build());
        }

        public MyBuilder triggerId(String sourceSystemCode) {
            this.theBuilder.triggerId(sourceSystemCode);
            return this;
        }
    }
}