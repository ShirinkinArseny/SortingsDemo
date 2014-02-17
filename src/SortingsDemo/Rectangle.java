package SortingsDemo;

public class Rectangle {

    protected int x, y, w, h, x2, y2;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public boolean contains(int xPos, int yPos) {
        return (xPos>x && xPos<x2 && yPos>y && yPos<y2);
    }

    public Rectangle(float x, float y, float w, float h) {
        this.x=(int)x;
        this.y=(int)y;
        this.w=(int)w;
        this.h=(int)h;
        x2=(int)(x+w);
        y2=(int)(y+h);
    }

    public Rectangle(int x, int y, int w, int h) {
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        x2=x+w;
        y2=y+h;
    }
}
