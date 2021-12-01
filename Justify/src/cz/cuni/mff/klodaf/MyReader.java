package cz.cuni.mff.klodaf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyReader {

    char nextCharacter;
    int charNum;
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public int readNumber() throws MissingLenghtOfLineExcexption, IOException{
        String num;

        int lineWidth;
        try {
            num = input.readLine();
            lineWidth = Integer.parseInt(num);
        }
        catch(NumberFormatException ex) {
            throw new MissingLenghtOfLineExcexption();
        }
        //Set the reader pointer on the first non-white character
        charNum = input.read();
        if (charNum == -1) {
            return -1;
        }
        nextCharacter = (char) charNum;
        while (Character.isWhitespace(nextCharacter)) {
            charNum = input.read();
            if (charNum == -1) {
                return -1;
            }
            nextCharacter = (char) charNum;
        }
        return lineWidth;
    }
    public String readWord() throws IOException{
        boolean endOfLineReached = false;
        StringBuilder word = new StringBuilder();
        if (charNum == -1){
            return null;
        }
        while (!Character.isWhitespace(nextCharacter)) {
            word.append(nextCharacter);
            charNum = input.read();
            if (charNum == -1) {
                return word.toString();
            }
            else nextCharacter = (char) charNum;
        }
        while (Character.isWhitespace(nextCharacter)) {
            if (nextCharacter == '\n') {
                if (endOfLineReached) TextAligner.paragraph = true;
                else endOfLineReached = true;
            }
            charNum = input.read();
            if (charNum == -1) {
                break;
            }
            else nextCharacter = (char) charNum;
        }
        return word.toString();
    }
}

