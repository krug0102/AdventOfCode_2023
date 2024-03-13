import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
public class Day_2_Cube_Conundrum {

    // TODO: Found solution to separate counts and colors into arrays here:
    // https://stackoverflow.com/questions/6020384/create-array-of-regex-matches/46859130#46859130

    public static void main(String[] args) {
        prompt();
    }

    public static void prompt() {
        System.out.println("Please provide the game records.");
        Scanner s = new Scanner(System.in);
        String fileName = s.nextLine();
        System.out.println("The sum of the calibration values is: " + sumOfIDs(fileName));
    }

    public static int sumOfIDs(String input) {
        int sum = 0;
        ArrayList<Game> games = new ArrayList<>();
        try {
            File inputFile = new File(input);
            Scanner reader = new Scanner(inputFile);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] numbers = Pattern.compile("(\\d+)")
                        .matcher(line)
                        .results()
                        .map(MatchResult::group)
                        .toArray(String[]::new);

                String[] colors = Pattern.compile("([a-zA-Z]+)")
                        .matcher(line)
                        .results()
                        .map(MatchResult::group)
                        .toArray(String[]::new);

                Game g = new Game(Integer.parseInt(numbers[0]));

                for (int i = 1; i < numbers.length; i++) {
                    if (colors[i].equals("red")) {
                        g.redCount = g.redCount + Integer.parseInt(numbers[i]);
                    }
                    if (colors[i].equals("green")) {
                        g.greenCount = g.greenCount + Integer.parseInt(numbers[i]);
                    }
                    if (colors[i].equals("blue")) {
                        g.blueCount = g.blueCount + Integer.parseInt(numbers[i]);
                    }
                }

                g.isPossible();

                games.add(g);

            }
        } catch(FileNotFoundException e) {
            System.out.println(e);
        }

        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).possible) {
                sum = sum + games.get(i).id;
            }
        }

        return sum;
    }




    public static class Game {
        int id;
        int redCount, greenCount, blueCount;

        boolean possible;

        public Game() {}

        public Game(int id) {
            this.id = id;
        }

        public Game(int id, int reds, int greens, int blues) {
            this.id = id;
            this.redCount = reds;
            this.greenCount = greens;
            this.blueCount = blues;
            this.possible = false;
        }

        public void isPossible() {
            if (this.redCount <= 12 && this.greenCount <= 13 && this.blueCount <= 14) {
                this.possible = true;
            }
        }
    }
}
