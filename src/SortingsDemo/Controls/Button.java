package SortingsDemo.Controls;

public class Button extends Control {

    private boolean oldIsDown;
    private Runnable event;

    public Button(int x, int y, int w, int h, String s) {
        super(x, y, w, h);
        text=s;
    }

    public void setEvent(Runnable e) {
        event=e;
    }

    public void runEvent() {
        event.run();
    }

    public void update(int xPos, int yPos, boolean isDown) {
        if (contains(xPos, yPos))
        {
            if (isDown && !oldIsDown)
                runEvent();
            if (isSelected<gradations-1) isSelected++;
        }
        else if (isSelected>0) isSelected--;
        oldIsDown=isDown;
    }
}
