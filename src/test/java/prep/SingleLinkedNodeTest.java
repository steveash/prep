package prep;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Steve Ash
 */
public class SingleLinkedNodeTest {

    @Test
    public void shouldCreateAndPrint() throws Exception {
        SingleLinkedNode<Integer> list1 = SingleLinkedNode.create(1, 2, 3, 4, 5, 6);
        assertEquals("1 2 3 4 5 6", SingleLinkedNode.toString(list1));
    }

    @Test
    public void shouldReverse() throws Exception {
        SingleLinkedNode<Integer> list1 = SingleLinkedNode.create(1, 2, 3, 4, 5, 6);
        assertEquals("6 5 4 3 2 1", SingleLinkedNode.toString(SingleLinkedNode.reverse(list1)));
    }
}