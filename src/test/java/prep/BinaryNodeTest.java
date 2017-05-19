package prep;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Steve Ash
 */
public class BinaryNodeTest {

    @Test
    public void shouldPrefixParse() throws Exception {
        BinaryNode<Integer> tree = BinaryTree.prefixParse(1, 2, 3, null, null, 4, null, 7, null, null, 5, null, 6, null, null);
        assertEquals("1 2 3 4 7 5 6", BinaryTree.toString(tree));
    }

    @Test
    public void shouldLevelParse() throws Exception {
        BinaryNode<Integer> tree = BinaryTree.levelParse(1, 2, 3, 4, 5, 6, 7);
        assertEquals("1 2 4 5 3 6 7", BinaryTree.toString(tree));
    }

    @Test
    public void shouldFindCommonAncestor() throws Exception {
        BinaryNode<Integer> tree = BinaryTree.levelParse(1, 2, 5, 3, 4, null, 6, null, null, null, 7, null, null, null, null);
        int result = BinaryTree.commonAncestor(tree, 7, 6);
        assertEquals(1, result);
        int result2 = BinaryTree.commonAncestor(tree, 5, 6);
        assertEquals(5, result2);
        int result3 = BinaryTree.commonAncestor(tree, 3, 7);
        assertEquals(2, result3);
    }

    @Test
    public void shouldIs2Tree() throws Exception {
        assertTrue(BinaryTree.is2Tree(BinaryTree.levelParse(1, 2, 3, 4, 5, 6, 7)));
        assertFalse(BinaryTree.is2Tree(BinaryTree.levelParse(1, 2, null)));
        assertFalse(BinaryTree.is2Tree(BinaryTree.levelParse(1, null, 2)));
        assertFalse(BinaryTree.is2Tree(BinaryTree.levelParse(1, null, 2, null, null, 3, null)));
    }

    @Test
    public void shouldEqual() throws Exception {
        BinaryNode<Integer> tree = BinaryTree.prefixParse(1, 2, 3, null, null, 4, null, 7, null, null, 5, null, 6, null, null);
        BinaryNode<Integer> tree2 = BinaryTree.prefixParse(1, 2, 3, null, null, 4, null, 7, null, null, 5, null, 8, null, null);
        BinaryNode<Integer> tree3 = BinaryTree.prefixParse(1, 2, 3, null, null, 4, null, 7, null, null, 5, null, 6, 9, null, null, null);
        assertTrue(BinaryTree.isEqual(tree, tree));
        assertFalse(BinaryTree.isEqual(tree, tree2));
        assertFalse(BinaryTree.isEqual(tree, tree3));
        assertFalse(BinaryTree.isEqual(tree2, tree3));
    }

    @Test
    public void shouldReverse() throws Exception {
        BinaryNode<Integer> tree = BinaryTree.prefixParse(1, 2, 3, null, null, 4, null, 7, null, null, 5, null, 6, null, null);
        BinaryNode<Integer> tree2 = BinaryTree.prefixParse(1, 5, 6, null, null, null, 2, 4, 7, null, null, null, 3, null, null);
        BinaryNode<Integer> tree3 = BinaryTree.reverse(tree);
        System.out.println(BinaryTree.toString(tree));
        System.out.println(BinaryTree.toString(tree2));
        System.out.println(BinaryTree.toString(tree3));
        assertTrue(BinaryTree.isEqual(tree2, tree3));
        assertTrue(BinaryTree.isEqual(tree3, tree2));
        assertFalse(BinaryTree.isEqual(tree, tree2));
        assertFalse(BinaryTree.isEqual(tree, tree3));

    }
}