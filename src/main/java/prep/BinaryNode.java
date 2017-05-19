package prep;

/**
 * @author Steve Ash
 */
public class BinaryNode<K> {

    public static <K> BinaryNode<K> create(K value) {
        return new BinaryNode<K>(value);
    }

    private K value;
    private BinaryNode<K> left;
    private BinaryNode<K> right;

    public BinaryNode(BinaryNode<K> copyFrom) {
        this(copyFrom.value, copyFrom.left, copyFrom.right);
    }

    public BinaryNode(K value) {
        this.value = value;
    }

    public BinaryNode(K value, BinaryNode<K> left, BinaryNode<K> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public K getValue() {
        return value;
    }

    public BinaryNode<K> getLeft() {
        return left;
    }

    public void setLeft(BinaryNode<K> left) {
        this.left = left;
    }

    public BinaryNode<K> getRight() {
        return right;
    }

    public void setRight(BinaryNode<K> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", left? " + (left != null) +
                ", right? " + (right != null) +
                '}';
    }
}
