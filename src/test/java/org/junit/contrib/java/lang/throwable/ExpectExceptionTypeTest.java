package org.junit.contrib.java.lang.throwable;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.rules.ExpectedException.none;

public class ExpectExceptionTypeTest {
    @Rule
    public ExpectedException thrown = none();

    @Test
    public void isValidForCorrectType() {
        ExpectExceptionType expectExceptionType = new ExpectExceptionType(Exception.class);
        expectExceptionType.shouldBeThrownBy(Statements.STATEMENT_THROWING_EXCEPTION);
    }

    @Test
    public void isValidWithAdditionalExpectation() {
        Expectations expectations = new ExpectExceptionType(Exception.class)
                .that(is(instanceOf(Exception.class)));
        expectations.shouldBeThrownBy(Statements.STATEMENT_THROWING_EXCEPTION);
    }

    @Test
    public void failsForMissingException() {
        ExpectExceptionType expectExceptionType = new ExpectExceptionType(Exception.class);
        thrown.expect(AssertionError.class);
        expectExceptionType.shouldBeThrownBy(Statements.STATEMENT_THROWING_NO_EXCEPTION);
    }

    @Test
    public void failsForWrongType() {
        ExpectExceptionType expectExceptionType = new ExpectExceptionType(RuntimeException.class);
        thrown.expect(AssertionError.class);
        expectExceptionType.shouldBeThrownBy(Statements.STATEMENT_THROWING_EXCEPTION);
    }

    @Test
    public void failsForAdditionalExpectation() {
        Expectations expectations = new ExpectExceptionType(Exception.class)
                .that(is(instanceOf(NullPointerException.class)));
        thrown.expect(AssertionError.class);
        expectations.shouldBeThrownBy(Statements.STATEMENT_THROWING_EXCEPTION);
    }
}
