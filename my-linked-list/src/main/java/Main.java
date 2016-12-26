
public class Main {
    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        for (int i =0; i<20; i++){
            list.add((int) (Math.random()*100));
        }

        for (int i =0; i<list.size(); i++){
            System.out.print(list.find(i).getValue()+" ");
        }
        System.out.println();
        Sorter.sort(list);
        MyLinkedList<Integer> sortedList =list;
        for (int i =0; i<sortedList.size(); i++){
            System.out.print(list.find(i).getValue()+" ");
        }

/**
        System.out.println(list.getHead().getValue());
        list.delete(0);
        System.out.println(list.getHead().getValue());
        System.out.println(list.find(3).getValue());
        list.delete(3);
        System.out.println(list.find(3).getValue());
*/
    }
}
