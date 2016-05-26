/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch3._04_EmailValidation_office;

/**
 * Created by Marco Faustinelli - Muzietto on 4/12/2016.
 */
public interface ValidationRezzult extends Runnable {

    static void errorLog(String msg) {
        System.out.println("WRONG! " + msg);
    }

    static void emailOk(String msg) {
        System.out.println("OK! " + msg);
    }

    class Suzzess implements ValidationRezzult {

        private String msg;

        public Suzzess(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            ValidationRezzult.emailOk(msg);
        }
    }

    class Faizzure implements ValidationRezzult {

        private String msg;

        public Faizzure(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            ValidationRezzult.errorLog(msg);
        }
    }
}
