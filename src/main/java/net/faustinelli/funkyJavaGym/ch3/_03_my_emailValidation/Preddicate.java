/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch3._03_my_emailValidation;

import java.util.function.BooleanSupplier;
import java.util.function.Function;

/**
 * Created by Marco Faustinelli (Muzietto) on 13/04/2016.
 */
public interface Preddicate<T> extends Function<T, Boolean> {
}
