package sorting.alg;

import sorting.Slot;

/**
 * @author evan
 * create-date 2018/8/2
 */
public class BucketSort extends AbstractSortInterface {

    public static class Node {
        public Node next;
        public Slot data;

    }

    public static Node[] buckets;

    @Override
    public void doSort(Slot[] slots) {

        buckets = new Node[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new Node();
        }
        pause();

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
            pause();
        }

        int pos = 0;
        for (int i = 0; i < buckets.length; i++) {
            for (Node p = buckets[i].next; p != null; p = p.next){
                swap(slots,pos,p.data.getPosition());
                slots[pos].updatePosition(pos);
                pos ++;
                pause();
            }
        }
    }

    private int hash(int value){
        return value / 10;
    }
}
