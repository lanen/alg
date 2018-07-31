package sorting.control;

import sorting.Slot;

/**
 *
 */
public class BubbleSort extends AbstractSortInterface {

    @Override
    public void sort(Slot[] slots) {
        stop();
        for(int i = 0; i <slots.length; i++){
            for (int j = 0; j <slots.length - i - 1; j++){
                if(slots[j].getValue() > slots[j+1].getValue()){
                    swap(slots, j, j+1);
                    stop();
                }
            }
        }
    }
}
