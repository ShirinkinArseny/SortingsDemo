package SortingsDemo.Controls;

import SortingsDemo.Font;
import static org.lwjgl.opengl.GL11.glVertex2f;

public class SelectBox extends Control {

    private boolean oldIsDown;
    private Runnable[] events;
    private String[] texts;
    private int[] coordX;
    private int size;
    private int selectedItem;
    private float selectorPosition;
    private float width;
    private static final float speed=7f;

    public SelectBox(int x, int y, int w, int h, int size) {
        super(x, y, w, h);
        events = new Runnable[size];
        texts = new String[size];
        coordX = new int[size+1];
        width = w / size;
        float x2 = 0;
        for (int i = 0; i < size+1; i++) {
            coordX[i] = (int)x2;
            x2 += width;
        }
        this.size=size;
        selectedItem=0;
        selectorPosition=0;
    }

    public void setEvent(int index, Runnable e) {
        events[index] = e;
    }

    public void setTitle(int index, String s)  {
        texts[index] = s;
    }

    public void drawTitle() {
        for (int i = 0; i < size; i++) {
            Font.drawString(x+ coordX[i], y, texts[i],
                    (i==selectedItem)? textColor[currentColorTheme]: textLightColor[currentColorTheme]);
        }
    }

    public void drawBackground() {
        super.drawBackground();
        colors[isSelected][currentColorTheme].bind();
        float selectorStart=x + selectorPosition;
        glVertex2f(selectorStart + 3, y + 3);
        glVertex2f(selectorStart + 3, y2 - 3);
        glVertex2f(selectorStart + width - 1, y2 - 3);
        glVertex2f(selectorStart + width - 1, y + 3);
    }

    public void update(int xPos, int yPos, boolean isDown) {
        if (contains(xPos, yPos)) {
            if (isDown && !oldIsDown)
                for (int i=0; i<size+1; i++)
                    if (xPos<=coordX[i]+x)
                    {
                        events[i-1].run();
                        selectedItem=i-1;
                        break;
                    }
            if (isSelected < gradations - 1) isSelected++;
        } else if (isSelected > 0) isSelected--;
        if (coordX[selectedItem]<selectorPosition-1)
            selectorPosition=Math.max(coordX[selectedItem], selectorPosition-speed);
        else
        if (coordX[selectedItem]-1>selectorPosition)
            selectorPosition=Math.min(coordX[selectedItem], selectorPosition + speed);
        oldIsDown = isDown;
    }
}
