package prep;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Steve Ash
 */
public class StringsTest {

    @Test
    public void shouldReverse() throws Exception {
        assertEquals("4321", Strings.reverse("1234"));
        assertEquals("4321", Strings.reverse2("1234"));
        assertEquals("54321", Strings.reverse("12345"));
        assertEquals("54321", Strings.reverse2("12345"));
        assertEquals("1", Strings.reverse("1"));
        assertEquals("1", Strings.reverse2("1"));
        assertEquals("", Strings.reverse(""));
        assertEquals("", Strings.reverse2(""));
    }
}