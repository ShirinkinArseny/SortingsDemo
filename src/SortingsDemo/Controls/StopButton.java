package SortingsDemo.Controls;

import SortingsDemo.Rectangle;

public class StopButton extends IconifiedButton {

    public StopButton(int x, int y, int w, int h) {
        super(x, y, w, h, 4);
        float w1=w/7;
        float h1=h/7;
        rows[0]=new Rectangle(x+1*w1, y+1*h1, 5*w1, h1);
        rows[1]=new Rectangle(x+1*w1, y+1*h1, w1, 5*h1);
        rows[2]=new Rectangle(x+5*w1, y+1*h1, w1, 5*h1);
        rows[3]=new Rectangle(x+1*w1, y+5*h1, 5*w1, h1);
    }
}
