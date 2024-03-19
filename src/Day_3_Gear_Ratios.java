import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Day_3_Gear_Ratios {

    public static void main(String[] args) {

    }


    public static int sumOfEngineParts(String fileName) {
        int sum = 0;
        ArrayList<char[]> list = new ArrayList<>();
        try {
            File inputFile = new File(fileName);
            Scanner reader = new Scanner(inputFile);
            while (reader.hasNextLine()) {
                list.add(reader.nextLine().toCharArray());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        char[][] engineSchematic = (char[][]) list.toArray();

        for (int i = 0; i < engineSchematic.length; i++) {
            for (int j = 0; j < engineSchematic[i].length; i++) {
                if (Pattern.matches("[^a-zA-Z0-9.]", String.valueOf(engineSchematic[i][j]))) {
                    sum = sum + sumOfAdjacentNumbers(engineSchematic, j, i);
                }
            }
        }

        return sum;
    }

    public static int sumOfAdjacentNumbers(char[][] schematic, int x, int y) {
        ArrayList<Character> adjacentSpots = new ArrayList<>();

        return 0;
    }

}
