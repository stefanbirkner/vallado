package org.junit.contrib.java.lang.throwable.lambda;

/**
 * An {@code Expectations} object encapsulates a user's assumption about an exception that should
 * be thrown. The {@code Expectations} calls a {@link Statement} and verifies that it throws an
 * exception that matches the user's assumptions.
 */
public interface Expectations {
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
    void shouldBeThrownBy(Statement statement);
}
