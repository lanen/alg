import sorting.config.Texts;
import sorting.ui.PageManage;
import sorting.ui.SimpleControlPanel;
import sorting.ui.SimpleMain;
import sorting.ui.SimpleSortDisplayPanel;

import javax.swing.*;

/**
 * 排序的UI呈现
 * @author evan
 * create-date 2018/7/31
 */
public class SortingUI extends UI{

    /**
     *
     * @return
     */
    protected SimpleMain createSimpleMain(){
        SimpleMain simpleMain = new SimpleMain();

        SimpleSortDisplayPanel displayPanel = new SimpleSortDisplayPanel();
        PageManage pageManage = new PageManage(displayPanel);

        SimpleControlPanel simpleControlPanel = new SimpleControlPanel();
        simpleControlPanel.setPageManage(pageManage);

        simpleMain.getContent().setLeftComponent(simpleControlPanel.getContent());
        simpleMain.getContent().setRightComponent(displayPanel.getContent());

        return simpleMain;
    }

    public static void main(String[] args) {

        SortingUI sortingUI = new SortingUI();
        JFrame jFrame = sortingUI.createJFrame(Texts.TITLE);

        jFrame.add(sortingUI.createSimpleMain().getRoot());
        jFrame.setVisible(true);
    }
}
