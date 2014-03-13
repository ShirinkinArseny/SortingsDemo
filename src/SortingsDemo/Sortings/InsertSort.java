package SortingsDemo.Sortings;

import SortingsDemo.Language;

public class InsertSort extends Sort {

    public InsertSort() {
    }

    public void sort(Sortable array) {
        if (!isTimeToBreak()) {
            for (int i = 1; i < array.getItemsCount(); i++)
            {
                if (isTimeToBreak()) break;
                int j = i;
                array.unSetAllVariables();
                array.setVariableName(i, false, "i");
                tryToSleep(array);
                while (j > 0 && array.getItem(j) < array.getItem(j - 1))
                {
                    if (isTimeToBreak()) break;
                    if (i!=j) {
                    array.setVariableName(j-1, false, "j");
                    if (j+1<i)
                        array.unSetVariableName(j, false);
                    }
                    array.swap(j, j-1);
                    j--;
                    tryToSleep(array);
                }
            }
            doneSorting(array);
        }
    }

    public String getCompares(int count) {
        return "";
    }

    public String getMoves(int count) {
        return Language.TITLE_Maximum+String.valueOf(count*(count+3)/2);
    }
}
