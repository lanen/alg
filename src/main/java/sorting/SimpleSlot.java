package sorting;

import java.awt.*;

/**
 * @author evan
 * create-date 2018/7/31
 */
public class SimpleSlot implements Slot {

    private  final static int WIDTH = 10;
    private  final static int SP = WIDTH*3;
    private  final static int WIDTH_HALF = WIDTH/2;

    private Color color;

    private int value;

    private int position;

    public SimpleSlot(int position, int value) {
        this.value = value;
        this.color = Color.BLACK;
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
        return startX + (position+1) * WIDTH + SP;
    }

    @Override
    public int y(int startY){
        return startY - value;
    }

    @Override
    public int width(){
        return WIDTH_HALF;
    }

    @Override
    public int height(){
        return value;
    }
}
