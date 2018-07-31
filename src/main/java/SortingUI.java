import sorting.SimpleSlot;
import sorting.Slot;
import sorting.control.BubbleSort;
import sorting.ui.SortPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 排序的UI呈现
 * @author evan
 * create-date 2018/7/31
 */
public class SortingUI {

    private static Slot[] genSlot(int len) {
        Random random = new Random();
        if (len<1){
            len = 20;
        }

        Slot[] bars = new Slot[len];
        for (int i = 0; i < bars.length; i++) {
            bars[i] = new SimpleSlot(i,random.nextInt(100));
        }
        return bars;
    }


    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        Slot[] slots = genSlot(20);

        final SortPanel sortPanel = new SortPanel(slots);
        jFrame.setLayout(new BorderLayout());
        jFrame.add(sortPanel,BorderLayout.CENTER);

        // 排序线程
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        final BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.enableStepMode();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                bubbleSort.sort(slots);
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
                        bubbleSort.next();
                        sortPanel.repaint();
                    }
                });
            }
        });

        jFrame.add(button,BorderLayout.SOUTH);
        jFrame.setSize(600,400);
        jFrame.setVisible(true);

    }
}
