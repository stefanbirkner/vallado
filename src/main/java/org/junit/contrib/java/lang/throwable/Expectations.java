package org.junit.contrib.java.lang.throwable;

import org.hamcrest.Matcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * An {@code Expectations} object encapsulates a piece of code and a user's assumption about an
 * exception that should be thrown by this code. The user starts by creating an
 * {@code Expectations} object with a {@link org.junit.contrib.java.lang.throwable.Statement} and
 * the exception's type. Afterwards she adds further expectations with
 * {@link #and(org.hamcrest.Matcher)}. Each expectation is a
 * {@link org.hamcrest.Matcher Hamcrest Matcher}.
 * <p>If the expectations are completely defined you call {@link #isThrown()}. Now the
 * {@code Expectations} verifies that the {@link Statement} throws an exception that matches the
 * expectations.
 */
public class Expectations {
    private final List<Matcher<? super Throwable>> matchers;
    private final Statement statement;

    /**
     * Create an {@code Expectations} that expects an exception of the specified type to be thrown by the
     * {@link org.junit.contrib.java.lang.throwable.Statement}.
     *
     * @param statement the piece of code that should throw an exception.
     * @param type      the expected exception's type.
     */
    public Expectations(Statement statement, Class<? extends Throwable> type) {
        this.statement = statement;
        this.matchers = Collections.<Matcher<? super Throwable>>singletonList(instanceOf(type));
    }

    private Expectations(Expectations baseExpectations,
                         Matcher<? super Throwable> additionalExpectation) {
        this.statement = baseExpectations.statement;
        this.matchers = new ArrayList<Matcher<? super Throwable>>(baseExpectations.matchers);
        this.matchers.add(additionalExpectation);
    }

    /**
     * Create a new {@code Expectation}s object with an additional expectation.
     *
     * @param expectation the additional expectation.
     * @return a new {@code Expectation}s object with an additional expectation.
     */
    public Expectations and(Matcher<? super Throwable> expectation) {
        return new Expectations(this, expectation);
    }

    /**
     * Verifies that the statement throws a {@link java.lang.Throwable} that's equal to the user's
     * assumption. Therefore the statement is executed.
     *
     * @throws java.lang.AssertionError if the statement throws a {@link java.lang.Throwable} that's
     *                                  different from the user's assumption.
     */
    public void isThrown() {
        failIfStatementThrowsWrongOrNoException();
    }

    private void failIfStatementThrowsWrongOrNoException() {
        try {
            statement.evaluate();
        } catch (Throwable throwable) {
            assertThat("The code threw a wrong exception.", throwable, allOf(matchers));
            return;
        }
        /* the following line must be outside of the try block. Otherwise the catch block will catch
         * the exception.
         */
        throw new AssertionError("No exception has been thrown.");
    }
}
