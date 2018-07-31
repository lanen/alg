package sorting.ui;

import sorting.Slot;
import sorting.control.SlotGenerater;
import sorting.control.SortInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.*;

/**
 *
 * @author evan
 * create-date 2018/7/31
 */
public class Page {

    private JFrame jFrame;

    private SortPanel sortPanel;

    private String sortClassName;

    private SortInterface sortInterface;

    private JButton button;

    private ExecutorService executorService;

    private ExecutorService working;

    /**
     *
     * @param jFrame frame
     * @param className class name
     */
    public Page(JFrame jFrame, String className) {

        this.jFrame = jFrame;
        this.sortClassName = className;


        this.sortInterface = createSortInterface(this.sortClassName);
        if (null == sortInterface){
            return;
        }
        SlotGenerater generater = new SlotGenerater();
        Slot[] slots = generater.generate(20);

        this.sortPanel = new SortPanel(slots);

        // 排序线程
        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                sortInterface.sort(slots);
            }
        });


        // 按钮控制线程
        working = Executors.newSingleThreadExecutor();

        this.button = new JButton();
        this.button.setText("Step");
        this.button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                working.submit(new Runnable() {
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
    private ScheduledExecutorService ses;
    /**
     *
     * @param period
     */
    public void autoActionStep(int period){

        ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                actionStep();
                if (sortInterface.sorted()){
                    ses.shutdown();
                }
            }
        }, 200, period, TimeUnit.MILLISECONDS);
    }

    /**
     *
     */
    public void dispose(){

        if (null != this.sortPanel){
            this.sortPanel.invalidate();
            this.sortPanel.setVisible(false);
            this.sortPanel.removeAll();
            this.jFrame.getContentPane().remove(this.sortPanel);
            this.jFrame.getContentPane().remove(this.button);
        }
        this.sortPanel = null;
        this.button = null;

        this.working.shutdown();
        this.executorService.shutdown();
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
}
