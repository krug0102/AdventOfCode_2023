import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
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
        ArrayList<int[]> seedToSoil = new ArrayList<>();
        ArrayList<int[]> soilToFertilizer = new ArrayList<>();
        ArrayList<int[]> fertilizerToWater = new ArrayList<>();
        ArrayList<int[]> waterToLight = new ArrayList<>();
        ArrayList<int[]> lightToTemperature = new ArrayList<>();
        ArrayList<int[]> temperatureToHumidity = new ArrayList<>();
        ArrayList<int[]> humidityToLocation = new ArrayList<>();



        try {
            File inputFile = new File(fileName);
            Scanner reader = new Scanner(inputFile);

            String currentMap = "";

            while (reader.hasNextLine()) {
                String s = reader.nextLine();

                if (Pattern.matches("([a-zA-Z]+-to-[a-zA-Z]+)", s)) {
                    currentMap = mapName.matcher(s).group();
                    s = reader.nextLine();
                }

                switch (currentMap) {
                    case "seed-to-soil":
                        int[] seeds = numbers.matcher(s)
                                .results()
                                .map(MatchResult::group)
                                .mapToInt(Integer::parseInt)
                                .toArray();
                        seedToSoil.add(seeds);
                        break;
                    case "soil-to-fertilizer":
                        int[] soil = numbers.matcher(s)
                                .results()
                                .map(MatchResult::group)
                                .mapToInt(Integer::parseInt)
                                .toArray();
                        soilToFertilizer.add(soil);
                        break;
                    case "fertilizer-to-water":
                        int[] fertilizer = numbers.matcher(s)
                                .results()
                                .map(MatchResult::group)
                                .mapToInt(Integer::parseInt)
                                .toArray();
                        fertilizerToWater.add(fertilizer);
                        break;
                    case "water-to-light":
                        int[] water = numbers.matcher(s)
                                .results()
                                .map(MatchResult::group)
                                .mapToInt(Integer::parseInt)
                                .toArray();
                        waterToLight.add(water);
                        break;
                    case "light-to-temperature":
                        int[] light = numbers.matcher(s)
                                .results()
                                .map(MatchResult::group)
                                .mapToInt(Integer::parseInt)
                                .toArray();
                        lightToTemperature.add(light);
                        break;
                    case "temperature-to-humidity":
                        int[] temperature = numbers.matcher(s)
                                .results()
                                .map(MatchResult::group)
                                .mapToInt(Integer::parseInt)
                                .toArray();
                        temperatureToHumidity.add(temperature);
                        break;
                    case "humidity-to-location":
                        int[] humidity = numbers.matcher(s)
                                .results()
                                .map(MatchResult::group)
                                .mapToInt(Integer::parseInt)
                                .toArray();
                        humidityToLocation.add(humidity);
                        break;
                }

            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }



        return 0;
    }

    public static int[] destinationToSourceMap(ArrayList<int[]> maps, int[] initialSeedMaps) {
        HashMap<Integer, Integer> destinationToSource = new HashMap<>(); // Key is source, value is destination
        for (int i = 0; i < maps.size(); i++) {
            int[] map = maps.get(i);
            for (int j = map[1]; j < map[1] + map[3]; j++) {
                destinationToSource.put(map[1], map[0]+(j-map[1]));
            }
        }

        for (int i = 0; i < initialSeedMaps.length; i++) {
            int source = initialSeedMaps[i];
            if (destinationToSource.containsKey(source)) {
                initialSeedMaps[i] = destinationToSource.get(source);
            }
        }

        return initialSeedMaps;
    }
}
