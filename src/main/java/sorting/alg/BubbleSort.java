package sorting.alg;

import sorting.Slot;
/**
 * 冒泡排序
 * @author evan
 * create-date 2018/7/31
 */
public class BubbleSort extends AbstractSortInterface {

    @Override
    public void doSort(Slot[] slots) {
        for(int i = 0; i <slots.length; i++){
            for (int j = 0; j <slots.length - i - 1; j++){
                if( slots[j].getValue() > slots[j+1].getValue() ){
                    swap(slots, j, j+1);
                    pause();
                }
            }
        }
    }
}
