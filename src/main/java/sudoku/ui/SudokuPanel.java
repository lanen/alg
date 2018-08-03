package sudoku.ui;

import sudoku.BoxConfig;
import sudoku.Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author evan
 * create-date 2018/8/3
 */
public class SudokuPanel extends JPanel {

    private static class NumBoxBorder {
        public Color color;
    }

    private static class NumBox {
        // 位置信息
        public int x;
        public int y;

        public Color fillColor;

        // 边框信息
        public NumBoxBorder numBoxBorder;

        // 默认颜色
        static Color DEFAULT_COLOR;

        // 字符
        public int str_x;
        public int str_y;
        public String str;
        public boolean hasStr=false;

        public int row;
        public int col;
    }

    private static class NumSplitLine {

        public int x1;
        public int y1;

        public int x2;
        public int y2;

        Color color;
    }

    private static class NumPane {

        private NumBox[][] boxs = null;

        int len = BoxConfig.MAX_LEN;

        private NumSplitLine[] lines;

        public NumPane(int len) {
            this.len = len;

            boxs = new NumBox[len][len];
            for (int i = 0; i < this.len; i++) {
                for (int j = 0; j < this.len; j ++){
                    NumBox b = new NumBox();
                    b.fillColor = Color.gray;
                    b.row = i;
                    b.col = j;
                    boxs[i][j] = b;
                }
            }
            lines= new NumSplitLine[BoxConfig.MAX_LINE];
            for (int i = 0; i < lines.length; i++) {
                lines[i] = new NumSplitLine();
                lines[i].color = Color.gray;
            }
            initPositionOfBox();
            initPositionOfLine();
        }

        /**
         *
         */
        private void initPositionOfBox() {

            int x = BoxConfig.OFFSET_X;
            int y = BoxConfig.OFFSET_Y;

            for(int row = 0; row < len; row++){
                y += (BoxConfig.HEIGHT + BoxConfig.BOX_SP);
                if(row >1 && row % 3 == 0){
                    y += 2*BoxConfig.LINE_SP;
                }

                for (int col = 0; col < len; col++) {
                    boxs[row][col].y = y;
                }
            }

            for (int col = 0; col < len; col++) {
                x += (BoxConfig.WIDTH + BoxConfig.BOX_SP);
                if (col > 1 && col % 3 ==0 ){
                    x += 2*BoxConfig.LINE_SP;
                }
                for(int row = 0; row < len; row++) {
                    boxs[row][col].x = x;
                }
            }
        }

        private void initPositionOfLine(){
            // line 1 横
            int y = BoxConfig.OFFSET_Y + 4*(BoxConfig.HEIGHT + BoxConfig.BOX_SP)  ;
            lines[0].x1 = BoxConfig.OFFSET_X + BoxConfig.WIDTH;
            lines[0].y1 = y;
            lines[0].x2 = BoxConfig.OFFSET_X + BoxConfig.PANE_WIDTH + BoxConfig.BOX_SP ;
            lines[0].y2 = y;

            // line 2
            y += 3 * (BoxConfig.HEIGHT + BoxConfig.BOX_SP) + BoxConfig.LINE_SP*2;
            lines[1].x1 = BoxConfig.OFFSET_X + BoxConfig.WIDTH ;
            lines[1].y1 = y;
            lines[1].x2 = BoxConfig.OFFSET_X+BoxConfig.PANE_WIDTH + BoxConfig.BOX_SP;
            lines[1].y2 = y;

            // line 3 竖
            int x = BoxConfig.OFFSET_X + 4 * (BoxConfig.HEIGHT + BoxConfig.BOX_SP);
            lines[2].x1 = x;
            lines[2].y1 = BoxConfig.OFFSET_Y + BoxConfig.HEIGHT ;
            lines[2].x2 = x;
            lines[2].y2 = BoxConfig.OFFSET_Y+ BoxConfig.PANE_HEIGHT + BoxConfig.BOX_SP;

            // line 4 竖
            x += 3 * (BoxConfig.WIDTH + BoxConfig.BOX_SP) + BoxConfig.LINE_SP*2;
            lines[3].x1 = x;
            lines[3].y1 = BoxConfig.OFFSET_Y +BoxConfig.HEIGHT ;
            lines[3].x2 = x;
            lines[3].y2 = BoxConfig.OFFSET_Y+ BoxConfig.PANE_HEIGHT + BoxConfig.BOX_SP;
        }

    }

    private static class NumBucket {
        int x;
        int y;
        byte value;
        String str;
        int str_x;
        int str_y;

        int capacity = 9;

    }
    private static class NumBucketPane{

        NumBucket[] buckets;

        public NumBucketPane(int len) {

            buckets = new NumBucket[len];
            for (int i = 0; i < buckets.length; i++) {
                buckets[i] = new NumBucket();
                buckets[i].value = (byte)(1+i);
                buckets[i].str = String.valueOf(buckets[i].value);
            }

            int x = BoxConfig.BUCKET_OFFSET_X;

            for (int col = 0; col < len; col++) {
                x += (BoxConfig.WIDTH + BoxConfig.BOX_SP);
                if (col > 1 && col % 3 ==0 ){
                    x += 2*BoxConfig.LINE_SP;
                }

                buckets[col].y = BoxConfig.BUCKET_OFFSET_Y;
                buckets[col].x = x;

                buckets[col].str_x = x+BoxConfig.WIDTH/2-4;
                buckets[col].str_y = buckets[col].y+BoxConfig.HEIGHT-5 ;
            }
        }
    }


