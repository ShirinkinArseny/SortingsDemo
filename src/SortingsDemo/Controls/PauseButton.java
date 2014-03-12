package SortingsDemo.Controls;

import SortingsDemo.Rectangle;

import static org.lwjgl.opengl.GL11.glVertex2f;

public class PauseButton extends IconifiedButton {
    private boolean isChecked;
    private boolean oldIsDown;

    public PauseButton(int x, int y, int w, int h) {
        super(x, y, w, h, 2);
        float w1=w/7;
        float h1=h/7;
        rows[0]=new Rectangle(x+2*w1, y+h1, w1, 5*h1);
        rows[1]=new Rectangle(x+4*w1, y+h1, w1, 5*h1);
        isChecked=oldIsDown=false;
    }

    public void drawBackground() {
        super.drawBackground();
        if (isChecked)
        colors[gradations-1][currentColorTheme].bind();
        else
        colors[isSelected/2][currentColorTheme].bind();
        for (int i=0; i<count; i++){
            glVertex2f(rows[i].getX(), rows[i].getY());
            glVertex2f(rows[i].getX(), rows[i].getY2());
            glVertex2f(rows[i].getX2(), rows[i].getY2());
            glVertex2f(rows[i].getX2(), rows[i].getY());
        }
    }

    public void update(int xPos, int yPos, boolean isDown) {
        super.update(xPos, yPos, isDown);
        if (contains(xPos, yPos))
        {
            if (isDown && !oldIsDown)
            {
                isChecked=!isChecked;
            }
        }
        oldIsDown=isDown;
    }
}
