package SortingsDemo.Controls;

import SortingsDemo.Rectangle;

import static org.lwjgl.opengl.GL11.glVertex2f;

public abstract class IconifiedButton extends Button {

    protected Rectangle rows[];
    protected int count;

    public IconifiedButton(int x, int y, int w, int h, int count) {
        super(x, y, w, h, "");
        this.count=count;
        rows=new Rectangle[count];
    }

    public void drawTitle() {
    }

    public void drawBackground() {
        super.drawBackground();
        colors[isSelected].bind();
        for (int i=0; i<count; i++){
        glVertex2f(rows[i].getX(), rows[i].getY());
        glVertex2f(rows[i].getX(), rows[i].getY2());
        glVertex2f(rows[i].getX2(), rows[i].getY2());
        glVertex2f(rows[i].getX2(), rows[i].getY());
        }
    }
}
