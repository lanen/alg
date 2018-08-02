package sorting.ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * @author evan
 * create-date 2018/8/2
 */
public class SimpleControlPanel  {
    private JComboBox comboBox;
    private JCheckBox checkBox;
    private JButton goButton;
    private JPanel content;
    private JSlider slider1;

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
    private PageManage pageManage;

    private boolean checkedAutoStep = true;

    private int period = 200;

    public void setPageManage(PageManage pageManage) {
        this.pageManage = pageManage;
    }

    public SimpleControlPanel() {

        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (null == pageManage) {
                    return;
                }

                if (null == selectItem) {
                    return;
                }

                pageManage.switchPage(selectItem, checkedAutoStep, period);
            }
        });

        for (int i = 0; i < ITEMS.length; i++) {
            this.comboBox.addItem(ITEMS[i]);
        }

        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                selectItem = (String) e.getItem();
            }
        });
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkedAutoStep = !checkedAutoStep;
            }
        });

        slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                period = slider1.getValue();
            }
        });
    }

    public JPanel getContent() {
        return content;
    }

}
