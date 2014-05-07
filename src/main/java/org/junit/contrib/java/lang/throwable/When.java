package org.junit.contrib.java.lang.throwable;

/**
 * Vallado is a library that helps you to test that your code throws the right exceptions. It
 * works with JUnit and TestNG and is designed for Java 8.
 * <p>An example is worth a thousands words.
 * <pre>
 * public class YourClassTest {
 *   &#064;Test
 *   public void throwsException() {
 *     YourClass theObject = new YourClass();
 *     when(() -&gt; theObject.methodThrowsException())
 *       .thenA(RuntimeException.class).isThrown();
 *   }
 * }
 * </pre>
 * <h3>Write an assertion</h3>
 * <p>An assertion consists of three parts. First you enclose your code under test with When's
 * when method. Afterwards you define your expectations about the exception that should be thrown
 * by this code and finally you verify that your code really throws such an exception. When has
 * a fluent API that joins these three steps together into a single statement.
 * <h4>Specify the code under test</h4>
 * <p>You start by wrapping the code that should throw the exception with the when method. You can
 * use a lambda expression.
 * <pre>when(() -&gt; { /* code that throws an exception *&#47; })</pre>
 * <h4>Define the expectations about the exception</h4>
 * <p>You continue by defining the exception's type. The simplest approach is to use
 * {@link #aThrowable()}. If your exception is a real {@link Exception} you can use
 * {@link #anException()}, which is nicer to read. You can test for a specific type of exception,
 * too. Use {@link #thenA(Class)} or {@link #thenAn(Class)} like in the introduction's example.
 * Both methods are equivalent. They exist because of English grammar.
 * <p>Now you can add additional expectations. Every expectation must be a hamcrest matcher.
 * <pre>
 *   when(...)
 *     .thenA(RuntimeException.class)
 *       .that(hasProperty("message", equalTo("wrongState"))
 *       .and(hasProperty("cause", is(nullValue()))
 * </pre>
 * <h4>Verify your code</h4>
 * <p>After specifying the expectations about the exception you have an object that has an
 * {@code isThrown} method. Start verification by calling this method.
 * <pre>when(...).thenA(...).isThrown();</pre>
 */
public class When {
    private final Statement statement;

    /**
     * Encapsulates some code into a {@code When} object that is the starting point of Vallado's fluent API.
     *
     * @param statement the code that should throw an exception.
     * @return a {@code When} object that is the starting point of Vallado's fluent API.
     */
    public static When when(Statement statement) {
        return new When(statement);
    }

    private When(Statement statement) {
        if (statement == null)
            throw new NullPointerException("The statement is missing.");
        this.statement = statement;
    }

    /**
     * Expect a {@link java.lang.Throwable} with a specific type. (This methods is equal to
     * {@link #thenAn(Class)}. The second method only exists because of English grammar.)
     *
     * @param exceptionType the {@link java.lang.Throwable}'s type.
     */
    public ExpectExceptionType thenA(Class<? extends Throwable> exceptionType) {
        return new ExpectExceptionType(statement, exceptionType);
    }

    /**
     * Expect a {@link java.lang.Throwable} with a specific type. (This methods is equal to
     * {@link #thenA(Class)}. The second method only exists because of English grammar.)
     *
     * @param exceptionType the {@link java.lang.Throwable}'s type.
     */
    public ExpectExceptionType thenAn(Class<? extends Throwable> exceptionType) {
        return thenA(exceptionType);
    }

    /**
     * Expect an arbitrary {@link java.lang.Throwable}.
     */
    public ExpectExceptionType aThrowable() {
        return thenA(Throwable.class);
    }

    /**
     * Expect an arbitrary {@link java.lang.Exception}.
     */
    public ExpectExceptionType anException() {
        return thenAn(Exception.class);
    }
}
