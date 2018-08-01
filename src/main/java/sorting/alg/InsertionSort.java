package sorting.alg;

import sorting.Slot;

/**
 * @author evan
 * create-date 2018/8/1
 */
public class InsertionSort extends AbstractSortInterface{

    @Override
    public void sort(Slot[] slots) {
        pause();

        for(int i = 1; i < slots.length; i ++){
            if(slots[i - 1].getValue() > slots[i].getValue()){
                Slot tmp = slots[i];
                int j = i ;

                while(j > 0 && slots[j - 1].getValue() > tmp.getValue()){
                    slots[j] = slots[j - 1];
                    slots[j].updatePosition(j);
                    j --;
                    pause();
                }
                slots[j] = tmp;
                slots[j].updatePosition(j);
                pause();
            }
        }

        sorted = true;
    }
}
