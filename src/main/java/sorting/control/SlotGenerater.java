package sorting.control;

import sorting.SimpleSlot;
import sorting.Slot;

import java.util.Random;

/**
 * @author evan
 * create-date 2018/7/31
 */
public class SlotGenerater {

    /**
     * 随机生成
     * @param len
     * @return
     */
    public Slot[] generate(int len) {
        Random random = new Random();
        if (len<1){
            len = 20;
        }

        Slot[] bars = new Slot[len];
        for (int i = 0; i < bars.length; i++) {
            bars[i] = new SimpleSlot(i,random.nextInt(100));
        }
        return bars;
    }
}
