package SortingsDemo.Controls;

import SortingsDemo.Font;

public class TextOutBox extends Control {

    public TextOutBox(int x, int y, int w, int h, String s) {
        super(x, y, w, h);
        text=s;
    }

    public void drawTitle() {
        Font.drawString(x, y, text, textDarkColor);
    }

    public void drawBackground() {
    }
}
