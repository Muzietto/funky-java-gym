/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch6._03_statements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Created by Marco Faustinelli (Muzietto) on 19/05/2016.
 */
public class Statements<T> {

    private List<Predicate<T>> matchers = new ArrayList<Predicate<T>>();

    public Statements(Predicate<T>... predicates) {
        this.matchers = Arrays.asList(predicates);
    }

    public Boolean disjunction(Optional<T> needsTest) {
        return needsTest.map(m ->
                matchers
                        .stream()
                        .map(p -> p.test(m))
                        .reduce((a, b) -> a || b).get()
        ).orElse(Boolean.FALSE);
    }

    public Boolean conjunction(Optional<T> needsTest) {
        return needsTest.map(m ->
                matchers
                        .stream()
                        .map(p -> p.test(m))
                        .reduce((a, b) -> a && b).get()
        ).orElse(Boolean.FALSE);
    }
}