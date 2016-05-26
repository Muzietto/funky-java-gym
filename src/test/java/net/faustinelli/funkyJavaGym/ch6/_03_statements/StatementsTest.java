package net.faustinelli.funkyJavaGym.ch6._03_statements;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

import org.junit.Test;

import java.util.Optional;

public class StatementsTest {

    private static String VALUE_1 = "value1";
    private static String VALUE_2 = "value2";

    @Test
    public void testD1() {

        Optional<String> opt = Optional.empty();

        Boolean result  = new Statements<String>(
            VALUE_1::equals,
            VALUE_2::equals
        ).disjunction(opt);

        assertFalse(result);
    }

    @Test
    public void testD2() {

        Optional<String> opt = Optional.of("value2");

        Boolean result = new Statements<String>(
            VALUE_1::equals,
            VALUE_2::equals
        ).disjunction(opt);

        assertTrue(result);
    }

    @Test
    public void testD3() {

        Optional<Integer> opt = Optional.of(12);

        Boolean result = new Statements<Integer>(
            new Integer(123)::equals,
            new Integer(12)::equals
        ).disjunction(opt);

        assertTrue(result);
    }

    @Test
    public void testC1() {

        Optional<String> opt = Optional.empty();

        Boolean result  = new Statements<String>(
            VALUE_1::equals,
            "value123"::contains
        ).conjunction(opt);

        assertFalse(result);
    }

    @Test
    public void testC2() {

        Optional<String> opt = Optional.of("value2");

        Boolean result = new Statements<String>(
            VALUE_2::equals,
            "value234"::contains
        ).conjunction(opt);

        assertTrue(result);
    }

    @Test
    public void testC3() {

        Optional<Integer> opt = Optional.of(12);

        Boolean result = new Statements<Integer>(
            x -> x < new Integer(123),
            new Integer(12)::equals
        ).conjunction(opt);

        assertTrue(result);
    }

}
