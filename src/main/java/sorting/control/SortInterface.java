package sorting.control;

import sorting.Slot;
import sorting.ui.SortPanel;

/**
 *
 */
public interface SortInterface  extends SortAction{

    /**
     *
     * @param bars
     */
    void sort(Slot[] bars);

}
