package org.junit.contrib.java.lang.throwable.lambda;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
    public void failsForMissingException() {
        ExpectExceptionType expectExceptionType = new ExpectExceptionType(Exception.class);
        thrown.handleAssertionErrors();
        thrown.expect(AssertionError.class);
        thrown.expectMessage("No exception has been thrown.");
        expectExceptionType.shouldBeThrownBy(Statements.STATEMENT_THROWING_NO_EXCEPTION);
    }

    @Test
    public void failsForWrongType() {
        ExpectExceptionType expectExceptionType = new ExpectExceptionType(RuntimeException.class);
        thrown.handleAssertionErrors();
        thrown.expect(AssertionError.class);
        thrown.expectMessage("Wrong type of exception has been thrown. Expected: <java.lang.RuntimeException> but was: <java.lang.Exception>");
        expectExceptionType.shouldBeThrownBy(Statements.STATEMENT_THROWING_EXCEPTION);
    }
}
