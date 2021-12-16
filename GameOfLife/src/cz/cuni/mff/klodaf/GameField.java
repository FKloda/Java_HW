package cz.cuni.mff.klodaf;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.RecursiveAction;

public class GameField implements Runnable {
    boolean[][] inTable, outTable;
    int tableSize, indexX, indexY;
    public GameField( int indexX, int indexY, boolean[][] inTable, boolean[][]outTable){
        this.inTable = inTable;
        this.outTable = outTable;
        this.tableSize = tableSize;
        this.indexX = indexX;
        this.indexY = indexY;
    }
    @Override
    public void run() {
        try {
            int numOfAlive;
            int[] directions = new int[]{-1,-1,-1,0,-1,1,0,-1,0,1,1,-1,1,0,1,1};
            if (indexX == 0){
                directions[0] = directions[2] = directions[4] = tableSize-1;
            }
            if (indexY == 0){
                directions[1] = directions[7] = directions[11] = tableSize-1;
            }
            if (indexX == tableSize-1){
                directions[0] = directions[2] = directions[4] = -tableSize-1;
            }
            if (indexY == tableSize-1){
                directions[1] = directions[7] = directions[11] = -tableSize-1;
            }

            Main.barrier.await();
        }
        catch (InterruptedException e){

        }
        catch (BrokenBarrierException e){

        }
    }
}
