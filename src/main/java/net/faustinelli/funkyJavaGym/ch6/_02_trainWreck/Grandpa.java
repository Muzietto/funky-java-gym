/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch6._02_trainWreck;

import net.faustinelli.funkyJavaGym.annotations.See;

import java.util.Optional;

/**
 * Created by Marco Faustinelli (Muzietto) on 09/05/2016.
 */
public class Grandpa {
    private final Father father;

    public Grandpa(Father father) {
        this.father = father;
    }

    @See("https://github.com/Muzietto/funky-java-gym/blob/master/src/test/java/net/faustinelli/funkyJavaGym/ch6/_02_trainWreck/DinastyTest.java")
    public static String grandSonName(Grandpa grandpa) {

        // return grandpa.getFather().getSon().getGrandSon().getName();

        Optional<String> name
                = Optional.ofNullable(grandpa.getFather()) // father may be null
                .map(Father::getSon) // son may be null
                .map(son -> son.getGrandSon().getName());  // cannot map a whole train wreck - still throwing if grandson is null

        return name.orElse("no descendant");
    }

    public Father getFather() {
        return father;
    }

    public static class Father {
        private final Son son;

        public Father(Son son) {
            this.son = son;
        }

        public Son getSon() {
            return son;
        }

        public static class Son {
            private final GrandSon grandSon;

            public Son(GrandSon grandSon) {
                this.grandSon = grandSon;
            }

            public GrandSon getGrandSon() {
                return grandSon;
            }

            public static class GrandSon {
                private final String name;

                public GrandSon(String name) {
                    this.name = name;
                }

                public String getName() {
                    return name;
                }
            }
        }
    }
}