
public class Node<Integer> {
    private int value;
    private Node next;

    public Node() {
    }

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node<Integer> getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
