import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Day_12_Hot_Springs {
    

    public static void main(String[] args) {
        prompt();
    }

    public static void prompt() {
        System.out.println("Please provide the rows of hotsprings: ");
        Scanner s = new Scanner(System.in);
        String fileName = s.nextLine();
        System.out.println("Part 1: \nThe sum of different arrangements is: " + part1(fileName));
    }


    public static int part1(String fileName) {
        int totalArrangements = 0;

        try {
            File inputFile = new File(fileName);
            Scanner reader = new Scanner(inputFile);

            while (reader.hasNextLine()) {
                String s = reader.nextLine();
                char[] hotSprings = s.split(" ")[0].toCharArray();
                int[] sizes = Pattern.compile("([0-9]+)")
                    .matcher(s)
                    .results()
                    .map(MatchResult::group)
                    .mapToInt(Integer::parseInt)
                    .toArray();
                
                // Every group of contiguous damaged hotsprings (#) must be separated by an operational hotspring (.)
                
                // Look at a window of size n+2, where n is the size of the contiguous damaged hotsprings
                for (int i = 0; i < sizes.length; i++) {
                    
                
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }



        return totalArrangements;
    }
}
