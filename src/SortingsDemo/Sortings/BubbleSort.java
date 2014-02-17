package SortingsDemo.Sortings;

import SortingsDemo.Language;

public class BubbleSort extends Sort {

    public BubbleSort() {
    }

    public void sort(Sortable array) {
        if (!isTimeToBreak()) {
            for (int j = array.getItemsCount() - 1; j > 0; j--) {
                if (isTimeToBreak()) break;
                array.unSetAllVariables();
                array.setVariableName(j,  false,"i");
                for (int i = 0; i < j; i++) {
                    if (isTimeToBreak()) break;
                    array.setVariableName(i, false, "j");
                    if (i > 0)
                        array.unSetVariableName(i - 1, false);
                    if (array.getItem(i) > array.getItem(i + 1)) {
                        array.unSetVariableName(i, false);
                        array.setVariableName(i + 1, false, "j");
                        array.swap(i, i + 1);
                    }
                    tryToSleep(array);
                }
                tryToSleep(array);
            }
            doneSorting(array);
        }
    }

    public String getCompares(int count) {
        return Language.TITLE_Maximum+String.valueOf((count*count-count)/2);
    }

    public String getMoves(int count) {
        return "";
    }
}
