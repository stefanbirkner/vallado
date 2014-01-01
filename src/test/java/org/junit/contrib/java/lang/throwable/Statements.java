package org.junit.contrib.java.lang.throwable;

/**
 * Some {@link org.junit.contrib.java.lang.throwable.Statement}s that are used by different tests.
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
