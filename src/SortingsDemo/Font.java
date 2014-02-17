package SortingsDemo;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;


public class Font {

    private static final String additionAlphabet ="АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static TrueTypeFont font;

    public static void init() {
        try {
            java.awt.Font awtFont2 = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, Font.class.getResourceAsStream("urw-gothic-l-book.ttf"));
            awtFont2 = awtFont2.deriveFont(16f);
            font = new TrueTypeFont(awtFont2, true, additionAlphabet.toCharArray());
            System.out.println("[FONT] "+Language.MSG_FontLoaded);
        } catch (Exception e) {
            System.err.println("[FONT] "+Language.ERR_FontCanNotBeLoaded+" "+e);
        }
    }

    public static void drawString(float x, float y, String s, Color color) {
        font.drawString(x+5, y+5, s, color);
    }

}
