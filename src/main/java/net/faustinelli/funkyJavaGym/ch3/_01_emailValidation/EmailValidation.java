/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch3._01_emailValidation;

import net.faustinelli.funkyJavaGym.ch2._01_functions.F;

import java.util.function.Consumer;
import java.util.regex.Pattern;

/**
 * Created by Marco Faustinelli (Muzietto) on 08/04/2016.
 */
public class EmailValidation {
    static Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

    static F<String, Result> emailChecker = s ->
            s == null
                ? new Result.Failure("email can't be null")
                : s.length() == 0
                    ? new Result.Failure("email can't be empty")
                    : emailPattern.matcher(s).matches()
                        ? Result.success(s)
                        : Result.failure("email address is invalid");

    static Consumer<String> success = msg ->  System.out.println("HURRAH! " + msg);

    static Consumer<String> failure = msg -> System.out.println("wrong " + msg);

    public static void main(String[] args) {
        emailChecker.apply("this.is@my.email").bind(success, failure);
        emailChecker.apply(null).bind(success, failure);
        emailChecker.apply("").bind(success, failure);
        emailChecker.apply("john.doe@acme.com").bind(success, failure);
    }
}