    private NumPane pane = new NumPane(BoxConfig.MAX_LEN);
    private NumBox selected = null;
    private NumBucketPane bucketPane = new NumBucketPane(BoxConfig.MAX_LEN);

    private Sudoku sudoku = new Sudoku();

    public SudokuPanel() {

        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseClickedOnSelected(e.getX(), e.getY());

                // update
                mouseClickedOnBucket(e.getX(), e.getY());
                if (null!=selected){
                    repaint();
                }
            }
        });

        byte[][] data = sudoku.getData();

        for(int row = 0; row < BoxConfig.MAX_LEN; row++){
            for (int col = 0; col < BoxConfig.MAX_LEN; col++) {
                if(0<data[row][col]){
                    updateNumBoxStr(pane.boxs[row][col], String.valueOf(data[row][col]));
                }
            }
        }
    }

    /**
     * 判断是否点击中了目标
     * @param x
     * @param y
     */
    private void mouseClickedOnSelected(int x, int y){
        if (x < BoxConfig.OFFSET_X){
            return;
        }
        if (x > BoxConfig.OFFSET_X + BoxConfig.PANE_WIDTH){
            return;
        }
        if (y < BoxConfig.OFFSET_Y){
            return;
        }
        if (y > BoxConfig.OFFSET_Y + BoxConfig.PANE_HEIGHT){
            return;
        }

        for(int row = 0; row < pane.len; row++){
            for (int col = 0; col < pane.len; col++) {
                if (x >= pane.boxs[row][col].x
                        && x <= pane.boxs[row][col].x + BoxConfig.WIDTH){
                    if (y >= pane.boxs[row][col].y
                           && y<= pane.boxs[row][col].y+ BoxConfig.HEIGHT){
                        selected = pane.boxs[row][col];
                        break;
                    }
                }
            }
        }
    }

    private void mouseClickedOnBucket(int x, int y){
        if (x < BoxConfig.BUCKET_OFFSET_X){
            return;
        }
        if (x > BoxConfig.BUCKET_OFFSET_X + BoxConfig.PANE_WIDTH){
            return;
        }
        if (y < BoxConfig.BUCKET_OFFSET_Y){
            return;
        }
        if (y > BoxConfig.BUCKET_OFFSET_Y + BoxConfig.HEIGHT){
            return;
        }
        for(int row = 0; row < bucketPane.buckets.length; row++){
            if (x >= bucketPane.buckets[row].x
                    && x <= bucketPane.buckets[row].x + BoxConfig.WIDTH){
                if (y >= bucketPane.buckets[row].y
                        && y<= bucketPane.buckets[row].y+ BoxConfig.HEIGHT){

                    if( null != selected){
                        if (!selected.hasStr){
                            boolean check = sudoku.update(selected.row, selected.col, bucketPane.buckets[row].value);
                            if (!check){
                                //进入错误环节
                                return;
                            }
                            updateNumBoxStr(selected, String.valueOf(bucketPane.buckets[row].value));
                        }
                    }
                    break;
                }
            }
        }
    }

    /**
     *
     * @param selectedBox
     * @param str
     */
    private void updateNumBoxStr(NumBox selectedBox, String str){
        selectedBox.str = str;
        selectedBox.str_x = selectedBox.x + BoxConfig.WIDTH/2-4;
        selectedBox.str_y = selectedBox.y + BoxConfig.HEIGHT-5;
        selectedBox.hasStr = true;
    }

    @Override
    public void paint(Graphics g) {

        for(int row = 0; row < pane.len; row++){
            for (int col = 0; col < pane.len; col++) {
                drawBox(pane.boxs[row][col],g);
            }
        }

        for(int i = 0; i < pane.lines.length; i++) {
            drawLine(pane.lines[i], g);
        }

        // 画字符
        for(int row = 0; row < pane.len; row++){
            for (int col = 0; col < pane.len; col++) {
                drawNumInBox(pane.boxs[row][col],g);
            }
        }

        for (int i = 0; i < bucketPane.buckets.length; i++) {
            drawBucket(bucketPane.buckets[i], g);
        }
    }


    private void drawBox(NumBox box, Graphics g){
        g.setColor(box.fillColor);
        g.fillRect(box.x, box.y, BoxConfig.WIDTH, BoxConfig.HEIGHT);

        if (null != box.numBoxBorder){
            g.setColor(box.numBoxBorder.color);
            g.drawRect(box.x, box.y, BoxConfig.WIDTH, BoxConfig.HEIGHT);
        }
        if (null != selected){
            g.setColor(Color.cyan);
            g.drawRect(selected.x-1, selected.y-1, BoxConfig.WIDTH+1, BoxConfig.HEIGHT+1);
        }
    }

    private void drawLine(NumSplitLine line, Graphics g){
        g.setColor(line.color);
        g.drawLine(line.x1,line.y1, line.x2,line.y2);
    }

    private void drawBucket(NumBucket bucket, Graphics g){
        g.setColor(Color.BLACK);
        g.drawRect(bucket.x, bucket.y, BoxConfig.WIDTH, BoxConfig.HEIGHT);
        g.drawString(bucket.str, bucket.str_x, bucket.str_y);
    }

    private void drawNumInBox(NumBox box, Graphics g){
        if (null != box.str){
            g.setColor(Color.WHITE);
            g.drawString(box.str, box.str_x, box.str_y);
        }
    }
}
