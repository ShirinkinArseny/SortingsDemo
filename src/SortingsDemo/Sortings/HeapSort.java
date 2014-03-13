package SortingsDemo.Sortings;

import SortingsDemo.Language;

public class HeapSort extends Sort {

    public HeapSort() {
    }

    private int addToTree(Sortable arr, int i, int N)
    {
        int iMax;
        if ((2 * i + 2) < N)
        {
            if (arr.getItem(2 * i + 1) < arr.getItem(2 * i + 2)) iMax = 2 * i + 2;
            else iMax = 2 * i + 1;
        }
        else iMax = 2 * i + 1;
        if (iMax >= N) return i;
        if (arr.getItem(i) < arr.getItem(iMax))
        {
            arr.setVariableName(iMax, false, "i");
            arr.setVariableName(i, false, "iMax");
            arr.swap(i, iMax);
            tryToSleep(arr);
            if (iMax < N / 2) i = iMax;
        }
        return i;
    }

    private void treeSort(Sortable arr, int length)
    {
        for (int i = length / 2 - 1; i >= 0; i--)
        {
            if (isTimeToBreak()) return;
            arr.unSetAllVariables();
            arr.setVariableName(i, false, "i");
            tryToSleep(arr);

            int prev_i = i;
            i = addToTree(arr, i, length);
            if (prev_i != i) i++;
        }

        for (int k = length - 1; k > 0; k--)
        {
            if (isTimeToBreak()) return;
            arr.unSetAllVariables();
            arr.setVariableName(0, false, "k");
            arr.swap(0, k);
            tryToSleep(arr);
            int i = 0;
            int prev_i = -1;
            while (i != prev_i)
            {
                arr.unSetAllVariables();
                arr.setVariableName(0, true, "prev_i="+prev_i);
                prev_i = i;
                i = addToTree(arr, i, k);
            }
        }
    }

    public void sort(Sortable array) {
        if (!isTimeToBreak()) {
            treeSort(array, array.getItemsCount());
            doneSorting(array);
        }
    }


    public String getCompares(int count) {
        return "";
    }

    public String getMoves(int count) {
        return Language.TITLE_Maximum+String.valueOf(count*log2(count));
    }
}
