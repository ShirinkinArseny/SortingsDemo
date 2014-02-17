package SortingsDemo.Controls;

import SortingsDemo.Font;
import SortingsDemo.Rectangle;
import org.newdawn.slick.Color;

import static org.lwjgl.opengl.GL11.glVertex2f;

public class Control extends Rectangle {
    protected int isSelected=0;
    protected static Color colors[];
    protected static final int gradations=10;
    protected String text;
    protected static final Color backgroundColor = new Color(0f, 0f, 0f, 0.5f);
    protected static final Color backgroundTransparentColor = new Color(0f, 0f, 0f, 0.2f);
    protected static final Color textColor = new Color(0.5f, 0.5f, 0.5f, 1f);
    protected static final Color textDarkColor = new Color(0.1f, 0.1f, 0.1f, 1f);

    public static void init() {
        colors=new Color[gradations];
        for (int i=0; i<gradations; i++)   {
            colors[i]=new Color(0.4f+i/(3f*gradations), 0.4f, 0.4f, 1f);
        }
    }

    public void drawBackground() {
        backgroundColor.bind();
        glVertex2f(x, y);
        glVertex2f(x, y2);
        glVertex2f(x2, y2);
        glVertex2f(x2, y);
        backgroundTransparentColor.bind();
        glVertex2f(x, y2);
        glVertex2f(x2, y2);
        Color.transparent.bind();
        glVertex2f(x2 + 3, y2 + 10);
        glVertex2f(x - 3, y2 + 10);
    }

    public void drawTitle() {
        Font.drawString(x, y, text, textColor);
    }

    public void setText(String s) {
         text=s;
    }

    public void update(int x, int y, boolean isDown) {
    }

    public Control(int x, int y, int w, int h) {
        super(x, y, w, h);
        text="";
    }

}
