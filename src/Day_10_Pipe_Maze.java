import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Day_10_Pipe_Maze {
    /**
     * Class Point
     *  stores a character, x and y positions
     *
     *  Read every character in the input and create a point object for it
     *  Then, find S, and start walking down the possible paths
     *
     *  If we come to a dead end, either '.' or something else, return and wipe progress
     *
     *  Then go down another route
     *
     *  Could build a tree and until we get back to S
     */

    public static void main(String[] args) {

    }


    public static int part1(String fileName) {
        int steps = 0;
        Point startPoint = new Point();
        ArrayList<char[]> maze = new ArrayList<>();
        try {
            File input = new File(fileName);
            Scanner reader = new Scanner(input);

            while (reader.hasNextLine()) {
                String s = reader.nextLine();
                char[] line = s.toCharArray();
                maze.add(line);

                if (s.contains("S")) {
                    for (int i = 0; i < line.length; i++) {
                        char c = line[i];
                        if (c == 'S') {
                            startPoint.x = i;
                            startPoint.y = maze.size() - 1;
                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }




        return steps;
    }


    public static class Point {
        int x;
        int y;
        char pipe;
        public Point() {}

        public Point(int x, int y, char c) {
            this.x = x;
            this.y = y;
            this.pipe = c;
        }


        public void startingPointPipe() {

        }

        public boolean connected(char connectingPipe) {
            switch (this.pipe) {
                case '|':
                    return Pattern.matches("[|LJ7F]", String.valueOf(connectingPipe));
                case '-':
                    return Pattern.matches("[LJ7F]", String.valueOf(connectingPipe));
                case 'L':
                    return Pattern.matches("[|\\-J7F]", String.valueOf(connectingPipe));
                case 'J':
                    return Pattern.matches("[|\\-L7F]", String.valueOf(connectingPipe));
                case '7':
                    return Pattern.matches("[|\\-LF]", String.valueOf(connectingPipe));
                case 'F':
                    return Pattern.matches("[|\\-J7]", String.valueOf(connectingPipe));
                default:
                    return false;
            }
        }



    }
}
