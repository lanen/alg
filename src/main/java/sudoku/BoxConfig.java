package sudoku;

/**
 * @author evan
 * create-date 2018/8/3
 */
public interface BoxConfig {
    /**
     * 每行格子数
     */
    int MAX_LEN = 9;
    /**
     * 分割线
     */
    int MAX_LINE = 4;

    int WIDTH = 20;
    int HEIGHT = WIDTH;

    int BOX_SP = 6;
    int LINE_SP = 3;

    int OFFSET_X = 40;
    int OFFSET_Y = 100;

    int PANE_WIDTH = (WIDTH + BOX_SP) * 9 + LINE_SP * 4 + WIDTH;
    int PANE_HEIGHT = (HEIGHT + BOX_SP) * 9 + LINE_SP * 4 + HEIGHT;


    //

    int BUCKET_OFFSET_X = OFFSET_X;
    int BUCKET_OFFSET_Y = OFFSET_Y + PANE_HEIGHT + 30;

}
