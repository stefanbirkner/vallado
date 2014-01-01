package org.junit.contrib.java.lang.throwable.lambda;

/**
 * An {@link org.junit.contrib.java.lang.throwable.lambda.Expectations} that expects the exception to have a special type.
 */
public class ExpectExceptionType implements Expectations {
    private final Class<? extends Throwable> type;

    /**
     * Create an {@link org.junit.contrib.java.lang.throwable.lambda.Expectations} that expects an exception of the specified type.
     *
     * @param type the expected exception's type.
     */
    public ExpectExceptionType(Class<? extends Throwable> type) {
        this.type = type;
    }

    /**
     * Verifies that the statement throws a {@link Throwable} that has a type that is equal to the
     * type specified by the constructor.
     *
     * @param statement an arbitrary piece of code.
     * @throws AssertionError if the statement throws a {@link Throwable} that has a
     *                        wrong type or if the statement throws no {@link Throwable} at all.
     */
    @Override
    public void shouldBeThrownBy(Statement statement) {
        failIfStatementThrowsWrongOrNoException(statement);
    }

    private void failIfStatementThrowsWrongOrNoException(Statement statement) {
        try {
            statement.evaluate();
        } catch (Throwable throwable) {
            if (!type.isInstance(throwable))
                throw new AssertionError("Wrong type of exception has been thrown. Expected: <"
                        + type.getName() + "> but was: <" + throwable.getClass().getName() + ">");
            return;
        }
        /* the following line must be outside of the try block. Otherwise the catch block will catch
         * the exception.
         */
        throw new AssertionError("No exception has been thrown.");
    }
}
