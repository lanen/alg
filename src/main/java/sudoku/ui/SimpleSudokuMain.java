package sudoku.ui;

import javax.swing.*;

/**
 * @author evan
 * create-date 2018/8/3
 */
public class SimpleSudokuMain {

    private JPanel content;

    private SudokuPanel sudukuPanel;


    public SimpleSudokuMain() {

        sudukuPanel = new SudokuPanel();

        content.add(sudukuPanel);
    }

    public JPanel getContent() {
        return content;
    }
}
