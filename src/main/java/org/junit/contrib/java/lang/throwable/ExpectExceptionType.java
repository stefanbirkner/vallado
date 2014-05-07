package org.junit.contrib.java.lang.throwable;

import org.hamcrest.Matcher;

/**
 * An {@link Expectations} that expects the exception
 * to have a special type. You can specify additional expectations with
 * {@link #that(org.hamcrest.Matcher)}.
 */
public class ExpectExceptionType {
    private final Expectations expectations;

    /**
     * Create an {@link Expectations} that expects a statement to throw an
     * exception of the specified type.
     *
     * @param statement the statement that should throw the exception.
     * @param type the expected exception's type.
     */
    public ExpectExceptionType(Statement statement, Class<? extends Throwable> type) {
        this.expectations = new Expectations(statement, type);
    }

    /**
     * Create an {@link Expectations} that expects the same type and an additional expectation.
     * @param expectation the additional expectation.
     * @return an {@link Expectations} that expect the same type and an additional expectation.
     */
    public Expectations that(Matcher<? super Throwable> expectation) {
        return expectations.and(expectation);
    }

    /**
     * Verifies that the statement throws a {@link Throwable} that has a type that is equal to the
     * type specified by the constructor.
     *
     * @throws AssertionError if the statement throws a {@link Throwable} that has a
     *                        wrong type or if the statement throws no {@link Throwable} at all.
     */
    public void isThrown() {
        expectations.isThrown();
    }
}
