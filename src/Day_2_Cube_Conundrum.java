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
        System.out.println("The sum of the game IDs that are possible is: " + sumOfIDs(fileName));
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




    public static class Game {
        int id;
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
