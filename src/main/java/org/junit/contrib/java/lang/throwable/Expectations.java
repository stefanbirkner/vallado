package org.junit.contrib.java.lang.throwable;

import org.hamcrest.Matcher;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * An {@code Expectations} object encapsulates a user's assumption about an exception that should
 * be thrown. The user starts by creating an {@code Expectations} object with the exception's type
 * and adds further expectations with {@link #and(org.hamcrest.Matcher)}. Each expectation is a
 * {@link org.hamcrest.Matcher Hamcrest Matcher}.
 *
 * <p>If the expectations are completely defined you can verify that a {@link Statement} throws an
 * exception that matches this expectations.
 */
public class Expectations {
    private final List<Matcher<? super Throwable>> matchers;

    /**
     * Create an {@code Expectations} that expects an exception of the specified type.
     *
     * @param type the expected exception's type.
     */
    public Expectations(Class<? extends Throwable> type) {
        this.matchers = new ArrayList<Matcher<? super Throwable>>();
        this.matchers.add(instanceOf(type));
    }

    private Expectations(Expectations baseExpectations,
                         Matcher<? super Throwable> additionalExpectation) {
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
     * assumption. Therefore the statement is executed. It is expected to use a lambda expression
     * <pre>
     *     expectedException.shouldBeThrownBy(() -> objectUnderTest.doesSomething());
     * </pre>
     *
     * @param statement an arbitrary piece of code.
     * @throws java.lang.AssertionError if the statement throws a {@link java.lang.Throwable} that's
     *                                  different from the user's assumption.
     */
    public void shouldBeThrownBy(Statement statement) {
        failIfStatementThrowsWrongOrNoException(statement);
    }

    private void failIfStatementThrowsWrongOrNoException(Statement statement) {
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
