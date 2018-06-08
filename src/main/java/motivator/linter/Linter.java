package motivator.linter;

public class Linter {
    private int depthCount;

    public Linter() {
        this.depthCount = 0;
    }

    public String[] rowSplitter(String input) {
        return input.split("\n");
    }

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
