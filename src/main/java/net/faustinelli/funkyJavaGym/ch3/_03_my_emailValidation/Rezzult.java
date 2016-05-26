/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch3._03_my_emailValidation;

/**
 * Created by Marco Faustinelli (Muzietto) on 12/04/2016.
 */
public interface Rezzult extends Runnable {
    static void logError(String msg) {
        System.out.println("WRONG: " + msg);
    }

    static void confirmValidation(String msg) {
        System.out.println("OK: " + msg);
    }

    class Suzzess implements Rezzult {

        private String msg;

        public Suzzess(String s) {
            this.msg = s;
        }

        @Override
        public void run() {
            Rezzult.confirmValidation(msg);
        }
    }

    class Faizzure implements Rezzult {
        private String msg;

        public Faizzure(String s) {
            this.msg = s;
        }

        @Override
        public void run() {
            Rezzult.logError(msg);
        }

    }
}