package com.jpmorgan.ib.cp.scpp.ame.cosmic.bridge.service.util;

import org.springframework.jms.core.ProducerCallback;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class Case<T,U> implements Supplier<T> {
    protected final U arg;
    protected final Predicate<U> predicate;
    protected final Supplier<T> consequence;

    public Case(U arg, Predicate<U> predicate, Supplier<T> consequence) {
        this.consequence = consequence;
        this.arg = arg;
        this.predicate = predicate;
    }

    public Case(Predicate<U> predicate, Supplier<T> consequence) {
        this(null, predicate, consequence);
    }

    @Override
    public T get() {
        return consequence.get();
    }

    public static <T> T match(DefaultCase<T> defaultCase, Case... cases) {
        for (Case caze : cases) {
            if (caze.predicate.test(caze.arg)) {
                return (T) caze.consequence.get();
            }
        }
        return (T) defaultCase.consequence.get();
    }

    public static class DefaultCase<T> extends Case {

        public DefaultCase(Supplier<T> consequence) {
            super(null, x -> true, consequence);
        }
    }
}
