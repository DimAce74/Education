
public class Sorter {
    public static void sort(MyLinkedList list) {
        MyLinkedList[] array = new MyLinkedList[32];

        for (int i =0; i<array.length; i++) {
            array[i] = new MyLinkedList();
        }

        int currBasket = 0;


        while (list.getHead()!=null) {
            for (int i =0; i<array[0].size(); i++){
                System.out.print(array[0].find(i).getValue()+" ");
                System.out.println();
            }


            Node head = list.getHead();
            //System.out.println(head.getValue());
            Node next = list.getHead().getNext();
            //System.out.println(next.getValue());

            array[currBasket].add(head.getValue());
            head = next;
            currBasket++;
            if (head==null) break;
            array[currBasket].add(head.getValue());
            head = next.getNext();
            list.setHead(head);
            currBasket++;
            while (array[currBasket-1]!=null&&
                    currBasket-2>=0&&
                    array[currBasket-1].size() == array[currBasket-2].size()) {
                array[currBasket-2] = merge(array[currBasket-2], array[currBasket-1]);
                currBasket--;
                array[currBasket] = new MyLinkedList();
            }
        }

        while (array[currBasket-1]!=null&&currBasket-2>=0) {

        }

    }

    private static MyLinkedList merge(MyLinkedList list1, MyLinkedList list2) {
        Node one = list1.getHead();
        Node two = list2.getHead();
        MyLinkedList result = new MyLinkedList();
        while (one!=null||two!=null) {
            if (one==null){
                result.add(two.getValue());
                two = two.getNext();
            } else if (two==null) {
                result.add(one.getValue());
                one = one.getNext();
            } else if (one.getValue()<=two.getValue()) {
                result.add(two.getValue());
                two = two.getNext();
            } else if (one.getValue()>two.getValue()) {
                result.add(one.getValue());
                one = one.getNext();
            }
        }
        return result;

    }

}
