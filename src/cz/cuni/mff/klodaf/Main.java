package cz.cuni.mff.klodaf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Main {
    public static String[] OPERANDS = new String[]{"+", "-", "*", "/"};
    public static boolean endOfInput = false;
    public static void main(String[] args){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        MyStack stack;
        String input, element;
        String[] inputArray;
        int num;
        outer : while (true) {
            try {
                int x = 0;
                input = reader.readLine();
                if (Objects.equals(input, null)) break;
                if (input.isBlank()) continue;
                inputArray = input.split("\\s+");
                while(inputArray[x].isBlank()) x++;
                stack = new MyStack(Integer.parseInt(inputArray[x]));
                for (int i = x+1; i < inputArray.length; i++) {
                    element = inputArray[i];
                    if (element.isBlank()) continue;
                    if (isOperand(element)) {
                        if (!stack.operation(element)) {
                            continue  outer;
                        }
                    } else {
                        num = Integer.parseInt(element);
                        stack.add(num);
                    }
                }
                stack.end();
            } catch (IOException ex) {
                return;
            } catch (NumberFormatException ex) {
                System.out.println("Malformed expression");
            }
        }
    }
    private static  boolean isOperand(String operand){
        for (int i = 0; i < OPERANDS.length; i++){
            if (operand.equals(OPERANDS[i])) return true;
        }
        return false;
    }
}
