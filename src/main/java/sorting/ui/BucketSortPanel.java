package sorting.ui;

import sorting.Slot;
import sorting.config.SlotConst;

import java.awt.*;

/**
 * @author evan
 * create-date 2018/7/31
 */
public class BucketSortPanel extends SortPanel {

    public BucketSortPanel(Slot[] slots) {
        super(slots);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
//        for (int i = 0; i < bars.length; i++) {
//            g.setColor(bars[i].getColor());
//            g.fillRect(bars[i].x(SlotConst.UI_START_X), bars[i].y(SlotConst.UI_START_Y), bars[i].width(), bars[i].height());
//        }


    }
}
