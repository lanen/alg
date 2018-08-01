package sorting.alg;

import sorting.Slot;

/**
 * @author evan
 * create-date 2018/8/1
 */
public class MergeSort extends AbstractSortInterface{

    @Override
    public void sort(Slot[] slots) {
        pause();

        mergeSortUp2Down(slots, 0 , slots.length-1);

        sorted = true;
    }

    private void merge(Slot[] slots, int start, int mid, int end){
        Slot[] tmp = new Slot[end - start + 1];
        int i = start;
        int j = mid + 1;
        int k = 0;

        while(i<=mid && j<=end){
            if(slots[i].getValue()<=slots[j].getValue()){
                tmp[k++] = slots[i++];
            } else {
                tmp[k++] = slots[j++];
            }
        }

        while(i<=mid){
            tmp[k++] = slots[i++];
        }

        while(j <= end){
            tmp[k++] = slots[j++];
        }

        for(i = 0 ; i<k; i++){
            slots[start+i] = tmp[i];
            slots[start+i].updatePosition(start+i);
            pause();
        }
        tmp = null;
    }

    private void mergeSortUp2Down(Slot[] slots, int start, int end) {
        if(slots==null || start >= end){
            return;
        }
        int mid = (end + start)/2;
        mergeSortUp2Down(slots, start, mid);
        mergeSortUp2Down(slots, mid+1, end);
        merge(slots, start, mid, end);
    }
}
