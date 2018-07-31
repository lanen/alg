import sorting.control.BubbleSort;
import sorting.ui.Page;

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

        Page page = new Page(jFrame, BubbleSort.class.getName());

        jFrame.setVisible(true);

    }
}
