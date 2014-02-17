package SortingsDemo.Sortings;

import static SortingsDemo.Window.getCurrentSortingNumber;
import static SortingsDemo.Window.setCurrentSorting;

public abstract class Sort {

    public Sort() {
    }

    public void sort(Sortable array) {
    }

    public boolean isTimeToBreak() {
        return getCurrentSortingNumber() == -1;
    }

    public void doneSorting(Sortable array) {
        array.unSetAllVariables();
        setCurrentSorting(-1);
    }

    public void tryToSleep(Sortable array) {
        try {
            do
                Thread.sleep((int)(350/array.getSpeed()));
            while (!array.isReady());
        } catch (InterruptedException e) {
            System.err.println("[SORT] Sorting thread has InterruptedException");
        }
    }

    public String getCompares(int count) {
        return "Min: -1; Max: -1";
    }

    public String getMoves(int count) {
        return "Min: -1; Max: -1";
    }
}
