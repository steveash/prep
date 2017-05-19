package prep;

/**
 * @author Steve Ash
 */
public class SingleLinkedNode<T> {

    private T value;
    private SingleLinkedNode<T> next;

    public SingleLinkedNode(T value) {
        this.value = value;
    }

    public SingleLinkedNode(T value, SingleLinkedNode<T> next) {
        this.value = value;
        this.next = next;
    }

    public static <T> SingleLinkedNode<T> create(T... values) {
        if (values.length == 0) { return null; }
        SingleLinkedNode<T> root = new SingleLinkedNode<>(values[0]);
        SingleLinkedNode<T> prev = root;
        for (int i = 1; i < values.length; i++) {
            SingleLinkedNode<T> next = new SingleLinkedNode<T>(values[i]);
            prev.next = next;
            prev = next;
        }
        return root;
    }

    public static <T> SingleLinkedNode<T> reverse(SingleLinkedNode<T> node) {
        final SingleLinkedNode<T> originalRoot = node;
        SingleLinkedNode<T> prev = node;
        while (node != null) {
            SingleLinkedNode<T> tmp = node.next;
            node.next = prev;
            prev = node;
            node = tmp;
        }
        originalRoot.next = null;
        return prev;
    }

    public static <T> String toString(SingleLinkedNode<T> node) {
        StringBuilder sb = new StringBuilder();
        while (node != null) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(node.value);
            node = node.next;
        }
        return sb.toString();
    }
}
