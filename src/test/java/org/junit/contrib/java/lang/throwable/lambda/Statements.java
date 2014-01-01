package org.junit.contrib.java.lang.throwable.lambda;

/**
 * Some {@link Statement}s that are used by different tests.
 */
public enum Statements implements Statement {
    STATEMENT_THROWING_EXCEPTION {
        @Override
        public void evaluate() throws Throwable {
            throw new Exception();
        }
    },
    STATEMENT_THROWING_NO_EXCEPTION {
        @Override
        public void evaluate() throws Throwable {
        }
    }
}
