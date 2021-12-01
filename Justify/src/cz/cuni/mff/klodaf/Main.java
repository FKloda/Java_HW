package cz.cuni.mff.klodaf;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        TextAligner aligner;
        try{
            aligner = new TextAligner();
        }
        catch (MissingLenghtOfLineExcexption ex){
            System.out.println("Error");
            return;
        }
        catch (IOException ex){
            return;
        }
        try{
            while (!TextAligner.endOfInput) {
                aligner.processLine();
            }
        }
        catch (IOException ex){
            return;
        }
        aligner.pushTheRest();
    }
}
