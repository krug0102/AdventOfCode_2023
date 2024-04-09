import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Array;
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

        System.out.println("Part 1: \nThe lowest location number that corresponds to an initial seed is " + part1(fileName));

        System.out.println("Part 2: \nThe lowest location number that corresponds to an initial seed is " + part2(fileName));
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
            almanac.add(arr);
        }

        for (long[] map : almanac) {
            sourceToDestination(map, initialSeeds);
        }

        for (long location : initialSeeds) {
            if (location < minLocation) {
                minLocation = location;
            }
        }

        return minLocation;
    }


    // TODO: Works on smaller data sets, like the example, but runs out of memory on larger sets, like the actual input
    public static long part2(String fileName) {
        long minLocation = Long.MAX_VALUE;
        ArrayList<long[]> almanac = new ArrayList<>();
        StringJoiner fileAsString = new StringJoiner(" ");

        try {
            File input = new File(fileName);
            Scanner reader = new Scanner(input);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                fileAsString.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        String[] splitString = String.valueOf(fileAsString).split("([a-z]+-to-[a-z]+)? ?[a-z]+:");

        long[] seedRanges = Pattern.compile("(\\d+)")
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
            almanac.add(arr);
        }

        for (int i = 0; i < seedRanges.length; i+=2) {
            long seedStart = seedRanges[i];
            long range = seedRanges[i+1];
            long[] initialSeeds = new long[(int)range];
            for (int k = 0; k < range; k++) {
                initialSeeds[k] = seedStart + k;
            }
            for (int j = 0; j < almanac.size(); j++) {
                sourceToDestination(almanac.get(j), initialSeeds);
            }
            for (int l = 0; l < initialSeeds.length; l++) {
                if (initialSeeds[l] < minLocation) {
                    minLocation = initialSeeds[l];
                }
            }
        }

        return minLocation;
    }


    public static long part2v2(String fileName) {
        long minLocation = Long.MAX_VALUE;
        ArrayList<long[]> almanac = new ArrayList<>();
        StringJoiner fileAsString = new StringJoiner(" ");

        try {
            File input = new File(fileName);
            Scanner reader = new Scanner(input);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                fileAsString.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        String[] splitString = String.valueOf(fileAsString).split("([a-z]+-to-[a-z]+)? ?[a-z]+:");

        long[] seedRanges = Pattern.compile("(\\d+)")
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
            almanac.add(arr);
        }

        int size = 0;
        for (int i = 0; i < seedRanges.length; i+=2) {
            size = (int) (seedRanges[i+1] + size);
        }

        // size is 1,835,328,022 for Day 5 input.  Way too big
        // Need to come up with a clever way to reduce the amount of ram being used
        // Hashmap to store changes?

        long[] initialSeeds = new long[size];
        int index = 0;
        for (int i = 0; i < seedRanges.length; i+=2) {
            long start = seedRanges[i];
            long range = seedRanges[i+1];
            for (int j = 0; j < range; j++) {
                initialSeeds[index] = start + j;
                index++;
            }
        }


        for (int i = 0; i < almanac.size(); i++) {
            sourceToDestination(almanac.get(i), initialSeeds);
        }

        for (int i = 0; i < initialSeeds.length; i++) {
            if (initialSeeds[i] < minLocation) {
                minLocation = initialSeeds[i];
            }
        }

        return minLocation;
    }



    public static void sourceToDestination(long[] map, long[] sourceMap) {
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
