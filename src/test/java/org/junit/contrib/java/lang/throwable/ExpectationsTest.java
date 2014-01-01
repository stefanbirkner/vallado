package org.junit.contrib.java.lang.throwable;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.not;
import static org.junit.rules.ExpectedException.none;

public class ExpectationsTest {
    private static final Exception DUMMY_EXCEPTION = new Exception();
    private static final Matcher<Throwable> IS_THE_DUMMY_EXCEPTION
            = Matchers.<Throwable>sameInstance(DUMMY_EXCEPTION);
    public static final Statement THROW_DUMMY_EXCEPTION = new Statement() {
        @Override
        public void evaluate() throws Throwable {
            throw DUMMY_EXCEPTION;
        }
    };

    @Rule
    public ExpectedException thrown = none();

    @Test
    public void isValidForCorrectType() {
        Expectations expectations = new Expectations(Exception.class);
        expectations.shouldBeThrownBy(Statements.STATEMENT_THROWING_EXCEPTION);
    }

    @Test
    public void isValidWithAdditionalExpectation() {
        Expectations expectations = new Expectations(Exception.class).and(IS_THE_DUMMY_EXCEPTION);
        expectations.shouldBeThrownBy(THROW_DUMMY_EXCEPTION);
    }

    @Test
    public void failsForMissingException() {
        Expectations expectations = new Expectations(Exception.class);
        thrown.expect(AssertionError.class);
        thrown.expectMessage("No exception has been thrown.");
        expectations.shouldBeThrownBy(Statements.STATEMENT_THROWING_NO_EXCEPTION);
    }

    @Test
    public void failsForWrongType() {
        Expectations expectations = new Expectations(RuntimeException.class);
        thrown.expect(AssertionError.class);
        thrown.expectMessage("The code threw a wrong exception.\nExpected: (an instance of java.lang.RuntimeException)\n" +
                "     but: an instance of java.lang.RuntimeException <java.lang.Exception> is a java.lang.Exception");
        expectations.shouldBeThrownBy(Statements.STATEMENT_THROWING_EXCEPTION);
    }

    @Test
    public void failsForAdditionalExpectation() {
        Expectations expectations = new Expectations(Exception.class).and(not(IS_THE_DUMMY_EXCEPTION));
        thrown.expect(AssertionError.class);
        expectations.shouldBeThrownBy(THROW_DUMMY_EXCEPTION);
    }

    @Test
    public void isNotAffectedByOriginalExpectations() {
        Expectations originalExpectations = new Expectations(Exception.class);
        Expectations derivedExpectations = originalExpectations.and(IS_THE_DUMMY_EXCEPTION);
        originalExpectations.and(not(IS_THE_DUMMY_EXCEPTION)); //try to modify original expectation
        derivedExpectations.shouldBeThrownBy(THROW_DUMMY_EXCEPTION);
    }
}
