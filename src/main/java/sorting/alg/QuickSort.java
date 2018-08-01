package sorting.alg;

import sorting.Slot;

/**
 * 快速排序
 * @author evan
 * create-date 2018/8/1
 */
public class QuickSort extends AbstractSortInterface{

    @Override
    public void sort(Slot[] slots) {
        pause();
        quickSort(slots,0,slots.length-1);
        //排序完成
        sorted = true;
    }

    /**
     *
     * @param slots
     * @param left
     * @param right
     */
    private void quickSort(Slot[] slots, int left, int right){
        int i = left;
        int j = right;

        if (left>=right){
            return;
        }

        Slot tmp = slots[left];
        while (i != j){
            while (i < j && slots[j].getValue() >= tmp.getValue()){
                j--;
            }
            if (j > i){
                slots[i] = slots[j];
                slots[i].updatePosition(i);
                pause();
            }
            while (i < j && slots[i].getValue() <= tmp.getValue()){
                i++;
            }
            if (i < j){
                slots[j] = slots[i];
                slots[j].updatePosition(j);
                pause();
            }

        }
        slots[i] = tmp;
        slots[i].updatePosition(i);
        pause();
        quickSort(slots, left, i -1);
        quickSort(slots, i+1, right);
    }
}
