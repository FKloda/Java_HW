package cz.cuni.mff.klodaf;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String param;
        String[] paramArray;
        int tableSize, numOfSteps;
        GameField game;
        try{
            param=reader.readLine();
            paramArray = param.split(" ");
            tableSize = Integer.parseInt(paramArray[0]);
            numOfSteps = Integer.parseInt(paramArray[1]);
        }
        catch (IOException ex){
            return;
        }
        catch (NumberFormatException ex){
            System.out.println("First row must contain two Integers");
            return;
        }
        game = new GameField(numOfSteps, tableSize, reader);
        try{
            game.RunTheGame();
        }
        catch (IOException ex){
            System.out.println("Error in input");
            return;
        }
    }
}
