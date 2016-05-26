/*
 * Project: funky-java-gym
 * Author: Marco Faustinelli - Muzietto (contacts@faustinelli.net)
 * Web: http://faustinelli.wordpress.com/, http://www.github.com/muzietto, http://faustinelli.net/
 * Version: 1.0
 * The GPL 3.0 License - Copyright (c) 2015-2016 - The funky-java-gym Project
 */

package net.faustinelli.funkyJavaGym.ch4._04_StackSafe;

import net.faustinelli.funkyJavaGym.annotations.IsTailCall;
import net.faustinelli.funkyJavaGym.annotations.NoTailCall;
import net.faustinelli.funkyJavaGym.annotations.NonRecursive;
import net.faustinelli.funkyJavaGym.annotations.Recursive;
import net.faustinelli.funkyJavaGym.ch4._01_TailCall.TailCall;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static net.faustinelli.funkyJavaGym.ch3._05_CollectionsUtils.CollectionsUtils.*;
import static net.faustinelli.funkyJavaGym.ch4._01_TailCall.TailCall.ret;
import static net.faustinelli.funkyJavaGym.ch4._01_TailCall.TailCall.sus;

/**
 * Created by Marco Faustinelli (Muzietto) on 19/04/2016.
 */
public class StackSafe {

    @Recursive
    @IsTailCall
    public static <A, B> B foldLeft(List<A> as, B acc, Function<A, Function<B, B>> fabb) {
        return as.isEmpty()
                ? acc
                : foldLeft(tail(as), fabb.apply(head(as)).apply(acc), fabb);
    }

    @NonRecursive
    @IsTailCall
    public static <A, B> B foldLeftTC(List<A> as, B acc, Function<A, Function<B, B>> fabb) {
        class FoldLeftTCInner {
            private TailCall<B> foldLeftTCInner(List<A> _as, B _acc) {
                return _as.isEmpty()
                        ? ret(_acc)
                        : sus(() -> foldLeftTCInner(tail(_as), fabb.apply(head(_as)).apply(_acc)));
            }
        }
        return new FoldLeftTCInner().foldLeftTCInner(as, acc).eval();
    }

    @Recursive
    @NoTailCall
    public static List<Integer> range(int min, int max) {
        class RangeHelper {
            List<Integer> rangeHelper(int _count, List<Integer> _acc) {
                return _count == max
                        ? _acc
                        : rangeHelper(_count + 1, append(new Integer(_count), _acc));
            }
        }
        return new RangeHelper().rangeHelper(min, Collections.EMPTY_LIST);
    }

    @NonRecursive
    @IsTailCall
    public static List<Integer> rangeTC(int min, int max) {
        class RangeTCHelper {
            TailCall<List<Integer>> rangeTCHelper(int _count, List<Integer> _acc) {
                return _count == max
                        ? ret(_acc)
                        : sus(() -> rangeTCHelper(_count + 1, append(new Integer(_count), _acc)));
            }
        }
        // why do I have to cast it?!?
        return (List<Integer>) new RangeTCHelper().rangeTCHelper(min, Collections.EMPTY_LIST).eval();
    }

    @Recursive
    @NoTailCall
    public static <A, B> B foldRight(List<A> as, B neutral, Function<A, Function<B, B>> fun) {
        class FoldRightInner {
            private B foldRightInner(List<A> _as) {
                return _as.isEmpty()
                        ? neutral
                        : fun.apply(head(_as)).apply(this.foldRightInner(tail(_as)));
            }
        }
        return new FoldRightInner().foldRightInner(as);
    }

    public static String addIS(Integer i, String s) {
        return "(" + i + " + " + s + ")";
    }

    @Recursive
    @IsTailCall
    public static <A, B> B foldRightXXX(List<A> as, B neutral, Function<A, Function<B, B>> fabb) {
        class FoldRightHelper {
            private B foldRightHelper(List<A> _as, B _res) {
                return _as.isEmpty()
                        ? _res
                        : this.foldRightHelper(tail(_as), fabb.apply(head(_as)).apply(_res));
            }
        }
        return new FoldRightHelper().foldRightHelper(reverse(as), neutral);
    }

    @NonRecursive
    @IsTailCall
    public static <A, B> B foldRightTC(List<A> as, B neutral, Function<A, Function<B, B>> fun) {
        class FoldRightTCHelper {
            private TailCall<B> foldRightTCHelper(List<A> _as, B _res) {
                return _as.isEmpty()
                        ? ret(_res)
                        : sus(() -> this.foldRightTCHelper(tail(_as), fun.apply(head(_as)).apply(_res)));
            }
        }
        return new FoldRightTCHelper().foldRightTCHelper(reverseTC(as), neutral).eval();
    }

    public static <A> List<A> reverseTC(List<A> as) {
        class ReverseTCHelper {
            private TailCall<List<A>> reverseTCHelper(List<A> _as, List<A> _acc) {
                return _as.isEmpty()
                        ? ret(_acc)
                        : sus(() -> this.reverseTCHelper(tail(_as), cons(head(_as), _acc)));
            }
        }
        return new ReverseTCHelper().reverseTCHelper(as, new ArrayList<A>()).eval();
    }

}
