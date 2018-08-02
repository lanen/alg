package sorting;

import java.awt.*;

/**
 * @author evan
 * create-date 2018/7/31
 */
public class SimpleBucketSlot implements Slot {

    public final static int WIDTH = 20;
    public final static int HEIGHT = 5;
    public final static int SP = WIDTH / 2;
    public final static int WIDTH_HALF = WIDTH/2;

    private Color color;

    private int value;

    private int position;

    public SimpleBucketSlot(int position, int value) {
        this.value = value;

        this.color = Color.BLACK;
        if (position % 2 ==0 ){
            this.color = Color.RED;
        }
        this.position = position;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void updatePosition(int position) {
        this.position = position;
    }

    @Override
    public int x(int startX){
        return startX + (position+1) * (WIDTH + SP);
    }

    @Override
    public int y(int startY){
        return startY - value;
    }

    @Override
    public int width(){
        return WIDTH;
    }

    @Override
    public int height(){
        return HEIGHT;
    }
}
