
public class MyLinkedList<Integer> {
    private Node head;
    private int size;

    public MyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public Node<Integer> getHead() {
        return head;
    }

    public void setHead(Node<Integer> head) {
        this.head = head;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isEmpty() {
        if (head == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean add(int i){
        Node nod = new Node();
        nod.setValue(i);
        nod.setNext(head);
        head=nod;
        size++;
        return true;
    }

    public Node<Integer> find (int index) {
        if (index<0) {
            System.out.println("Error: index<0");
            return null;
        } else if (index ==0) {
            return head;
        } else {
            Node<Integer> temp = head;
            for (int i=1; i<=index; i++) {
                temp = temp.getNext();
            }
            return temp;
        }
    }

    public boolean delete (int index) {
        if (index<0) {
            System.out.println("Error: index<0");
            return false;
        } else if (index ==0) {
            head = head.getNext();
            return true;
        } else {
            Node<Integer> nod = find(index);
            nod.setValue(nod.getNext().getValue());
            nod.setNext(nod.getNext());
            return true;
        }
    }


}
