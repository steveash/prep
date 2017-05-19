package prep;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * Static utility class for binary tree algorithms
 * @author Steve Ash
 */
public class BinaryTree {

    public static <K> BinaryNode<K> reverse(BinaryNode<K> root) {
        if (root == null) {
            return null;
        }

        BinaryNode<K> newLeft = reverse(root.getRight());
        BinaryNode<K> newRight = reverse(root.getLeft());
        return new BinaryNode<>(root.getValue(), newLeft, newRight);
    }

    public static <K> int maxDepth(BinaryNode<K> root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.getLeft()), maxDepth(root.getRight())) + 1;
    }

    public static <K> boolean is2Tree(BinaryNode<K> root) {
        if (root == null) {
            return false;
        }
        if (root.getLeft() == null && root.getRight() == null) {
            return true;
        }
        return is2Tree(root.getLeft()) && is2Tree(root.getRight());
    }

    public static <K> BinaryNode<K> prefixParse(K... values) {
        LinkedList<K> queue = new LinkedList<K>(Arrays.asList(values));
        return prefixParseFromQueue(queue);
    }

    public static <K> BinaryNode<K> levelParse(K... valuesInLevelOrder) {
        if (valuesInLevelOrder.length == 0) {
            return null;
        }
        // want to ensure that everything was specified so needs to have 2^k-1 values specified
        if ((valuesInLevelOrder.length + 1 & valuesInLevelOrder.length) != 0) {
            throw new IllegalArgumentException("Wrong number of tree elements specified, must specify complete tree");
        }

        Queue<K> values = new LinkedList<>(Arrays.asList(valuesInLevelOrder));
        Queue<BinaryNode<K>> queue = new LinkedList<>();

        BinaryNode<K> root = new BinaryNode<K>(values.remove());
        queue.add(root);
        while (!queue.isEmpty() && !values.isEmpty()) {
            BinaryNode<K> head = queue.remove();
            queue.add(parseListOrderEntry(values, head, true));
            queue.add(parseListOrderEntry(values, head, false));
        }
        return root;
    }

    private static <K> BinaryNode<K> parseListOrderEntry(Queue<K> values, BinaryNode<K> head, boolean isLeft) {
        K value = values.remove();
        BinaryNode<K> nextNode = null;
        if (value != null) {
            nextNode = new BinaryNode<>(value);
        }
        if (head != null) {
            if (isLeft) {
                head.setLeft(nextNode);
            } else {
                head.setRight(nextNode);
            }
        } else if (nextNode != null) {
            throw new IllegalArgumentException("malformed tree, missing parent on level: " + nextNode);
        }
        return nextNode;
    }

    public static <K> K commonAncestor(BinaryNode<K> root, K nodeA, K nodeB) {
        LinkedList<K> crumbsA = new LinkedList<>();
        boolean foundA = pathTo(root, nodeA, crumbsA);
        LinkedList<K> crumbsB = new LinkedList<>();
        boolean foundB = pathTo(root, nodeB, crumbsB);
        if (!foundA || !foundB) {
            throw new IllegalArgumentException("Cannot find ancestor of missing nodes");
        }
        K last = null;
        while (!crumbsA.isEmpty() && !crumbsB.isEmpty()) {
            K nextA = crumbsA.removeFirst();
            K nextB = crumbsB.removeFirst();
            if (!nextA.equals(nextB)) {
                return last;
            }
            last = nextA;
        }
        return last;
    }

    private static <K> boolean pathTo(BinaryNode<K> node, K toFind, LinkedList<K> crumbs) {
        if (node == null) {
            return false;
        }
        crumbs.addLast(node.getValue());
        if (node.getValue().equals(toFind)) {
            return true;
        }
        if (pathTo(node.getLeft(), toFind, crumbs)) {
            return true;
        }
        if (pathTo(node.getRight(), toFind, crumbs)) {
            return true;
        }
        crumbs.removeLast();
        return false;
    }

    public static <K> void preOrder(BinaryNode<K> node, Consumer<K> visitor) {
        if (node == null) {
            return;
        }
        visitor.accept(node.getValue());
        preOrder(node.getLeft(), visitor);
        preOrder(node.getRight(), visitor);
    }

    public static <K> boolean isEqual(BinaryNode<K> a, BinaryNode<K> b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        if (!a.getValue().equals(b.getValue())) {
            return false;
        }
        return isEqual(a.getLeft(), b.getLeft()) && isEqual(a.getRight(), b.getRight());
    }

    public static <K> String toString(BinaryNode<K> node) {
        StringBuilder sb = new StringBuilder();
        preOrder(node, n -> {
            sb.append(" ").append(n);
        });
        return sb.toString().trim();
    }

    private static <K> BinaryNode<K> prefixParseFromQueue(LinkedList<K> queue) {
        K value = queue.removeFirst();
        if (value == null) {
            return null;
        }
        BinaryNode<K> left = prefixParseFromQueue(queue);
        BinaryNode<K> right = prefixParseFromQueue(queue);
        return new BinaryNode<K>(value, left, right);
    }
}
