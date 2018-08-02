package sorting.ui;

import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author evan
 * create-date 2018/7/31
 */
public class PageControl {

    private Page current = null;

    private JFrame jFrame;

    private String currnetName;


    /**
     * 排序所在的线程
     */
    public final static ExecutorService SORTINT_THREAD = Executors.newSingleThreadExecutor();

    /**
     * 单步执行所在的线程
     */
    public final static ExecutorService SINGLE_STEP_THREAD = Executors.newSingleThreadExecutor();

    /**
     * 自动执行所在的线程
     */
    public final static ScheduledExecutorService AUTO_STEP_SORTING_THREAD = Executors.newSingleThreadScheduledExecutor();


    /**
     *
     * @param jFrame frame
     */
    public PageControl(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    public void switchPage(String name, boolean autoStep, int stepPeriod) {
        if (null == name) {
            return;
        }
        if (name.equals(currnetName)) {
            if (autoStep){
                current.autoActionStep(stepPeriod);
            }
            return;
        }

        if (null != current) {
            current.dispose();
        }

        current = new Page(this, "sorting.alg." + name);
        //决定是否自动
        if (autoStep){
            current.autoActionStep(stepPeriod);
        }
        this.currnetName = name;
    }

    public JFrame getjFrame() {
        return jFrame;
    }
}
