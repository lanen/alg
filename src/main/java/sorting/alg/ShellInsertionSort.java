package sorting.alg;

import sorting.Slot;

/**
 * @author evan
 * create-date 2018/8/1
 */
public class ShellInsertionSort extends AbstractSortInterface{

    @Override
    public void sort(Slot[] slots) {
        pause();

        for(int group = slots.length/ 2 ; group > 0 ; group = group/2 ){
            for (int i = group; i < slots.length; i ++){
                for(int j = i - group; j >=0; j-=group){
                    if(slots[j].getValue()>slots[j+group].getValue()){
                        swap(slots, j , j+group);
                        pause();
                    }
                }
            }
        }

        sorted = true;
    }
}
