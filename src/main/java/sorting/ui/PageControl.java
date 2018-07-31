package sorting.ui;

import javax.swing.*;

/**
 * @author evan
 * create-date 2018/7/31
 */
public class PageControl {

    private Page current = null;

    private JFrame jFrame;

    private String currnetName;

    public PageControl(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    public void switchPage(String name, boolean autoStep, int stepPeriod) {
        if (null == name) {
            return;
        }
        if (name.equals(currnetName)) {
            return;
        }

        if (null != current) {
            current.dispose();
        }

        current = new Page(jFrame, "sorting.control." + name);
        //决定是否自动
        if (autoStep){
            current.autoActionStep(stepPeriod);
        }
        this.currnetName = name;
    }
}
