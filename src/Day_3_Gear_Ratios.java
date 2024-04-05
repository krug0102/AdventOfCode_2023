import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Day_3_Gear_Ratios {


    // Answer from example should be 4361
    public static void main(String[] args) {
        prompt();
    }

    public static void prompt() {
        System.out.println("Please provide the engine schematic.");
        Scanner s = new Scanner(System.in);
        String fileName = s.nextLine();

        System.out.println("The sum of all the engine parts is " + part1(fileName));
    }

    public static int part1(String fileName) {
        int sum = 0;
        ArrayList<char[]> engineSchematic = new ArrayList<>();
        try {
            File input = new File(fileName);
            Scanner reader = new Scanner(input);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                engineSchematic.add(line.toCharArray());
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }


        boolean valid = false;
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < engineSchematic.size(); i++) { // traverse through all arrays
            char[] line = engineSchematic.get(i);
            for (int j = 0; j < line.length; j++) { // traverse through a singe array
                if (Character.isDigit(line[j])) {
                    number.append(line[j]);
                    if (hasAdjacentSymbol(i, j, engineSchematic)) {
                        valid = true;
                    }
                } else {
                    if (valid) {
                        sum += Integer.parseInt(String.valueOf(number));
                        number = new StringBuilder();
                        valid = false;
                    } else {
                        number = new StringBuilder();
                    }

                }
            }

            if (valid) {
                sum += Integer.parseInt(String.valueOf(number));
            }

            number = new StringBuilder();
            valid = false;
        }

        return sum;
    }

    public static boolean hasAdjacentSymbol(int i, int j, ArrayList<char[]> schematic) {
        // (j, i) = (x,y) of number

        //  . . .    (x-1, y-1)  (x, y-1)  (x+1, y-1)
        //  . x .    (x-1, y)    (x,y)     (x+1, y)
        //  . . .    (x-1, y+1)  (x, y+1)  (x+1, y+1)

        for (int y = -1; y < 2; y++) {
            for (int x = -1; x < 2; x++) {
                int xdiff = j + y;
                int ydiff = i + x;
                if (ydiff >= 0 && ydiff < schematic.size()) {
                    if (xdiff >= 0 && xdiff < schematic.get(ydiff).length) {
                        char[] line = schematic.get(ydiff);
                        if (Pattern.matches("[^a-zA-Z0-9.]", String.valueOf(line[xdiff]))) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}
