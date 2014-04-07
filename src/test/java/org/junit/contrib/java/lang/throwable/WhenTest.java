package org.junit.contrib.java.lang.throwable;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.contrib.java.lang.throwable.When.when;
import static org.junit.rules.ExpectedException.none;

public class WhenTest {
    @Rule
    public final ExpectedException thrown = none();

    @Test
    public void cannotBeCreatedWithoutStatement() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("The statement is missing.");
        when(null);
    }

    @Test
    public void createsWhenObject() {
        When when = when(Statements.STATEMENT_THROWING_NO_EXCEPTION);
        assertThat(when, is(notNullValue()));
    }
}
