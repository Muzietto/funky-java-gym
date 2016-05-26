/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch3._04_EmailValidation_office;

/**
 * Created by Marco Faustinelli - Muzietto on 4/13/2016.
 */
public class Caze<T> implements Runnable {

    protected final Predizzate<T> predizzate;
    protected final ValidationRezzult rezzult;

    public Caze(Predizzate<T> predizzate,ValidationRezzult rezzult) {
        this.predizzate = predizzate;
        this.rezzult = rezzult;
    }

    public Caze(ValidationRezzult rezzult) {
        this(new Predizzate<T>((T) null, x -> true), rezzult);
    }


    public static void match(DefaultCaze deffault, Caze... cazes) {

        for (Caze caze : cazes) {
            if (caze.predizzate.getAsBoolean()) {
                caze.rezzult.run();
                return;
            }
        }
        deffault.run();
    }

    @Override
    public void run() {
        if (this.predizzate.getAsBoolean()) {
            rezzult.run();
        }
    }

    static class DefaultCaze extends Caze {
        public DefaultCaze(ValidationRezzult rezzult) {
            super(rezzult);
        }
    }
}
