/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch3._01_emailValidation;

import java.util.function.Consumer;

/**
 * Created by Marco Faustinelli (Muzietto) on 08/04/2016.
 */
public interface Result<T> {

    void bind(Consumer<T> success, Consumer<String> failure);

    public static <T> Result<T> failure(String msg) {
        return new Failure<T>(msg);
    }

    public static <T> Result<T> success(T value) {
        return new Success<T>(value);
    }

    class Success<T> implements Result {
        private T value;

        private Success(T value) {
            this.value = value;
        }

        @Override
        public void bind(Consumer success, Consumer failure) {
            success.accept(this.value);
        }
    }

    class Failure<T> implements Result {
        private String errorMsg;

        public Failure(String msg) {
            this.errorMsg = msg;
        }

        @Override
        public void bind(Consumer success, Consumer failure) {
            failure.accept(this.errorMsg);
        }
    }
}
