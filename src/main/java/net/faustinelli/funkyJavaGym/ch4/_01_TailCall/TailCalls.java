/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch4._01_TailCall;

import static net.faustinelli.funkyJavaGym.ch4._01_TailCall.TailCall.*;

/**
 * Created by Marco Faustinelli (Muzietto) on 16/04/2016.
 */
public class TailCalls {

    public static Integer add(int x, int y) {
        return (y == 0)
                ? x
                : add(++x, --y);
    }

    public static TailCall<Integer> addTC(final int x, final int y) {
        return (y == 0)
                ? new Return<Integer>(x)
                : new Suspend<Integer>(() -> addTC(x + 1, y - 1));
    }

    public static Integer addTCBest(final int x, final int y) {
        return addTCBestHelper(x, y).eval();
    }

    private static TailCall<Integer> addTCBestHelper(final int x, final int y) {
        return (y == 0)
                ? ret(x)
                : sus(() -> addTCBestHelper(x + 1, y - 1));
    }

    public static Integer addTCBestWithInnerClass(final int x, final int y) {
        class AddTCBestHelper {
            private TailCall<Integer> addTCBestHelper(final int x, final int y) {
                return (y == 0)
                        ? ret(x)
                        : sus(() -> addTCBestHelper(x + 1, y - 1));
            }

        }
        return new AddTCBestHelper().addTCBestHelper(x, y).eval();
    }
}
