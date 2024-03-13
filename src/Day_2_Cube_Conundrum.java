import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
public class Day_2_Cube_Conundrum {
    Pattern p = Pattern.compile("Game (\\d+): (\\d+) ([a-zA-Z]+)");


    public static int sumOfIDs(String input) {
        int sum = 0;
        int games = 0;
        try {
            File inputFile = new File(input);
            Scanner reader = new Scanner(inputFile);
            while (reader.hasNextLine()) {
                char[] line = reader.nextLine().toCharArray();
                games++;

            }
        } catch(FileNotFoundException e) {
            System.out.println(e);
        }

        return sum;
    }

    public static void findPairs(char[] game) {
        //ArrayList<ColorCountPair> pairs = new ArrayList<>();
        Game g = new Game();
        for (int i = 0; i < game.length; i++) {
            if (Character.isDigit(game[i])) {
                //pairs.add(new ColorCountPair(Character.getNumericValue(game[i]), game[i+2]));

            }
        }
    }

    public static void countColorPairs(ArrayList<ColorCountPair> colorCountPairs) {
        for (int i = 0; i < colorCountPairs.size(); i++) {
            // iterate over the color count pairs and create a new Game object which holds all of the
        }
    }


    public static class ColorCountPair {
        int count;  // The number of cubes pulled
        Character color;  // an r, g, or b depending on the color of the cube

        public ColorCountPair(int count, Character color) {
            this.count = count;
            this.color = color;
        }
    }

    public static class Game {
        int id;
        int redCount, greenCount, blueCount;

        boolean possible;

        public Game() {}

        public Game(int id, int reds, int greens, int blues) {
            this.id = id;
            this.redCount = reds;
            this.greenCount = greens;
            this.blueCount = blues;
            this.possible = false;
        }
    }
}
