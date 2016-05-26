/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch3._04_EmailValidation_office;

import java.util.regex.Pattern;

/**
 * Created by Marco Faustinelli - Muzietto on 4/12/2016.
 */
public class EmailValidation {

    static Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

    public static void main(String[] args) {

        validate("this.is@my.email");
        validate(null);
        validate("");
        validate("john.doe@acme.com");
    }

    private static void validate(String address) {

        Caze.match(
                new Caze.DefaultCaze(new ValidationRezzult.Suzzess(address + " is valid")),
                new Caze(new Predizzate(address, s -> (s == null)), new ValidationRezzult.Faizzure("address is null")),
                new Caze(new Predizzate(address, s -> (s == "")), new ValidationRezzult.Faizzure("address is empty")),
                new Caze(new Predizzate(address, s -> emailPattern.matcher((CharSequence) s).matches()), new ValidationRezzult.Faizzure(address + " is null"))
        );
    }
}
