package SortingsDemo.Controls;

import SortingsDemo.Font;
import SortingsDemo.Rectangle;
import org.newdawn.slick.Color;

import static org.lwjgl.opengl.GL11.glVertex2f;

public class Control extends Rectangle {
    protected int isSelected=0;
    protected static Color colors[][];
    protected static int currentColorTheme=0;
    protected static boolean drawShadow=true;
    protected static final int gradations=10;
    protected String text;

    //for background
    protected static final Color [] backgroundColor = new Color[]
            {new Color(0.2f, 0.2f, 0.2f), new Color(0x2d2d2d)};
    //for shadows
    protected static final Color [] backgroundTransparentColor = new Color[]
            {new Color(0f, 0f, 0f, 0.2f), new Color(0xdedede)};

    //for selections
    protected static final Color [] selectionColor = new Color[]
            {new Color(1f, 0.35f, 0.35f), new Color(0xd64937)};

    //for selected text
    protected static final Color [] textLightColor = new Color[]
            {new Color(0.5f, 0.5f, 0.5f), new Color(0xdedede)};
    //for text
    protected static final Color [] textColor = new Color[]
            {new Color(0.1f, 0.1f, 0.1f), new Color(0x2d2d2d)};

    //for window background
    protected static final Color [] lightBackground = new Color[]
            {new Color(0.7f, 0.7f, 0.7f), new Color(0xdedede)};

    //for array rows
    protected static final Color [] rowSelectionColor = new Color[]
            {new Color(1f, 0.1f, 0.1f, 0.5f), new Color(0xd64937)};
    protected static final Color [] rowColor = new Color[]
            {new Color(0f, 0f, 0f, 0.6f), new Color(0x2d2d2d)};



    public static Color getBackgroundColor() {
        return textLightColor[currentColorTheme];
    }

    public static Color getLightBackgroundColor() {
        return lightBackground[currentColorTheme];
    }

    public static void changeColorTheme() {
        if (currentColorTheme==0)
            currentColorTheme=1;
        else currentColorTheme=0;
        drawShadow=!drawShadow;
    }

    protected static Color mixColors(Color c1, Color c2, float mixCoeff) {
        float oMM=1-mixCoeff;
        return new Color(
                c1.r*mixCoeff+c2.r*oMM,
                c1.g*mixCoeff+c2.g*oMM,
                c1.b*mixCoeff+c2.b*oMM,
                c1.a*mixCoeff+c2.a*oMM);
    }

    public static void init() {
        colors=new Color[gradations][backgroundColor.length];
        for (int i=0; i<gradations; i++)   {
            for (int j=0; j<backgroundColor.length; j++)
            colors[i][j]=mixColors(selectionColor[j], textLightColor[j], i*1f/gradations);
        }
    }

    public void drawBackground() {
        backgroundColor[currentColorTheme].bind();
        glVertex2f(x, y);
        glVertex2f(x, y2);
        glVertex2f(x2, y2);
        glVertex2f(x2, y);
        if (drawShadow){
        backgroundTransparentColor[currentColorTheme].bind();
        glVertex2f(x, y2);
        glVertex2f(x2, y2);
        Color.transparent.bind();
        glVertex2f(x2 + 3, y2 + 10);
        glVertex2f(x - 3, y2 + 10);  }
    }

    public void drawTitle() {
        Font.drawString(x, y, text, textLightColor[currentColorTheme]);
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
