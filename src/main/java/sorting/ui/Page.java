package sorting.ui;

import sorting.Slot;
import sorting.config.SlotConst;
import sorting.control.SlotGenerater;
import sorting.control.SortInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.*;

/**
 *
 * @author evan
 * create-date 2018/7/31
 */
public class Page {

    private JFrame jFrame;
    private JButton button;
    private SortPanel sortPanel;

    /**
     * 排序的界面
     */
    private String uiName;

    /**
     * 排序类
     */
    private String sortClassName;

    private SortInterface sortInterface;

    /**
     *
     */
    private SlotGenerater generater = new SlotGenerater();

    /**
     * 动画任务
     */
    private ScheduledFuture<?> scheduledFuture;

    /**
     *
     * @param pageControl pageControl
     * @param className class name
     */
    public Page(PageControl pageControl, String className) {
        this(pageControl, className,"default");
    }

    /**
     *
     * @param pageControl
     * @param className
     * @param uiName
     */
    public Page(PageControl pageControl, String className, String uiName) {
        this.jFrame = pageControl.getjFrame();
        this.sortClassName = className;
        this.uiName = uiName;
        this.init();
    }


    /**
     * 初始化
     */
    private void init(){

        this.sortInterface = createSortInterface(this.sortClassName);
        if (null == sortInterface){
            return;
        }
        this.sortPanel = createSlotPanel(this.uiName);

        this.button = new JButton();
        this.button.setText("Step");
        this.button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                PageControl.SINGLE_STEP_THREAD.submit(new Runnable() {
                    @Override
                    public void run() {
                        actionStep();
                    }
                });
            }
        });

        this.jFrame.add(this.sortPanel, BorderLayout.CENTER);
        this.jFrame.add(this.button, BorderLayout.SOUTH);

        this.jFrame.revalidate();

    }

    private void actionStep(){
        if (null != sortInterface){
            sortInterface.resume();
        }
        if (null != sortPanel){
            sortPanel.repaint();
        }
    }


    /**
     *
     * @param period
     */
    public void autoActionStep(int period){

        stopScheduledFuture();

        if(null != PageControl.SORTINT_THREAD){
            PageControl.SORTINT_THREAD.execute(new Runnable() {
                @Override
                public void run() {
                    sortPanel.setBars(generater.generate(SlotConst.SLOT_NUM));
                    sortInterface.sort(sortPanel.getBars());
                }
            });
        }

        if (null == PageControl.AUTO_STEP_SORTING_THREAD){
            return;
        }

        scheduledFuture = PageControl.AUTO_STEP_SORTING_THREAD.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                actionStep();

                if (sortInterface.sorted()){
                    stopScheduledFuture();
                }
            }
        }, 200, period, TimeUnit.MILLISECONDS);
    }

    /**
     * 停止动画
     */
    private void stopScheduledFuture(){
        if (null != scheduledFuture){
            sortInterface.resumeAndExit();
            scheduledFuture.cancel(true);
            scheduledFuture = null;
        }
    }
    /**
     *
     */
    public void dispose(){
        stopScheduledFuture();

        if (null != this.sortPanel){
            this.sortPanel.invalidate();
            this.sortPanel.setVisible(false);
            this.sortPanel.removeAll();
            this.jFrame.getContentPane().remove(this.sortPanel);
            this.jFrame.getContentPane().remove(this.button);
        }
        this.sortPanel = null;
        this.button = null;
    }

    /**
     *
     * @param sortClassName
     * @return
     */
    protected SortInterface createSortInterface(String sortClassName) {
        if (null != sortClassName){
            try {
                Class<SortInterface> clz = (Class<SortInterface>)Class.forName(sortClassName);
                return clz.newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     *
     * @param panelName
     * @return
     */
    private SortPanel createSlotPanel(String panelName){
        String name = "sorting.ui.SortPanel";
        if (  "bucketsort".equalsIgnoreCase(panelName)){
            name = "sorting.ui.BucketSortPanel";
        }
        Slot[] slots = generater.generate(SlotConst.SLOT_NUM);
        try {
            Class<?> aClass = Class.forName(name);
            Constructor<?> constructor = aClass.getConstructor(new Slot[]{}.getClass());
            return (SortPanel) constructor.newInstance(new Object[]{
                    slots
            });
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
