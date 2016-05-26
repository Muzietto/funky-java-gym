/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch3._03_my_emailValidation;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by Marco Faustinelli (Muzietto) on 12/04/2016.
 */
public class EmailValidation {
    static Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

    public static void main(String[] args) {

        Arrays.asList(new String[]{"this.is@my.email", null, "", "john.doe@acme.com"})
                .stream()
                .forEach(address -> validate(address));
    }

    private static void validate(String address) {

        Caze.match(
                new Caze.DefaultCaze(new Rezzult.Suzzess(address + " is valid")),
                new Caze(address, s -> (s == null), new Rezzult.Faizzure("address is null")),
                new Caze(address, s -> (s == ""), new Rezzult.Faizzure("address is empty")),
                new Caze(address, s -> (!emailPattern.matcher((CharSequence) s).matches()), new Rezzult.Faizzure(address + " is wrong"))
        );
    }
}
