package cz.cuni.mff.klodaf;
import java.io.IOException;
import java.util.Objects;

public class TextAligner {
    public static boolean paragraph = false, endOfInput = false ;
    StringBuilder line;
    MyReader inputReader;
    String[] words;
    int lineWidth;
    public TextAligner() throws  MissingLenghtOfLineExcexption, IOException{
        line = new StringBuilder();
        inputReader = new MyReader();
        lineWidth = inputReader.readNumber();
        if (lineWidth == -1){   //Nothing to read
            endOfInput = true;
        }
        words = new String[lineWidth/2+1];
    }
    public void processLine() throws IOException{
        int index = 0, length = lineWidth;
        if (!Objects.equals(words[0], null)) {
            length -= words[index].length() + 1; //+1 stands for one whitespace added
        }
        else {
            words[0] = inputReader.readWord();
            if (Objects.equals(words[0], null)){
                endOfInput = true;
                return;
            }
            length -= words[0].length() + 1;
        }
        if (paragraph | endOfInput) {
            writeEndOfParagraph(0);
            System.out.println(line.toString());
            return;
        }
        if (length <= 0) {
            writeLineWithSpaces(0);
            System.out.println(line.toString());
            return;
        }
        while (true) {
            if (paragraph | endOfInput) {
                writeEndOfParagraph(index);
                System.out.println(line.toString());
                break;
            }
            String nextWord = inputReader.readWord();
            if (Objects.equals(nextWord, null)){
                endOfInput = true;
                continue;
            }
            if (length < nextWord.length()) {	// next word does not fit
                writeLineWithSpaces(index);
                System.out.println(line.toString());
                words[0] = nextWord;
                break;
            }
            else {	// next word fits in the line
                length -= nextWord.length() +1;
                index ++;
                words[index] = nextWord;
            }

        }

    }
    public void writeLineWithSpaces(int index) {
        //index marks the last index of the words array, where a word is stored
        //it is also equal to number of words in array -1
        int length = lineWidth, spacesEverywhere, spacesExtra;
        if (index == 0) {
            line.delete(0, line.length());
            line.append(words[0]);
            words[0] = null;
            return;
        }
        for (int x = 0; x <= index; x++) {
            length -= words[x].length();
        }
        spacesEverywhere = length / index;
        spacesExtra = length % index;

        this.line.delete(0, this.line.length());
        for (int x = 0; x < spacesExtra; x++) {
            line.append(words[x]);
            line.append(" ".repeat(spacesEverywhere + 1));
            words[x] = null;
        }
        for (int x = spacesExtra; x < index; x++) {
            line.append(words[x]);
            line.append(" ".repeat(spacesEverywhere));
            words[x] = null;
        }
        line.append(words[index]);
        words[index] = null;
        words[0] = null;
    }

    public void writeEndOfParagraph(int index) {
        line.delete(0, this.line.length());
        for (int x = 0; x < index; x++) {
            line.append(words[x]);
            line.append(" ");
            words[x] = null;
        }
        line.append(words[index]);
        if (!endOfInput) line.append(System.lineSeparator());
        paragraph = false;
        words[index] = null;
        words[0] = null;
    }
    public void pushTheRest() {
        if (Objects.equals(words[0], null)) return;
        int index = 0;
        while (!Objects.equals(words[index+1], null)){
            index ++;
        }
        for (int i = 0; i < index; i++) {
            System.out.print(words[i]);
            System.out.print(" ");
        }
        System.out.print(words[index]);
        System.out.print(System.lineSeparator());
    }
}
