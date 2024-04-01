import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Day_1_Trebuchet {

    public static void main(String[] args) {
        prompt();
    }

    public static void prompt() {
        System.out.println("Please provide the calibration document.");
        Scanner s = new Scanner(System.in);
        String fileName = s.nextLine();
        System.out.println("The sum of the calibration values is: " + part1(fileName));
    }

    /**
     * reads a file line by line
     * @param input The name of a text file
     */
    public static int part1(String input) {
        int sum = 0;
        try {
            File inputFile = new File(input);
            Scanner reader = new Scanner(inputFile);
            while (reader.hasNextLine()) {
                char[] line = reader.nextLine().toCharArray();
                sum = sum + getNumbers(line);
            }
        } catch(FileNotFoundException e) {
            System.out.println(e);
        }

        return sum;
    }

    /**
     * takes a line of text as a character array and returns an integer that
     * represents the two-digit number formed by the first and last digit in
     * the line
     *
     * If there is only one number in the line, then both numbers are the same
     * @param line
     */
    public static int getNumbers(char[] line) {
        ArrayList<IndexValuePair> pairs = new ArrayList<>();
        for (int i = 0; i < line.length; i++) {
            if (Character.isDigit(line[i])) {
                pairs.add(new IndexValuePair(i, Character.getNumericValue(line[i])));
            }
        }

        return Integer.parseInt(pairs.get(0).value + "" + pairs.get(pairs.size()-1).value);
    }

    //TODO: Don't fully utilize the IndexValuePair class, can
    // refactor and remove the IndexValuePair class
    public static class IndexValuePair {
        int index;
        int value;

        public IndexValuePair(int i, int v) {
            this.index = i;
            this.value = v;
        }
    }
}
