import sorting.ui.LeftPanel;
import sorting.ui.PageControl;

import javax.swing.*;
import java.awt.*;

/**
 * 排序的UI呈现
 * @author evan
 * create-date 2018/7/31
 */
public class SortingUI {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLayout(new BorderLayout());
        jFrame.setSize(600,400);

        PageControl pageControl = new PageControl(jFrame);
        jFrame.add(new LeftPanel(pageControl), BorderLayout.WEST);

        jFrame.setVisible(true);

    }
}
