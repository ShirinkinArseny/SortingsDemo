package SortingsDemo.Sortings;


import SortingsDemo.Language;

public class QuickSort extends Sort {

    public QuickSort() {
    }

    private void setNames(Sortable array, int low, int high, int i, int j) {
        array.unSetAllVariables();
        array.setVariableName(low, true, "low");
        array.setVariableName(high, true, "high");
        array.setVariableName((i + j) / 2, true, "middle");
            array.setVariableName(i, false,  "i");
            array.setVariableName(j, false,  "j");
    }

    private void sort(Sortable array, int low, int high, int recur) {
        if (!isTimeToBreak()) {
        int i;
        int j;
        float m;
        i = low;
        j = high;
        m = array.getItem((i + j) / 2);
        do {
            if (isTimeToBreak()) break;
            while (array.getItem(i) < m) {
                i++;
                setNames(array, low, high, i, j);
                tryToSleep(array);
            }
            while (array.getItem(j) > m) {
                j--;
                setNames(array, low, high, i, j);
                tryToSleep(array);
            }
            if (i <= j) {
                if (i!=j) {
                    setNames(array, low, high, i, j);
                    array.swap(i, j);
                    tryToSleep(array);
                }
                i++;
                j--;
            }
        } while (i <= j);
        if (low < j && !isTimeToBreak()) {
            sort(array, low, j, recur + 1);
        }
        if (i < high && !isTimeToBreak()) {
            sort(array, i, high, recur + 1);
        }
        }
    }

    public void sort(Sortable array) {
        if (!isTimeToBreak()) {
            sort(array, 0, array.getItemsCount() - 1, 0);
            doneSorting(array);
        }
    }

    private int getC(int n) {
        if (n==1) return 0;
        return (getC(n/2)+getC(n-n/2))+n;
    }

    public String getCompares(int count) {
        return Language.TITLE_Maximum+getC(count);
    }

    public String getMoves(int count) {
        return "";
    }
}
