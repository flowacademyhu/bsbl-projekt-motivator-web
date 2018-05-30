package motivator.linter;

import java.util.Arrays;

public class Linter {
    private int depthCount;

    public Linter() {
        this.depthCount = 0;
    }

    public String[] rowSplitter(String input) {
        return input.split("\n");
    }

    /*
    public boolean rowCounter(String[] input) {
        int rowCount = 0;
        for (int i = 0; i < input.length; i++) {
            rowCount++;
            if (input[i].contains("{")) {
                return rowCounter(Arrays.copyOfRange(input, i, input.length));
            }
            if (input[i].contains("}")) {
                return rowCount <= 15;
            }
        }
        return true;
    }
    */

    public boolean fileLength(String[] input) {
        return input.length <= 100;
    }

    public char[] charSplitter(String input) {
        return input.toCharArray();
    }

    public boolean depthChecker(char[] input) {
        for (char inputChar: input) {
            if (inputChar == '{') {
                depthCount++;
            }
            if (depthCount > 4) {
                return false;
            }
            if (inputChar == '}') {
                depthCount--;
            }
        }
        return true;
    }
}
