package sorting.alg;

import sorting.Slot;
import sorting.control.SortAction;
import sorting.control.SortInterface;

import java.util.concurrent.Semaphore;

/**
 * @author evan
 * create-date 2018/7/31
 */
public abstract class AbstractSortInterface implements SortInterface, SortAction {

    private Semaphore semaphore = new Semaphore(0);

    private boolean step = true;

    protected boolean sorted = false;

    public void swap(Slot[] slots, int left, int right) {
        Slot temp = slots[left];
        slots[left] = slots[right];
        slots[right] = temp;

        slots[left].updatePosition(left);
        slots[right].updatePosition(right);
    }


    /**
     * 设计是否 单步 排序
     */
    public void enableStepMode(){
        this.step = true;
    }

    public void disableStepMode(){
        this.step = false;
    }

    @Override
    public void pause() {
        if (!step) return;
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void resume() {
        if (!step) return;
        semaphore.release(1);
    }

    @Override
    public boolean sorted() {
        return sorted;
    }
}
