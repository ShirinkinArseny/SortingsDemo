package SortingsDemo;

import SortingsDemo.Controls.*;
import SortingsDemo.Sortings.Sortable;

import java.util.ArrayList;

import static SortingsDemo.Window.getCurrentSorting;
import static SortingsDemo.Window.getCurrentSortingNumber;
import static SortingsDemo.Window.setCurrentSorting;


public class MasterControl extends Control {

    private static ArrayList<Control> controls;
    private static final Table table = new Table(30, 10, 940, 300);

    public MasterControl() {

        super(0, 0, 0, 0);

        controls = new ArrayList<Control>();
        controls.add(table);


        int emptySpace = 10;
        int titleStartX = 10;
        int titleWidth = 120;
        int titleHeight = 30;
        int titleStartY = 420;
        int sortNamesWidth = 770;
        int smallButtonSize = 50;
        int statisticLineWidth = 160;

        int titleSpaceY = titleHeight + emptySpace;
        int titleSpaceX = titleWidth + titleStartX + emptySpace;
        int bigButtonsSize = titleHeight + titleSpaceY;
        int bigButtonsStartY = titleStartY + titleSpaceY;
        int sizeAndSpeedWidth = sortNamesWidth - 3 * (bigButtonsSize + emptySpace);
        int bigButtonsStartX = titleSpaceX + emptySpace + sizeAndSpeedWidth;


        TextOutBox sorts = new TextOutBox(titleStartX, titleStartY, titleWidth, titleHeight, Language.TITLE_Sorts);
        add(sorts);

        SelectBox sortBox = new SelectBox(titleSpaceX, titleStartY, sortNamesWidth, 30, 6);

        for (int i = 0; i < 6; i++) {
            final int finalI = i;
            sortBox.setEvent(i, new Runnable() {
                @Override
                public void run() {
                    table.resetComparesAndMoves();
                    setCurrentSorting(finalI);
                }
            });
        }

        sortBox.setTitle(0, Language.TITLE_Choice);
        sortBox.setTitle(1, Language.TITLE_Bubble);
        sortBox.setTitle(2, Language.TITLE_Quick);
        sortBox.setTitle(3, Language.TITLE_Insert);
        sortBox.setTitle(4, Language.TITLE_Shell);
        sortBox.setTitle(5, Language.TITLE_Heap);
        controls.add(sortBox);

        TextOutBox size = new TextOutBox(titleStartX, titleStartY + titleSpaceY, titleWidth, titleHeight,
                Language.TITLE_Size);
        controls.add(size);

        SelectBox sizeBox = new SelectBox(titleSpaceX, titleStartY + titleSpaceY, sizeAndSpeedWidth, titleHeight, 4);


        final int[] sizes = new int[]{16, 32, 64, 128};
        for (int i = 0; i < 4; i++) {
            final int finalI = i;
            sizeBox.setEvent(i, new Runnable() {
                @Override
                public void run() {
                    table.setItemsCount(sizes[finalI]);
                }
            });
            sizeBox.setTitle(i, String.valueOf(sizes[i]));
        }
        controls.add(sizeBox);

        TextOutBox speed = new TextOutBox(titleStartX, titleStartY + 2 * titleSpaceY, titleWidth, titleHeight,
                Language.TITLE_Speed);
        controls.add(speed);

        final int[] speeds = new int[]{1, 5, 10, 20};
        SelectBox speedBox = new SelectBox(titleSpaceX, titleStartY + 2 * titleSpaceY, sizeAndSpeedWidth, titleHeight, 4);
        for (int i = 0; i < 4; i++) {
            final int finalI = i;
            speedBox.setEvent(i, new Runnable() {
                @Override
                public void run() {
                    table.setSpeed(speeds[finalI]);
                }
            });
            speedBox.setTitle(i, String.valueOf(speeds[finalI]));
        }
        controls.add(speedBox);

        UpSortButton upSort = new UpSortButton(bigButtonsStartX, bigButtonsStartY, bigButtonsSize, bigButtonsSize);
        upSort.setEvent(new Runnable() {
            @Override
            public void run() {
                table.upSort();
            }
        });
        controls.add(upSort);
        DownSortButton downSort = new DownSortButton(bigButtonsStartX + emptySpace + bigButtonsSize,
                bigButtonsStartY, bigButtonsSize, bigButtonsSize);
        downSort.setEvent(new Runnable() {
            @Override
            public void run() {
                table.downSort();
            }
        });
        controls.add(downSort);
        ShuffleButton shuffle = new ShuffleButton(bigButtonsStartX + 2 * (emptySpace + bigButtonsSize),
                bigButtonsStartY, bigButtonsSize, bigButtonsSize);
        shuffle.setEvent(new Runnable() {
            @Override
            public void run() {
                table.shuffle();
            }
        });
        controls.add(shuffle);


        PauseButton pause = new PauseButton(titleSpaceX, bigButtonsStartY + bigButtonsSize + emptySpace,
                smallButtonSize, smallButtonSize);
        pause.setEvent(new Runnable() {
            @Override
            public void run() {
                table.setPaused(!table.getIsPaused());
            }
        });
        controls.add(pause);
        StopButton stopButton = new StopButton(titleSpaceX + smallButtonSize + emptySpace,
                bigButtonsStartY + bigButtonsSize + emptySpace, smallButtonSize, smallButtonSize);
        stopButton.setEvent(new Runnable() {
            @Override
            public void run() {
                if (getCurrentSortingNumber() != -1) {
                    setCurrentSorting(-1);
                }
            }
        });
        controls.add(stopButton);

        final TextOutBox compares = new TextOutBox(titleSpaceX+emptySpace*2+2*smallButtonSize,
                bigButtonsStartY+bigButtonsSize+emptySpace, statisticLineWidth, titleHeight,
                Language.TITLE_Compares + " " + table.getCompares() / 2);
        controls.add(compares);
        final TextOutBox moves = new TextOutBox(titleSpaceX+emptySpace*2+2*smallButtonSize,
                bigButtonsStartY+bigButtonsSize+3*emptySpace, statisticLineWidth, titleHeight,
                Language.TITLE_Moves + " " + table.getMoves());
        controls.add(moves);

        table.setAction(new Runnable() {
            @Override
            public void run() {

                String compLims="";
                String moveLims="";
                if (getCurrentSortingNumber()!=-1) {
                    compLims=getCurrentSorting().getCompares(table.getItemsCount());
                    moveLims=getCurrentSorting().getMoves(table.getItemsCount());
                }

                compares.setText(Language.TITLE_Compares + " " + table.getCompares() / 2 +"; "+ compLims);
                moves.setText(Language.TITLE_Moves + " " + table.getMoves() +"; "+ moveLims);
            }
        });
    }

    public Sortable getSortable() {
        return table;
    }

    public void add(Control a) {
        controls.add(a);
    }

    public void update(int x, int y, boolean b) {
        for (Control i : controls)
            i.update(x, y, b);
    }


    public void drawBackground() {
        for (Control i : controls)
            i.drawBackground();
    }

    public void drawTitle() {
        for (Control i : controls)
            i.drawTitle();
    }

}
