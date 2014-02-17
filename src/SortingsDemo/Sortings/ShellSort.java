package SortingsDemo.Sortings;


import SortingsDemo.Language;

public class ShellSort extends Sort {

    public ShellSort() {
    }

    public void sort(Sortable array) {
        if (!isTimeToBreak()) {
            int i, j, k, m = 0, b = array.getItemsCount();
            float h;
            int[] d = {1, 4, 10, 23, 57, 145};
            while (d[m] < b) m++;
            while (--m >= 0) {
                if (isTimeToBreak()) break;
                k = d[m];
                array.unSetAllVariables();
                array.setVariableName(0, true, "k="+k);
                for (i = k; i < b; i++) {
                    if (isTimeToBreak()) break;
                    array.setVariableName(i, false, "i");
                    if (i>0)
                        array.unSetVariableName(i - 1, false);
                    tryToSleep(array);
                    j = i;
                    h = array.getItem(i);
                    while ((j >= k) && (array.getItem(j - k) > h)) {
                        array.setVariableName(j-k, false, "j");
                        array.setVariableName(j, false, "j-k");
                        array.swap(j, j-k);
                        tryToSleep(array);
                        if (i!=j)
                            array.unSetVariableName(j, false);
                        array.unSetVariableName(j-k, false);
                        j -= k;
                    }
                }
            }
            doneSorting(array);
        }
    }

    public String getCompares(int count) {
        return "";
    }

    public String getMoves(int count) {
        return "";
    }
}
