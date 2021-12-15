package cz.cuni.mff.klodaf;

import java.io.BufferedReader;
import java.io.IOException;

public class GameField {
    Boolean[][] first, second;
    int numOfSteps, tableSize;
    BufferedReader reader;
    public GameField(int numOfSteps, int tableSize, BufferedReader reader){
        this.numOfSteps = numOfSteps;
        first = new Boolean[tableSize][tableSize];
        this.reader = reader;
        this.tableSize = tableSize;
    }
    public void readTheInput() throws IOException {
        String input;
        for (int x = 0; x < tableSize; x++) {
            input = reader.readLine();
            for (int y = 0; y < tableSize; y++){
                if (input.charAt(y) == 'X'){
                    first[x][y] = true;
                }
            }
        }
    }
    public void RunTheGame() throws IOException{
        readTheInput();

    }
}
