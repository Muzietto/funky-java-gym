/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch6._01_naiveOptional;

/**
 * Created by Marco Faustinelli (Muzietto) on 08/05/2016.
 */
public class Optionall<T> {

    public static class Some<T> extends Optionall<T> {
        private T value;

        public Some(T value) {
            this.value = value;
        }
    }

    public static class None extends Optionall {

    }
}
