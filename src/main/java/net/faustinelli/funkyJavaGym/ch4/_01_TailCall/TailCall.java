/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch4._01_TailCall;

import java.util.function.Supplier;

/**
 * Created by Marco Faustinelli (Muzietto) on 16/04/2016.
 */
public abstract class TailCall<T> {

    public abstract TailCall<T> resume();
    public abstract T eval();
    public abstract boolean isSuspend();

    public static <T> TailCall<T> ret(T val) {
        return new Return<T>(val);
    }

    public static <T> TailCall<T> sus(Supplier<TailCall<T>> fun) {
        return new Suspend<T>(fun);
    }

    public static class Return<T> extends TailCall<T> {
        private final T t;

        public Return(T t) {
            this.t = t;
        }

        @Override
        public TailCall<T> resume() {
            throw new RuntimeException("Returns cannot resume");
        }

        @Override
        public T eval() {
            return t;
        }

        @Override
        public boolean isSuspend() {
            return false;
        }
    }

    public static class Suspend<T> extends TailCall<T> {
        private final Supplier<TailCall<T>> resume;

        public Suspend(Supplier<TailCall<T>> resume) {
            this.resume = resume;
        }

        @Override
        public TailCall<T> resume() {
            return resume.get();
        }

        @Override
        public T eval() {
            TailCall<T> tc = this;
            while (tc.isSuspend()) {
                tc = tc.resume();
            }
            return tc.eval();
        }

        @Override
        public boolean isSuspend() {
            return true;
        }
    }
}