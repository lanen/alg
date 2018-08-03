package sudoku;

import java.util.Random;

/**
 * @author evan
 * create-date 2018/8/3
 */
public class Sudoku {

    interface SudokuLevel {
        void update(byte[][] data);
    }

    /**
     * 控制生成数
     */
    public static class ControlNumberSudoku implements SudokuLevel {

        private Random random = new Random();

        /**
         *
         */
        private Sudoku sudoku;

        public ControlNumberSudoku(Sudoku sudoku) {
            this.sudoku = sudoku;
        }

        /**
         *
         * @param level
         * @return
         */
        private int createCount(int level){

            if(level> 3){
                level = 3;
            }

            if (level<1){
                level = 1;
            }

            int count = 50;
            if (1 == level){
                count = 50 + random.nextInt(10);
            }

            if (2 == level){
                count = 40 + random.nextInt(10);
            }

            if (3 == level){
                count = 30 + random.nextInt(10);
            }
            return count;
        }

        private int count;

        public void update(int level, byte[][] data){
            this.count = createCount(level);
            update(data);
        }

        @Override
        public void update(byte[][] data) {
            int counter = count;
            while(counter > 0){
                int c = random.nextInt(9);
                int r = random.nextInt(9);
                int v = random.nextInt(9);
                if (sudoku.update(r,c, (byte)v)){
                    counter --;
                }
            }
        }
    }

    byte[][] data = new byte[BoxConfig.MAX_LEN][BoxConfig.MAX_LEN];

    private ControlNumberSudoku controlNumberSudoku = new ControlNumberSudoku(this);
    /**
     *
     */
    public Sudoku() {
        for(int row = 0; row < BoxConfig.MAX_LEN; row++){
            for(int col = 0; col < BoxConfig.MAX_LEN; col++) {
                data[row][col] = 0;
            }
        }
        controlNumberSudoku.update(2, data);

    }

    /**
     *
     * @param row
     * @param col
     * @param num
     */
    public boolean update(int row, int col, byte num) {

        if (row >=0 &&row <BoxConfig.MAX_LEN) {
            if (col >=0 &&col <BoxConfig.MAX_LEN) {
                if (check(row,col,num)){
                    data[row][col] = num;
                    return true;
                }else{
                    // 写入错误
                    return false;
                }
            }
        }
        return false;
    }

    public boolean check(int row, int col, byte num){
        if (checkBox(row, col, num)){
            if (checkRow(row, num)){
                if (checkCol(col, num)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkRow(int row, byte num){
        for (int i = 0; i < data[row].length; i++) {
            if (num == data[row][i]){
                return false;
            }
        }
        return true;
    }

    public boolean checkCol(int col, byte num){
        for(int row = 0; row <BoxConfig.MAX_LEN;row++){
            if (num == data[row][col]){
                return false;
            }
        }
        return true;
    }

    public boolean checkBox(int row, int col, byte num){
        int mRow = row % 3;
        int mCol = col % 3;
        for(int r = row; r<=mRow;r++){
            for(int c = col; c<=mCol; c++ ){
                if (num == data[r][c]){
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 判断指定行是否完成
     * @param row
     * @return
     */
    public boolean isRowFull(int row){
        if (row >=0 &&row <BoxConfig.MAX_LEN) {
            for(int col = 0; col < BoxConfig.MAX_LEN; col++) {
                ;
                if(data[row][col] == 0) {
                    return false;
                }
            }
            ;
            return true;
        }
        return false;
    }

    public boolean isColFull(int col){
        if (col >=0 && col < BoxConfig.MAX_LEN) {
            for(int row = 0; row < BoxConfig.MAX_LEN; row++) {

                if(data[row][col] == 0) {
                    ;
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 判断是否完成
     * @return
     */
    public boolean isFinished(){
        for(int row = 0; row < BoxConfig.MAX_LEN; row++) {
            if( ! isColFull(row)) {
                return false;
            }
        }
        return false;
    }

    public byte[][] getData() {
        return data;
    }

    public boolean done(){
        return false;
    }
}