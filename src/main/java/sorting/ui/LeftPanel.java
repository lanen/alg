package sorting.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * @author evan
 * create-date 2018/7/31
 */
public class LeftPanel extends JPanel {

    static String[] ITEMS = {
            "BubbleSort",
            "QuickSort",
            "SelectionSort",
            "InsertionSort",
            "ShellInsertionSort",
            "MergeSort",
    };

    /**
     *
     */
    private String selectItem = ITEMS[0];

    /**
     *
     */
    private PageControl pageControl;

    private boolean checkedAutoStep = true;

    public LeftPanel(PageControl pageControl) {
        this.pageControl = pageControl;
        setLayout(new GridLayout(4, 1));
        add(new JLabel("算法"));
        add(getAlgComboBox());
        add(checkBox());
        add(button());
    }

    public JButton button() {
        JButton jButton = new JButton("RUN");

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (null == pageControl) {
                    return;
                }

                if (null == selectItem) {
                    return;
                }

                pageControl.switchPage(selectItem, checkedAutoStep, 100);
            }
        });
        return jButton;
    }

    /**
     * 自动的按钮
     * @return
     */
    public JCheckBox checkBox(){

        JCheckBox jCheckBox = new JCheckBox();
        jCheckBox.setSelected(checkedAutoStep);
        jCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                checkedAutoStep = !checkedAutoStep;
            }
        });


        return jCheckBox;
    }

    public JComboBox<String> getAlgComboBox() {
        JComboBox<String> list = new JComboBox<>();
        for (int i = 0; i < ITEMS.length; i++) {
            list.addItem(ITEMS[i]);
        }

        list.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                selectItem = (String) e.getItem();
            }
        });
        return list;
    }
}
