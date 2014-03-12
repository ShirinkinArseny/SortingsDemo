package SortingsDemo.Controls;

import SortingsDemo.Rectangle;

import static org.lwjgl.opengl.GL11.glVertex2f;

public class ThemeButton extends Button {

    protected Rectangle rows[][];
    private boolean checked=false;

    public void drawTitle() {
    }

    public void runEvent() {
        super.runEvent();
        checked=!checked;
    }

    public void drawBackground() {
        super.drawBackground();
        colors[isSelected][currentColorTheme].bind();
        int count = checked?6:5;
        int num=checked?0:1;
        for (int i=0; i<count; i++){
            glVertex2f(rows[i][num].getX(), rows[i][num].getY());
            glVertex2f(rows[i][num].getX(), rows[i][num].getY2());
            glVertex2f(rows[i][num].getX2(), rows[i][num].getY2());
            glVertex2f(rows[i][num].getX2(), rows[i][num].getY());
        }
    }

    public ThemeButton(int x, int y, int w, int h) {
        super(x, y, w, h, null);
        rows=new Rectangle[6][2];
        float w1=w/12;
        float h1=h/12;
        rows[0][0]=new Rectangle(x+w1, y+2*h1, w1, 6*h1);
        rows[1][0]=new Rectangle(x+w1, y+2*h1, 8*w1, h1);

        rows[2][0]=new Rectangle(x+3*w1, y+4*h1, w1, 6*h1);
        rows[3][0]=new Rectangle(x+3*w1, y+4*h1, 8*w1, h1);
        rows[4][0]=new Rectangle(x+3*w1, y+9*h1, 8*w1, h1);
        rows[5][0]=new Rectangle(x+10*w1, y+4*h1, w1, 6*h1);


        rows[0][1]=new Rectangle(x+w1, y+2*h1, w1, 6*h1);
        rows[1][1]=new Rectangle(x+w1, y+2*h1, 8*w1, h1);
        rows[2][1]=new Rectangle(x+w1, y+7*h1, 2*w1, h1);
        rows[3][1]=new Rectangle(x+8*w1, y+2*h1, w1, 2*h1);

        rows[4][1]=new Rectangle(x+3*w1, y+4*h1, 8*w1, 6*h1);
    }
}
