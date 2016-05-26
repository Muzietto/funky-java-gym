/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch2._01_functions;

/**
 * Created by Marco Faustinelli (Muzietto) on 06/04/2016.
 */
interface BinaryOperator<A, B, C> extends F<A, F<B, C>> {
}

interface BinaryIntegerOperator extends BinaryOperator<Integer, Integer, Integer> {
}

interface BinaryIntegerStringOperator extends BinaryOperator<Integer, String, String> {
}
