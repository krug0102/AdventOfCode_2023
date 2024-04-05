import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Day_5_Seeds {

    static Pattern numbers = Pattern.compile("(\\d+)");
    static Pattern mapName = Pattern.compile("([a-zA-Z]+-to-[a-zA-Z]+)");
    public static void main(String[] args) {
        prompt();
    }

    public static void prompt() {
        System.out.println("Please provide the Island Island Almanac.");
        Scanner s = new Scanner(System.in);
        String fileName = s.nextLine();

        System.out.println("The lowest location number that corresponds to an initial seed is " + part1(fileName));
    }


    public static int part1(String fileName) {
        int minLocation = Integer.MAX_VALUE;
        ArrayList<int[]> almanac = new ArrayList<>();
        StringJoiner fileAsString = new StringJoiner(" ");

        try {
            File inputFile = new File(fileName);
            Scanner reader = new Scanner(inputFile);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();

                fileAsString.add(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        String[] splitString = String.valueOf(fileAsString).split("([a-z]+-to-[a-z]+)? ?[a-z]+:");


        int[] initialSeeds = Pattern.compile("(\\d+)")
                .matcher(splitString[1])
                .results()
                .map(MatchResult::group)
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int i = 2; i < splitString.length; i++) {
            int[] arr = Pattern.compile("(\\d+)")
                    .matcher(splitString[i])
                    .results()
                    .map(MatchResult::group)
                    .mapToInt(Integer::parseInt)
                    .toArray();
            System.out.println(Arrays.toString(arr));
            almanac.add(arr);
        }

        for (int[] map : almanac) {
            destinationToSourceMap3(map, initialSeeds);
        }

        for (int location : initialSeeds) {
            if (location < minLocation) {
                minLocation = location;
            }
        }

        return minLocation;
    }

    public static void destinationToSourceMap3(int[] map, int[] sourceMap) {
        for (int i = 0; i < map.length; i+=3) {
            int sourceStart = map[i+1];
            int range = map[i+2];
            System.out.println(sourceStart + " " + range);
            for (int j = sourceStart; j < (sourceStart + range); j++) {
                System.out.println(" " + j);
                int x;
                if ((x = indexOfSource(j, sourceMap)) > 0) {
                    sourceMap[x] = (map[i]+(j-sourceStart));
                }
            }
        }
    }

    public static boolean inSource(int x, int[] source) {
        for (int element : source) {
            if (x == element) {
                return true;
            }
        }

        return false;
    }

    public static int indexOfSource(int x, int[] source) {
        for (int i = 0; i < source.length; i++) {
            if (x == source[i]) {
                return i;
            }
        }

        return -1;
    }

}
