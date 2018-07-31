package sorting.ui;

import sorting.Slot;

import javax.swing.*;
import java.awt.*;

/**
 * @author evan
 * create-date 2018/7/31
 */
public class SortPanel extends JPanel {

    /**
     * 图标开始位置
     */
    private static int START_X  = 10;

    private static int START_Y = 200;

    private Slot[] bars;

    public SortPanel(Slot[] bars) {
        this.bars = bars;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < bars.length; i++) {
            g.setColor(bars[i].getColor());
            g.fillRect(bars[i].x(START_X), bars[i].y(START_Y), bars[i].width(), bars[i].height());
        }
    }
}
