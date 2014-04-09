package org.junit.contrib.java.lang.throwable;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.contrib.java.lang.throwable.Statements.STATEMENT_THROWING_EXCEPTION;
import static org.junit.contrib.java.lang.throwable.Statements.STATEMENT_THROWING_NO_EXCEPTION;
import static org.junit.rules.ExpectedException.none;

public class ExpectExceptionTypeTest {
    @Rule
    public ExpectedException thrown = none();

    @Test
    public void isValidForCorrectType() {
        ExpectExceptionType expectExceptionType = new ExpectExceptionType(
                STATEMENT_THROWING_EXCEPTION, Exception.class);
        expectExceptionType.isThrown();
    }

    @Test
    public void isValidWithAdditionalExpectation() {
        Expectations expectations = new ExpectExceptionType(STATEMENT_THROWING_EXCEPTION, Exception.class)
                .that(is(instanceOf(Exception.class)));
        expectations.isThrown();
    }

    @Test
    public void failsForMissingException() {
        ExpectExceptionType expectExceptionType = new ExpectExceptionType(
                STATEMENT_THROWING_NO_EXCEPTION, Exception.class);
        thrown.expect(AssertionError.class);
        expectExceptionType.isThrown();
    }

    @Test
    public void failsForWrongType() {
        ExpectExceptionType expectExceptionType = new ExpectExceptionType(
                STATEMENT_THROWING_EXCEPTION, RuntimeException.class);
        thrown.expect(AssertionError.class);
        expectExceptionType.isThrown();
    }

    @Test
    public void failsForAdditionalExpectation() {
        Expectations expectations = new ExpectExceptionType(STATEMENT_THROWING_EXCEPTION, Exception.class)
                .that(is(instanceOf(NullPointerException.class)));
        thrown.expect(AssertionError.class);
        expectations.isThrown();
    }
}
