package SortingsDemo.Sortings;

import SortingsDemo.Language;

public class ChoiceSort extends Sort {

    public ChoiceSort() {
    }

    public void sort(Sortable array) {
        if (!isTimeToBreak())
            for (int j = 0; j < array.getItemsCount() - 1; j++) {
                if (isTimeToBreak()) break;
                array.unSetAllVariables();
                array.setVariableName(j, false, "i");
                int min = j;
                if (j > 0) array.unSetVariableName(j - 1, false);
                for (int i = j+1; i < array.getItemsCount(); i++) {
                    if (isTimeToBreak()) break;

                        array.setVariableName(i, false, "j");
                        if (min != i - 1) array.unSetVariableName(i - 1, false);

                    if (array.getItem(i) < array.getItem(min)) {
                        if (min != j) array.unSetVariableName(min, false);
                        else array.setVariableName(min, false, "i");
                        min = i;
                        array.setVariableName(min, false, "min");
                    }
                    tryToSleep(array);
                }
                if (min != j) {
                    array.setVariableName(min,  false,"i");
                    array.setVariableName(j,  false,"min");
                    array.swap(min, j);
                    tryToSleep(array);
                }
            }
        doneSorting(array);
    }

    public String getCompares(int count) {
        return Language.TITLE_Maximum+String.valueOf((count*count-count)/2);
    }

    public String getMoves(int count) {
        return Language.TITLE_Minimum+"0, "+Language.TITLE_Maximum+String.valueOf(count);
    }
}