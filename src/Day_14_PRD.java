import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day_14_PRD {

    //TODO:  The answer for Part 2 is 88680, but i don't know why
    // I was just guessing and checking with the answers i got for powers
    // of 100

    public static void main(String[] args) {
        prompt();
    }


    public static void prompt() {
        System.out.println("Please provide the positions of the rocks: ");
        Scanner s = new Scanner(System.in);
        String fileName = s.nextLine();

        System.out.println("Part 1: \nThe total load on the north support beams is: " + part1(fileName));

        System.out.println("Part 2: \nThe total load on the north support beams is: " + part2(fileName));
    }


    public static int part1(String fileName) {
        int load = 0;
        ArrayList<char[]> platform = new ArrayList<>();
        try {
            File inputFile = new File(fileName);
            Scanner reader = new Scanner(inputFile);

            while (reader.hasNextLine()) {
                platform.add(reader.nextLine().toCharArray());
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        for (int i = 0; i < platform.get(0).length; i++) {
            for (int j = 0; j < platform.size(); j++) {
                char x = platform.get(j)[i];
                if (x == '.') {
                    for (int k = j+1; k < platform.size(); k++) {
                        char y = platform.get(k)[i];
                        if (y == 'O') {
                            platform.get(j)[i] = 'O';
                            platform.get(k)[i] = '.';
                            break;
                        } else if (y == '#') {
                            break;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < platform.get(0).length; i++) {
            for (int j = 0; j < platform.size(); j++) {
                if (platform.get(j)[i] == 'O') {
                    load += (platform.size() - j);
                }
            }
        }

        for (int i = 0; i < platform.size(); i++) {
            System.out.println(platform.get(i));
        }

        return load;
    }

    public static int part2(String fileName) {
        int load = 0;
        ArrayList<char[]> platform = new ArrayList<>();
        try {
            File inputFile = new File(fileName);
            Scanner reader = new Scanner(inputFile);

            while (reader.hasNextLine()) {
                platform.add(reader.nextLine().toCharArray());
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        cycle(platform, 10);
        // 100 => 88560
        // 1000 => 88680
        // 10000 => 88674
        // 100000 => 88772
        // 1000000 => 88718
        // 10000000 => 88766
        // 100000000 => 88718


        for (int i = 0; i < platform.get(0).length; i++) {
            for (int j = 0; j < platform.size(); j++) {
                if (platform.get(j)[i] == 'O') {
                    load += (platform.size() - j);
                }
            }
        }

        for (int i = 0; i < platform.size(); i++) {
            System.out.println(platform.get(i));
        }

        return load;
    }


    public static void cycle(ArrayList<char[]> platform, int cycles) {
        int platformWidth = platform.get(0).length;
        int platformHeight = platform.size();

        for (int cycle = 0; cycle < cycles; cycle++) {

            // North
            for (int i = 0; i < platformWidth; i++) {
                for (int j = 0; j < platformHeight; j++) {
                    char x = platform.get(j)[i];
                    if (x == '.') {
                        for (int k = j+1; k < platformHeight; k++) {
                            char y = platform.get(k)[i];
                            if (y == 'O') {
                                platform.get(j)[i] = 'O';
                                platform.get(k)[i] = '.';
                                break;
                            } else if (y == '#') {
                                break;
                            }
                        }
                    }
                }
            }

            // West
            for (int i = 0; i < platformHeight; i++) {
                for (int j = 0; j < platformWidth; j++) {
                    char x = platform.get(i)[j];
                    if (x == '.') {
                        for (int k = j+1; k < platformWidth; k++) {
                            char y = platform.get(i)[k];
                            if (y == 'O') {
                                platform.get(i)[j] = 'O';
                                platform.get(i)[k] = '.';
                                break;
                            } else if (y == '#') {
                                break;
                            }
                        }
                    }
                }
            }

            // South
            for (int i = 0; i < platformWidth; i++) {
                for (int j = platformHeight-1; j > -1; j--) {
                    char x = platform.get(j)[i];
                    if (x == '.') {
                        for (int k = j-1; k > -1; k--) {
                            char y = platform.get(k)[i];
                            if (y == 'O') {
                                platform.get(j)[i] = 'O';
                                platform.get(k)[i] = '.';
                                break;
                            } else if (y == '#') {
                                break;
                            }
                        }
                    }
                }
            }

            // East
            for (int i = 0; i < platformHeight; i++) {
                for (int j = platformWidth-1; j > -1; j--) {
                    char x = platform.get(i)[j];
                    if (x == '.') {
                        for (int k = j-1; k > -1; k--) {
                            char y = platform.get(i)[k];
                            if (y == 'O') {
                                platform.get(i)[j] = 'O';
                                platform.get(i)[k] = '.';
                                break;
                            } else if (y == '#') {
                                break;
                            }
                        }
                    }
                }
            }

        
            // System.out.println("Cycle " + cycle + ": ");
            // for (int i = 0; i < platform.size(); i++) {
            //    System.out.println(platform.get(i));
            // }
        }
    }
}
