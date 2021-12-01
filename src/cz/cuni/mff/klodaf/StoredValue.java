package cz.cuni.mff.klodaf;

public class StoredValue {
    int value;
    StoredValue previous;
    public StoredValue(int value, StoredValue previous){
        this.value = value;
        this.previous = previous;
    }
}
