package sorting.ui;

import sorting.Slot;
import sorting.config.SlotConst;

import javax.swing.*;
import java.awt.*;

/**
 * @author evan
 * create-date 2018/7/31
 */
public class SortPanel extends JPanel {

    protected Slot[] slots;

    public SortPanel(Slot[] slots) {
        this.slots = slots;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < slots.length; i++) {
            g.setColor(slots[i].getColor());
            g.fillRect(slots[i].x(SlotConst.UI_START_X), slots[i].y(SlotConst.UI_START_Y), slots[i].width(), slots[i].height());
        }
    }

    public Slot[] getSlots() {
        return slots;
    }

    public void setSlots(Slot[] slots) {
        this.slots = slots;
    }
}
