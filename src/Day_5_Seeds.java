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


    public static long part1(String fileName) {
        long minLocation = Long.MAX_VALUE;
        ArrayList<long[]> almanac = new ArrayList<>();
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

        long[] initialSeeds = Pattern.compile("(\\d+)")
                .matcher(splitString[1])
                .results()
                .map(MatchResult::group)
                .mapToLong(Long::parseLong)
                .toArray();

        for (int i = 2; i < splitString.length; i++) {
            long[] arr = Pattern.compile("(\\d+)")
                    .matcher(splitString[i])
                    .results()
                    .map(MatchResult::group)
                    .mapToLong(Long::parseLong)
                    .toArray();
            System.out.println(Arrays.toString(arr));
            almanac.add(arr);
        }

        for (long[] map : almanac) {
            destinationToSource(map, initialSeeds);
        }

        for (long location : initialSeeds) {
            if (location < minLocation) {
                minLocation = location;
            }
        }

        return minLocation;
    }


    public static void destinationToSource(long[] map, long[] sourceMap) {
        for (int i = 0; i < sourceMap.length; i++) {
            long x = sourceMap[i];
            for (int j = 0; j < map.length; j+=3) {
                long destinationStart = map[j];
                long sourceStart = map[j+1];
                long range = map[j+2];
                if (sourceStart <= x && x < (sourceStart + range)) {
                    long destination = destinationStart + (x - sourceStart);
                    if (destinationStart <= destination && destination < (destinationStart + range)) {
                        sourceMap[i] = destination;
                    }
                }
            }
        }
    }

}
