/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch3._03_my_emailValidation;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Marco Faustinelli (Muzietto) on 13/04/2016.
 */
public class Caze<T> {

    protected final Rezzult rezzult;
    protected final Preddicate<T> preddicate;
    protected final T arg;

    public Caze(T arg, Preddicate preddicate, Rezzult rezzult) {
        this.arg = arg;
        this.preddicate = preddicate;
        this.rezzult = rezzult;
    }

    public static void match(DefaultCaze defaultCase, Caze... cazes) {

        for (Caze caze : cazes) {
            if ((Boolean) caze.preddicate.apply(caze.arg)) {
                caze.rezzult.run();
                return;
            }
        }
        defaultCase.rezzult.run();
    }

    public static class DefaultCaze extends Caze {

        public DefaultCaze(String arg, Preddicate preddicate, Rezzult rezzult) {
            super(arg, preddicate, rezzult);
        }

        public DefaultCaze(Rezzult rezzult) {
            super(null, x -> true, rezzult);
        }
    }
}
