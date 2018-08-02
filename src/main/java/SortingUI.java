import sorting.config.Texts;
import sorting.ui.PageManage;
import sorting.ui.SimpleControlPanel;
import sorting.ui.SimpleSortDisplayPanel;

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
        jFrame.setSize(600,600);

        SimpleSortDisplayPanel displayPanel = new SimpleSortDisplayPanel();
        PageManage pageManage = new PageManage(displayPanel);

        SimpleControlPanel simpleControlPanel = new SimpleControlPanel();
        simpleControlPanel.setPageManage(pageManage);

        jFrame.add(simpleControlPanel.getContent(), BorderLayout.WEST);
        jFrame.add(displayPanel.getContent(), BorderLayout.CENTER);

        //居中对齐
        jFrame.setLocationRelativeTo(null);
        jFrame.setTitle(Texts.TITLE);
        jFrame.setVisible(true);

    }
}
