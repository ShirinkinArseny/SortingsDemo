package SortingsDemo.Sortings;

public interface Sortable {

    public int getItemsCount();
    public float getItem(int i);
    public boolean isReady();
    public void swap(int i, int j);
    public void unSetAllVariables();
    public void setVariableName(int i, boolean isStatic, String s);
    public void unSetVariableName(int i, boolean isStatic);
    public float getSpeed();

}
