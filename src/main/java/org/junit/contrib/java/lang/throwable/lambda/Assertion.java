package org.junit.contrib.java.lang.throwable.lambda;

/**
 * Vallado is a Java 8 library that helps you to test that your code throws the right exceptions. It
 * works with JUnit and TestNG.
 *
 * <p>An example is worth a thousands words.
 *
 * <pre>
 * public class YourClass {
 *   public void methodThrowsException() {
 *       throw new IllegalStateException();
 *   }
 * }
 *
 * public class YourClassTest {
 *   &#064;Test
 *   public void throwsException() {
 *     YourClass theObject = new YourClass();
 *     an(IllegalStateException.class).shouldBeThrownBy(() -> theObject.methodThrowsException());
 *   }
 * }
 * </pre>
 *
 * <h3>Write an assertion</h3>
 * <p>An assertion consists of two parts. First you define your expectations about the exception
 * that should be thrown by the code under test. Afterwards you verify that your code really throws
 * such an exception.
 *
 * <h4>Define the expectations about the exception</h4>
 * <p>You start by defining the exception's type. The simplest approach is to use
 * {@link #aThrowable()}. If your exception is a real {@link Exception} you can use
 * {@link #anException()}, which is nicer to read. You can test for a specific type of exception,
 * too. Use {@link #a(Class)} or {@link #an(Class)} like in the introduction's example. Both methods
 * are equivalent. They exist because of English grammar.
 *
 * <h4>Verify your code</h4>
 * <p>After specifying the expectations about the exception you have an object that has a
 * {@code shouldBeThrownBy} method. Call this methods with the
 * {@link org.junit.contrib.java.lang.throwable.lambda.Statement} that should throw the
 * exception. You can use a lambda expression.
 *
 * <pre>
 *     an(IllegalStateException.class).shouldBeThrownBy(() -> theObject.methodThrowsException());
 * </pre>
 */
public class Assertion {
    /**
     * Expect a {@link java.lang.Throwable} with a specific type. (This methods is equal to
     * {@link #an(Class)}. The second method only exists becuase of English grammar.)
     * @param exceptionType the {@link java.lang.Throwable}'s type.
     */
    public static Expectations a(Class<? extends Throwable> exceptionType) {
        return new ExpectExceptionType(exceptionType);
    }

    /**
     * Expect a {@link java.lang.Throwable} with a specific type. (This methods is equal to
     * {@link #a(Class)}. The second method only exists becuase of English grammar.)
     * @param exceptionType the {@link java.lang.Throwable}'s type.
     */
    public static Expectations an(Class<? extends Throwable> exceptionType) {
        return a(exceptionType);
    }

    /**
     * Expect an arbitrary {@link java.lang.Throwable}.
     */
    public static Expectations aThrowable() {
        return a(Throwable.class);
    }

    /**
     * Expect an arbitrary {@link java.lang.Exception}.
     */
    public static Expectations anException() {
        return an(Exception.class);
    }
}
