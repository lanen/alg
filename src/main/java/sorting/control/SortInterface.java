package sorting.control;

import sorting.Slot;

/**
 *
 */
public interface SortInterface  extends SortAction {

    /**
     *
     * @param slots
     */
    void sort(Slot[] slots);

    boolean sorted();

}
