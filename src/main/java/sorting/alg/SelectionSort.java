package sorting.alg;

import sorting.Slot;

/**
 * 选择排序
 * @author evan
 * create-date 2018/8/1
 */
public class SelectionSort extends AbstractSortInterface {

    @Override
    public void doSort(Slot[] slots) {

        int i;        // 有序区的末尾位置
        int j;        // 无序区的起始位置
        int min;      // 无序区中最小元素位置

        for(i = 0 ;i<slots.length;i++){
            min = i;

            for(j = i + 1 ;j < slots.length; j ++){
                if (slots[j].getValue() < slots[min].getValue()){
                    min = j;
                }
            }
            if(min != i) {
                swap(slots, i, min);
                pause();
            }
        }
    }
}
