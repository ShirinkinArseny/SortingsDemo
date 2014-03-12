package SortingsDemo.Controls;

import SortingsDemo.Language;
import SortingsDemo.Sortings.Sortable;
import SortingsDemo.Window;
import org.newdawn.slick.Color;

import java.util.ArrayList;
import java.util.Random;

import static org.lwjgl.opengl.GL11.glVertex2f;

public class Table extends Control implements Sortable {

    private int rowsCount;//Count of rows
    private float[] arrayPos;//Array, which will be sorted
    private float[] array;//Array, which will be sorted
    private float[] arrayColors;//Colors of rows
    private float[] arrayIdealPos;//Positions of rows (when they are not moving)
    private Color[][][] colorType;//Color for every gradation
    private ArrayList<Integer> queueToMove;
    private Random rnd;
    private float rowWidth;//Width of rows
    private String[][] variables;//Variables names
    private boolean[][] needToDrawVariable;//Variables names
    private boolean canUpdateAndDraw = true;//To synchronise threads
    private boolean oldIsButtonDown = false;//Old state of left mouse button
    private int selectedItem = -1;
    private boolean isPaused = false;
    private int compares = 0;//Count of calling .getItem(int i)
    private int moves = 0;   //Calling of swapping * 2
    private Runnable action;
    private float speed = 3;  //Row moves on speed pixels per update

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float s) {
        speed = s;
    }

    public void setAction(Runnable r) {
        action = r;
    }

    public boolean getIsPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public void resetComparesAndMoves() {
        compares = 0;
        moves = 0;
    }

    public int getCompares() {
        return compares;
    }

    public int getMoves() {
        return moves;
    }

    public Table(int x, int y, int w, int h) {
        super(x, y, w, h);
        queueToMove = new ArrayList<Integer>();
        setItemsCount(16);
        rnd = new Random();
    }

    public void setItemsCount(int c) {
        Window.setCurrentSorting(-1);
        canUpdateAndDraw = false;
        queueToMove.clear();
        rowsCount = c;
        float w2 = (w - 10f) / rowsCount;
        float h2 = (h * 4f / 5) / rowsCount;
        float x2 = 5;
        float y2 = h / 6f;
        array = new float[rowsCount];
        arrayPos = new float[rowsCount];
        arrayColors = new float[rowsCount];
        variables = new String[rowsCount][2];
        needToDrawVariable = new boolean[rowsCount][2];
        colorType = new Color[256][2][2];
        for (int i = 0; i < 256; i++) {
            for (int j=0; j<selectionColor.length; j++) {
            colorType[i][0][j] = mixColors(rowSelectionColor[j],
                    rowColor[j], i/255f);
            colorType[i][1][j] = mixColors(rowSelectionColor[j],
                    rowColor[j], i/255f);
            colorType[i][1][j].a=0.2f;
            }
        }
        arrayIdealPos = new float[rowsCount];
        for (int i = 0; i < rowsCount; i++) {
            arrayColors[i] = 0;
            variables[i][0] = "";
            variables[i][1] = "";
            needToDrawVariable[i][0] = false;
            needToDrawVariable[i][1] = false;
            array[i] = y2;
            arrayPos[i] = x2;
            arrayIdealPos[i] = x2;
            x2 += w2;
            y2 += h2;
        }
        rowWidth = Math.max(1, w2 - 2);
        canUpdateAndDraw = true;
    }

    public int getItemsCount() {
        return rowsCount;
    }

    public float getItem(int i) {
        compares++;
        action.run();
        return array[i];
    }

    public boolean isReady() {
        //it returns true if nothing is moving and is not paused
        return queueToMove.isEmpty() && !isPaused;
    }

    private void fastSwap(int i, int j) {
        //swap only addresses. x position and height don't change!
        float p1 = array[i];
        array[i] = array[j];
        array[j] = p1;
        float p2 = arrayPos[i];
        arrayPos[i] = arrayPos[j];
        arrayPos[j] = p2;
        float color = arrayColors[i];
        arrayColors[i] = arrayColors[j];
        arrayColors[j] = color;
        moves ++;// 2;
        action.run();
    }

    private void addToQueueToMove(int i) {
        if (!queueToMove.contains(i))
            queueToMove.add(i);
    }

    public void swap(int i, int j) {
        //swap 2 rows with animation.
        fastSwap(i, j);
        addToQueueToMove(j);
        addToQueueToMove(i);
    }

    public void shuffle() {
        for (int i = 0; i < rowsCount; i++) {
            int p1 = rnd.nextInt(rowsCount);
            swap(p1, i);
        }
    }

    public void upSort() {
        float h2 = (h * 4f / 5) / rowsCount;
        float y2 = h / 6f;
        for (int i = 0; i < rowsCount; i++) {
            array[i] = y2;
            y2 += h2;
        }
    }

    public void downSort() {
        float h2 = (h * 4f / 5) / rowsCount;
        float y2 = h / 6f;
        for (int i = 0; i < rowsCount; i++) {
            array[rowsCount - i - 1] = y2;
            y2 += h2;
        }
    }

    public void drawTitle() {
        drawVariables();
    }

    public void drawRows() {
        for (int i = 0; i < rowsCount; i++) {
            colorType[(int) arrayColors[i]][0][currentColorTheme].bind();
            glVertex2f(x + arrayPos[i], y2 - array[i] - 10);
            glVertex2f(x + arrayPos[i], y2 - 30);
            glVertex2f(x + arrayPos[i] + rowWidth, y2 - 30);
            glVertex2f(x + arrayPos[i] + rowWidth, y2 - array[i] - 10);
            if (drawShadow){
            float xx2 = x + arrayPos[i] + array[i] * (arrayPos[i] / arrayIdealPos[rowsCount - 1] - 0.5f) * 0.4f;
            float yy2 = y2 + array[i] * 0.25f - 30;
            Color.transparent.bind();
            glVertex2f(xx2, yy2);
            colorType[(int) arrayColors[i]][1][currentColorTheme].bind();
            glVertex2f(x + arrayPos[i], y2 - 30);
            glVertex2f(x + arrayPos[i] + rowWidth, y2 - 30);
            Color.transparent.bind();
            glVertex2f(rowWidth + xx2, yy2);  }
        }
    }

    public void drawVariableMarks() {
        textColor[currentColorTheme].bind();
        for (int i = 0; i < rowsCount; i++) {
            if (needToDrawVariable[i][0] || needToDrawVariable[i][1]) {
                glVertex2f(x + arrayPos[i], y2 - 25);
                glVertex2f(x + arrayPos[i] + rowWidth, y2 - 25);
                glVertex2f(x + arrayPos[i] + rowWidth, y2 - 20);
                glVertex2f(x + arrayPos[i], y2 - 20);
            }
        }
    }

    public void drawVariables() {
        textColor[currentColorTheme].bind();
        for (int i = 0; i < rowsCount; i++) {
            if (needToDrawVariable[i][0]) {
                SortingsDemo.Font.drawString(x + arrayPos[i], y2 - 20, variables[i][0], Color.black);
            }
            if (needToDrawVariable[i][1]) {
                SortingsDemo.Font.drawString(x + arrayIdealPos[i], y2, variables[i][1], Color.black);
            }
        }
    }

    public void drawBackground() {
        if (canUpdateAndDraw) {
            drawRows();
            drawVariableMarks();
        }
    }

    public void unSetAllVariables() {
        for (int i = 0; i < rowsCount; i++) {
            needToDrawVariable[i][0] = false;
            needToDrawVariable[i][1] = false;
        }
    }

    public void unSetVariableName(int i, boolean isStatic) {
        if (isStatic)
            needToDrawVariable[i][1] = false;
        else
            needToDrawVariable[i][0] = false;
    }

    public void setVariableName(int i, boolean isStatic, String s) {
        if (isStatic) {
            variables[i][1] = s;
            needToDrawVariable[i][1] = true;
        } else {
            variables[i][0] = s;
            needToDrawVariable[i][0] = true;
        }
    }

    private int getPosition(float x) {
        //returns the address (i) of coordinate.
        int min = 0;
        for (int i = 0; i < rowsCount; i++) {
            if (Math.abs(x - arrayIdealPos[i]) < Math.abs(x - arrayIdealPos[min]))
                min = i;
        }
        return min;
    }

    public void update(int x, int y, boolean isDown) {
        try {
        if (oldIsButtonDown) {
            if (selectedItem != -1) {
                if (isDown && contains(x, y)) {
                    //Moving row by mouse
                    arrayPos[selectedItem] = x - this.x - rowWidth / 2;
                    int selectedPlace = getPosition(arrayPos[selectedItem]);
                    if (selectedPlace != selectedItem) {
                        fastSwap(selectedPlace, selectedItem);
                        addToQueueToMove(selectedItem);
                        selectedItem = selectedPlace;
                    }
                } else {
                    //Drop the row
                    addToQueueToMove(selectedItem);
                }
            }
        } else {
            if (isDown && contains(x, y)) {
                //make selection.
                int selectedItem2 = -1;
                for (int i = 0; i < rowsCount; i++)
                    if (x >= arrayPos[i] + this.x && x <= arrayPos[i] + rowWidth + this.x) {
                        selectedItem2 = i;
                    }
                selectedItem = selectedItem2;
            } else {
                selectedItem = -1;
            }
        }

        if (canUpdateAndDraw && !isPaused) {
            //move rows
            for (int j = 0; j < queueToMove.size(); j++) {
                int i = queueToMove.get(j);
                if (arrayPos[i] > arrayIdealPos[i])
                    arrayPos[i] = Math.max(arrayPos[i] - speed, arrayIdealPos[i]);
                else if (arrayPos[i] < arrayIdealPos[i])
                    arrayPos[i] = Math.min(arrayPos[i] + speed, arrayIdealPos[i]);
                else {
                    queueToMove.remove(j);
                    if (j > 0) j--;
                }
            }
            //change colors
            for (int i = 0; i < rowsCount; i++) {
                if (Math.abs(arrayPos[i] - arrayIdealPos[i]) >= 0.000001f) {
                    arrayColors[i] = Math.min(255, arrayColors[i]+speed);
                } else {
                    if (arrayColors[i] > 0.25f) arrayColors[i] -= 0.25f;
                }
            }
        }
        oldIsButtonDown = isDown;
        }
        catch (Exception e) {System.err.println("[TABLE] "+Language.ERR_TableHasException+" : "+e);}
    }
}
