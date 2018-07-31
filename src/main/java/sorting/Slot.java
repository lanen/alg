package sorting;

import java.awt.*;

/**
 * @author evan
 * create-date 2018/7/31
 */
public interface Slot extends SlotPosition{

    /**
     *
     * @return
     */
    int getValue();

    /**
     *
     * @return
     */
    int getPosition();

    /**
     *
     * @return
     */
    Color getColor();

    /**
     *
     * @param position
     */
    void updatePosition(int position);

}
