package SortingsDemo.Controls;

import SortingsDemo.Rectangle;

public class ShuffleButton extends IconifiedButton {

    public ShuffleButton(int x, int y, int w, int h) {
        super(x, y, w, h, 3);
        float w1=w/7;
        float h1=h/7;
        rows[0]=new Rectangle(x+w1, y+3*h1, w1, 3*h1);
        rows[1]=new Rectangle(x+3*w1, y+h1, w1, 5*h1);
        rows[2]=new Rectangle(x+5*w1, y+2*h1, w1, 4*h1);
    }
}
