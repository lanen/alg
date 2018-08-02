package sorting.alg;

import sorting.Slot;

/**
 * @author evan
 * create-date 2018/8/2
 */
public class BucketSort extends AbstractSortInterface{

    private static class Node {
        Node next;
        Slot data;
    }

    @Override
    public void doSort(Slot[] slots) {

        Node[] buckets = new Node[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new Node();
        }

        for (int i = 0; i < slots.length; i++) {
            int index = hash(slots[i].getValue());
            Node head = buckets[index];
            Node node = new Node();
            node.data = slots[i];
            if(head.next == null){
                head.next = node;
            }else {
                while(head.next!=null && head.next.data.getValue()<=node.data.getValue()){
                    head = head.next;
                }
                node.next = head.next;
                head.next = node;
            }
        }
    }

    private int hash(int value){
        return value % 10;
    }
}
