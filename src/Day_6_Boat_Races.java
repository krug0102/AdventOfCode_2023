import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Day_6_Boat_Races {


    public static void main(String[] args) {
       prompt();
    }

    public static void prompt() {
        System.out.println("Please provide race document.");
        Scanner s = new Scanner(System.in);

        String fileName = s.nextLine();
        System.out.println("The product of the number of ways to beat the records is: "+ part1(fileName));

        System.out.println("There are " + part2(fileName) + " number of ways to beat the one, long race.");
    }


    public static int part1(String fileName) {
        int product = 1;
        ArrayList<Integer> numbers = new ArrayList<>();

        try {
            File RaceDocument = new File(fileName);
            Scanner reader = new Scanner(RaceDocument);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                int[] x = Pattern.compile("(\\d+)")
                        .matcher(line)
                        .results()
                        .map(MatchResult::group)
                        .mapToInt(Integer::parseInt)
                        .toArray();

                for (int i = 0; i < x.length; i++) {
                    numbers.add(x[i]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        for (int i = 0; i < numbers.size()/2; i++) {
            product = product * recordsBeat(numbers.get(i), numbers.get(i+(numbers.size()/2)));
        }

        return product;
    }

    public static int recordsBeat(int time, int record) {
        int numberOfRecordsBeat = 0;
        for (int i = 0; i < time; i++) {
            int travelTime = time - i;
            int distance = i * travelTime;

            if (distance > record) {
                numberOfRecordsBeat++;
            }
        }

        return numberOfRecordsBeat;
    }

    public static long part2(String fileName) {
        ArrayList<String> numbers = new ArrayList<>();

        try {
            File RaceDocument = new File(fileName);
            Scanner reader = new Scanner(RaceDocument);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] x = Pattern.compile("(\\d+)")
                        .matcher(line)
                        .results()
                        .map(MatchResult::group)
                        .toArray(String[]::new);

                Collections.addAll(numbers, x);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        StringBuilder time = new StringBuilder();
        StringBuilder record = new StringBuilder();

        for (int i = 0; i < numbers.size()/2; i++) {
            time.append(numbers.get(i));
            record.append(numbers.get(i+(numbers.size()/2)));
        }

       return waysToBeatRecord(Long.parseLong(String.valueOf(time)), Long.parseLong(String.valueOf(record)));
    }

    public static long waysToBeatRecord(long time, long record) {
        long numberOfRecordsBeat = 0;
        for (int i = 0; i < time; i++) {
            long travelTime = time - i;
            long distance = i * travelTime;

            if (distance > record) {
                numberOfRecordsBeat++;
            }
        }

        return numberOfRecordsBeat;
    }
}
