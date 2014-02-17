package SortingsDemo.Sortings;


import SortingsDemo.Language;

public class HeapSort extends Sort {

    public HeapSort() {
    }

    private int add2pyramid(Sortable arr, int i, int N)
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

    private void Pyramid_Sort(Sortable arr, int len)
    {
        //step 1: building the pyramid
        for (int i = len / 2 - 1; i >= 0; --i)
        {
            if (isTimeToBreak()) break;
            arr.unSetAllVariables();
            arr.setVariableName(i, false, "i");
            tryToSleep(arr);
            long prev_i = i;
            i = add2pyramid(arr, i, len);
            if (prev_i != i) ++i;
        }

        //step 2: sorting
        for (int k = len - 1; k > 0; --k)
        {
            if (isTimeToBreak()) break;
            arr.unSetAllVariables();
            arr.setVariableName(0, false, "k");
            arr.swap(0, k);
            tryToSleep(arr);
            int i = 0, prev_i = -1;
            while (i != prev_i)
            {
                arr.unSetAllVariables();
                arr.setVariableName(0, true, "prev_i="+prev_i);
                prev_i = i;
                i = add2pyramid(arr, i, k);
            }
        }
    }

    public void sort(Sortable array) {
        if (!isTimeToBreak()) {
            Pyramid_Sort(array, array.getItemsCount());
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
