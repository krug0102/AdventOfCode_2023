import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
public class Day_2_Cube_Conundrum {

    public static void main(String[] args) {
        prompt();
    }

    public static void prompt() {
        System.out.println("Please provide the game records.");
        Scanner s = new Scanner(System.in);
        String fileName = s.nextLine();

        System.out.println("The sum of the game IDs that are possible is: " + part1(fileName));
        System.out.println("The sum of the power of the sets is: " + part2(fileName));
    }


    // TODO: Found solution to separate counts and colors into arrays here:
    // https://stackoverflow.com/questions/6020384/create-array-of-regex-matches/46859130#46859130
    public static int part1(String input) {
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


                //TODO: Could only add a game to the list if it is possible, instead of having a 'possible' field.
                // Then, I wouldn't have to loop through the list and check if a game is possible
                for (int i = 1; i < numbers.length; i++) {
                    if ((colors[i].equals("red") && Integer.parseInt(numbers[i]) > 12) | (colors[i].equals("green") && Integer.parseInt(numbers[i]) > 13) | (colors[i].equals("blue") && Integer.parseInt(numbers[i]) > 14)) {
                        g.possible = false;
                        break;
                    } else
                        g.possible = true;
                }

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

    public static int part2(String fileName) {
        int sumOfPower = 0;
        int gameNumber = 1;

        try {
            File input = new File(fileName);
            Scanner reader = new Scanner(input);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] pulls = Pattern.compile("([0-9]+ [a-z]+)")
                        .matcher(line)
                        .results()
                        .map(MatchResult::group)
                        .toArray(String[]::new);

                Game game = new Game(gameNumber);

                for (String pull : pulls) {
                    String[] split = pull.split(" ");
                    if (split[1].equals("red")) {
                        if (game.minimumReds < Integer.parseInt(split[0])) {
                            game.minimumReds = Integer.parseInt(split[0]);
                        }
                    }
                    if (split[1].equals("green")) {
                        if (game.minimumGreens < Integer.parseInt(split[0])) {
                            game.minimumGreens = Integer.parseInt(split[0]);
                        }
                    }
                    if (split[1].equals("blue")) {
                        if (game.minimumBlues < Integer.parseInt(split[0])) {
                            game.minimumBlues = Integer.parseInt(split[0]);
                        }
                    }
                }

                sumOfPower = sumOfPower + (game.minimumReds * game.minimumGreens * game.minimumBlues);

                gameNumber++;
            }


        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        return sumOfPower;
    }




    public static class Game {
        int id;

        int minimumReds;
        int minimumGreens;
        int minimumBlues;
        boolean possible;

        public Game() {}

        public Game(int id) {
            this.id = id;
        }

        public String toString() {
           if (this.possible) {
               return "Game " + this.id + " is possible.";
           } else
               return "Game " + this.id + " is NOT possible";

        }
   }
}
