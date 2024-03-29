import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Day_9_Mirage_Maintenance {


    //TODO: Seems recursive, but also seems like it could be done in a well-designed for loop
    public static void main(String[] args) {
        prompt();
    }

    public static void prompt() {
        System.out.println("Please provide the history report.");
        Scanner s = new Scanner(System.in);
        String fileName = s.nextLine();

        System.out.println("The sum of the FORWARD extrapolated values from the history report is " + part1(fileName));

        System.out.println("The sum of the BACKWARD extrapolated values from the history report is " + part2(fileName));
    }

    public static int part1(String fileName) {
        int sum = 0;

        try {
            File inputFile = new File(fileName);
            Scanner reader = new Scanner(inputFile);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                int[] historyValues = Pattern.compile("(-?\\d+)")
                        .matcher(line)
                        .results()
                        .map(MatchResult::group)
                        .mapToInt(Integer::parseInt)
                        .toArray();

                sum = sum + nextValue(historyValues, historyValues[historyValues.length - 1]);
            }


        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        return sum;
    }

    public static int part2(String fileName) {
        int sum = 0;

        try {
            File inputFile = new File(fileName);
            Scanner reader = new Scanner(inputFile);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                int[] historyValues = Pattern.compile("(-?\\d+)")
                        .matcher(line)
                        .results()
                        .map(MatchResult::group)
                        .mapToInt(Integer::parseInt)
                        .toArray();

                sum = sum + previousValue(historyValues, historyValues[0]);
            }


        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        return sum;
    }

    public static int nextValue(int[] sequence, int result) {
        if (arrayOfZeros(sequence)) {
            return 0;
        } else {
            int[] differences = new int[sequence.length - 1];
            for (int i = 0; i < differences.length; i++) {
                differences[i] = sequence[i+1] - sequence[i];
            }
            return result + nextValue(differences, differences[differences.length - 1]);
        }
    }


    public static int previousValue(int[] sequence, int result) {
        if (arrayOfZeros(sequence)) {
            return 0;
        } else {
            int[] differences = new int[sequence.length - 1];
            for (int i = 0; i < differences.length; i++) {
                differences[i] = sequence[i+1] - sequence[i];
            }
            return result - previousValue(differences, differences[0]);
        }
    }

    public static boolean arrayOfZeros(int[] arr) {
        for (int j : arr) {
            if (j != 0) {
                return false;
            }
        }
        return true;
    }

}
