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

    public Task task() {
        return new Task.Builder(this.task).build();
    }

    public MyBuilder update() {
        return new MyBuilder(this.task);
    }

    public static class MyBuilder {
        private Task.Builder theBuilder = Task.newBuilder();

        private MyBuilder() { }

        public MyBuilder(Task task) {
            Optional.ofNullable(task.getDepot())
                    .map(d -> { this.theBuilder.depot(d); return d; });
            Optional.ofNullable(task.getId())
                    .map(Task.TriggerId::getSystem)
                    .map(Task.SourceSystem::getCode)
                    .map(ssc -> { this.theBuilder.triggerId(ssc); return ssc; });
        }

        public MyBuilder depot(String depot) {
            if (depot != null) {
                this.theBuilder.depot(depot);
            }
            return this;
        }

        public MyTask build() {
            return new MyTask(theBuilder.build());
        }

        public MyBuilder triggerId(String sourceSystemCode) {
            if (sourceSystemCode != null) {
                this.theBuilder.triggerId(sourceSystemCode);
            }
            return this;
        }
    }
}