import sudoku.ui.SimpleSudokuMain;

import javax.swing.*;

/**
 * @author evan
 * create-date 2018/8/3
 */
public class SudokuUI extends UI{


    private SimpleSudokuMain createSimpleSudokuMain(){
        SimpleSudokuMain s = new SimpleSudokuMain();
        return s;
    }


    public static void main(String[] args) {
        SudokuUI sudokuUI = new SudokuUI();

        JFrame jFrame = sudokuUI.createJFrame("");
        jFrame.add(sudokuUI.createSimpleSudokuMain().getContent());
        jFrame.setVisible(true);
    }
}
