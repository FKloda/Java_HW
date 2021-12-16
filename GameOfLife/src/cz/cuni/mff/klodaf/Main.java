package cz.cuni.mff.klodaf;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static CyclicBarrier barrier;
    public static void main(String[] args){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String param;
        String[] paramArray;
        int tableSize, numOfSteps;
        boolean[][] first, second;
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try{
            param=reader.readLine();
            paramArray = param.split(" ");
            tableSize = Integer.parseInt(paramArray[0]);
            numOfSteps = Integer.parseInt(paramArray[1]);
            first = readTheInput(tableSize, reader);
            second = first.clone();
        }
        catch (IOException ex){
            System.out.println("Error in input reading");
            return;
        }
        catch (NumberFormatException ex){
            System.out.println("First row must contain two Integers");
            return;
        }
        barrier = new CyclicBarrier(tableSize*tableSize);
        for(int p = 0; p<numOfSteps; p++) {
            barrier.reset();
            for (int x = 0; x < tableSize; x++) {
                for (int y = 0; y < tableSize; y++) {
                    if (p % 2 == 0) {
                        executor.execute(new GameField(x, y, first, second));
                    }
                    else{
                        executor.execute(new GameField(x,y,second,first));
                    }
                }
            }
        }

    }
    static boolean[][] readTheInput( int tableSize, BufferedReader reader) throws IOException {
        String input;
        boolean[][] table = new boolean[tableSize][tableSize];
        for (int x = 0; x < tableSize; x++) {
            input = reader.readLine();
            for (int y = 0; y < tableSize; y++){
                if (input.charAt(y) == 'X'){
                    table[x][y] = true;
                }
            }
        }
        return table;
    }
}
