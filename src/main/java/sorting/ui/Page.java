package sorting.ui;

import sorting.Slot;
import sorting.control.SlotGenerater;
import sorting.control.SortInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 排序的UI呈现
 * @author evan
 * create-date 2018/7/31
 */
public class Page {

    private JFrame jFrame;

    private SortPanel sortPanel;

    private String sortClassName;

    private SortInterface sortInterface;

    private JButton button = new JButton();

    /**
     *
     * @param jFrame
     */
    public Page(JFrame jFrame, String className) {

        this.jFrame = jFrame;
        this.sortClassName = className;

        SlotGenerater generater = new SlotGenerater();
        Slot[] slots = generater.generate(20);

        this.sortPanel = new SortPanel(slots);

        this.jFrame.add(sortPanel, BorderLayout.CENTER);

        // 排序线程
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        this.sortInterface = createSortInterface(this.sortClassName);

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                sortInterface.sort(slots);
            }
        });


        // 按钮控制线程
        ExecutorService working = Executors.newSingleThreadExecutor();

        JButton button = new JButton();
        button.setText("Step");
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                working.submit(new Runnable() {
                    @Override
                    public void run() {
                        if (null != sortInterface){
                            sortInterface.next();
                        }
                        sortPanel.repaint();
                    }
                });
            }
        });

        jFrame.add(button,BorderLayout.SOUTH);
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
