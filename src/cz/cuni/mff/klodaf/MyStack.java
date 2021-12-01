package cz.cuni.mff.klodaf;

import java.util.Objects;

public class MyStack {
    StoredValue current;
    public MyStack(int value){
        this.current = new StoredValue(value, null);
    }

    public void add(int value){
        current = new StoredValue(value, current);
    }

    public boolean operation(String operand){
        int numA = current.value;
        if (Objects.equals(current.previous, null)){
            malformedExpError();
            return false;
        }
        switch (operand) {
            case "+" -> {
                current = current.previous;
                current.value = current.value + numA;
                return true;
            }
            case "-" -> {
                current = current.previous;
                current.value = current.value - numA;
                return true;
            }
            case "*" -> {
                current = current.previous;
                current.value = current.value * numA;
                return true;
            }
            case "/" -> {
                current = current.previous;
                if (numA == 0) {
                    System.out.println("Zero division");
                    return false;
                }
                current.value = current.value / numA;
                return true;
            }
            default -> {
                malformedExpError();
                return false;
            }
        }
    }
    private void malformedExpError(){
        System.out.println("Malformed expression");
        current = null;
    }
    public void end(){
        if (Objects.equals(current, null) | !Objects.equals(current.previous, null)){
            malformedExpError();
        }
        else{
            System.out.println(current.value);
            current = null;
        }
    }
}
