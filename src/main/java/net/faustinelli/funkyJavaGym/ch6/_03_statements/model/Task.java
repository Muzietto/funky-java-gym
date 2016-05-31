/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch6._03_statements.model;

import java.util.Locale;

/**
 * Created by Marco Faustinelli (Muzietto) on 31/05/2016.
 */
public class Task {
    private String depot;
    private TriggerId id;

    public String getDepot() {
        return this.depot;
    }

    private void setDepot(String depot) {
        this.depot = depot;
    }

    public TriggerId getId() {
        return id;
    }

    private void setId(TriggerId id) {
        this.id = id;
    }

    public static Task.Builder newBuilder() {
        return new Builder();
    }

    public Task.Builder update() {
        return new Builder(this);
    }

    public static class Builder {
        private Task theTask = new Task();

        private Builder() { }

        public Builder(Task task) {
            this.theTask = task;
        }

        public Builder depot(String depot) {
            this.theTask.setDepot(depot);
            return this;
        }

        public Task build() {
            return theTask;
        }

        public Builder triggerId(String sourceSystemCode) {
            this.theTask.setId(new TriggerId(new SourceSystem(sourceSystemCode)));
            return this;
        }

    }

    public static class TriggerId {
        private SourceSystem system;
        TriggerId(SourceSystem system) {
            this.system = system;
        }

        public static SourceSystem getSystem(TriggerId triggerId) {
            return triggerId.system;
        }
    }

    public static class SourceSystem {
        private final String sourceSystemCode;

        public SourceSystem(String code) {
            this.sourceSystemCode = code;
        }

        public static String getCode(SourceSystem sourceSystem) {
            return sourceSystem.sourceSystemCode;
        }
    }
}