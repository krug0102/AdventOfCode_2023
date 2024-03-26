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
        System.out.println("The sum of the extrapolated values from the history report is " + sumExtrapolatedValues(s.nextLine()));
    }

    public static int sumExtrapolatedValues(String fileName) {
        int sum = 0;

        try {
            File inputFile = new File(fileName);
            Scanner reader = new Scanner(inputFile);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                int[] historyValues = Pattern.compile("(\\d+)")
                        .matcher(line)
                        .results()
                        .map(MatchResult::group)
                        .mapToInt(Integer::parseInt)
                        .toArray();

                sum = sum + nextValue(historyValues);
            }


        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        return sum;
    }

    public static int nextValue(int[] values) {
        System.out.println(values.length);
        ArrayList<int[]> sequences = new ArrayList<>();
        sequences.add(values);
        while (!arrayOfZeros(values)) {
            int[] difference = new int[values.length-1];
            for (int i = 0; i < difference.length - 1; i++) {
                difference[i] = values[i+1] - values[i];
            }
            System.out.println(difference.length);

            sequences.add(difference);
            values = Arrays.copyOf(difference, difference.length);
        }


        for (int i = sequences.size() - 2; i > 0; i--) {
            int[] x = sequences.get(i);
            int[] y = sequences.get(i+1);

            x[x.length-1] = x[x.length-2] + y[y.length-1];
        }

        int[] x = sequences.get(0);

        return x[x.length-1];
    }

    public static boolean arrayOfZeros(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                return false;
            }
        }
        return true;
    }

}
