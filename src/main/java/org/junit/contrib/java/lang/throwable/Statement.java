package org.junit.contrib.java.lang.throwable;

/**
 * Code that should be executed by {@link Expectations}. This code could throw a {@link Throwable}.
 * Therefore we cannot use {@link Runnable}.
 */
public interface Statement {
    /**
     * Run the statement.
     *
     * @throws Throwable the statement may throw an arbitrary exception.
     */
    void evaluate() throws Throwable;
}
