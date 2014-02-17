package SortingsDemo;

import SortingsDemo.Controls.*;
import SortingsDemo.Sortings.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Color;

import java.util.Timer;
import java.util.TimerTask;

import static org.lwjgl.opengl.GL11.*;

public class Window {

    private static int currentSorting = -1;
    private static boolean isWorking = true;
    private static Color mainColor = new Color(0.4f, 0.4f, 0.4f, 1f);
    private static Color topColor = new Color(0.7f, 0.7f, 0.7f, 1f);
    private static MasterControl masterControl = new MasterControl();
    private static Sort[] sorts;

    public static Sort getCurrentSorting() {
        if (currentSorting!=-1)
            return sorts[currentSorting];
        else return null;
    }

    public static int getCurrentSortingNumber() {
        return currentSorting;
    }

    public static void setCurrentSorting(int i) {
        currentSorting = i;
    }

    private static void initDisplay() {
        try {
            int w = 1000;
            int h = 700;
            Display.setDisplayMode(new DisplayMode(w, h));
            Display.setVSyncEnabled(true);
            Display.create();
            Display.setTitle("Sorts");
            glClearColor(0.4f, 0.4f, 0.4f, 1f);
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            glOrtho(0, w, h, 0, 1, 0);
            Font.init();
            Mouse.create();
            System.out.println("[WINDOW] " + Language.MSG_DisplayInitialised);
        } catch (LWJGLException e) {
            System.err.println("[WINDOW] " + Language.ERR_DisplayIsNotInitialised);
            exit();
        }
    }

    private static void initUpdateThread() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    masterControl.update(Mouse.getX(), Display.getHeight() - Mouse.getY(), Mouse.isButtonDown(0));
                }
                catch (Exception e){ cancel(); }
            }
        }, 0, 10);
        System.out.println("[WINDOW] " + Language.MSG_UpdateTimerCreated);
    }

    private static void initSorts() {
        sorts=new Sort[6];
        sorts[0]=new ChoiceSort();
        sorts[1]=new BubbleSort();
        sorts[2]=new QuickSort();
        sorts[3]=new InsertSort();
        sorts[4]=new ShellSort();
        sorts[5]=new HeapSort();
    }

    private static void initSortingThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isWorking)
                    if  (currentSorting !=-1)
                                try {sorts[currentSorting].sort(masterControl.getSortable());
                                }
                                catch (Exception e) {
                                    System.err.println("[WINDOW] " +Language.ERR_SortingThreadHasException+" : "+e);
                                }
                    else
                            try {
                                Thread.sleep(100);
                            } catch (Exception e) {
                                System.err.println("[WINDOW] " + Language.ERR_SortingThreadHasException+" : "+e);
                            }
            }
        }).start();
        System.out.println("[WINDOW] " + Language.MSG_SortingThreadCreated);
    }

    private static void drawWhileWorks() {
        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            glBegin(GL_QUADS);
            {
                topColor.bind();
                glVertex2f(0, 0);
                glVertex2f(Display.getWidth(), 0);
                mainColor.bind();
                glVertex2f(Display.getWidth(), 300);
                glVertex2f(0, 300);
                masterControl.drawBackground();
            }
            glEnd();

            glEnable(GL_TEXTURE_2D);
            glBegin(GL_QUADS);
            masterControl.drawTitle();
            glEnd();
            glDisable(GL_TEXTURE_2D);

            Display.update();
        }
    }

    private static void exit() {
        isWorking = false;
        Mouse.destroy();
        Display.destroy();
        System.out.println("[WINDOW] " + Language.MSG_Exiting);
        System.exit(0);
    }

    public static void main(String[] args) {
        initDisplay();
        initSorts();
        initSortingThread();
        Control.init();
        initUpdateThread();
        drawWhileWorks();
        exit();
    }
}
