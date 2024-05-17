import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day_15_Lens_Library {
    


    public static void main(String[] args) {
        prompt();
    }



    // ignore newline

    public static void prompt() {
        System.out.println("Please provide the initialation sequence.");
        Scanner s = new Scanner(System.in);
        String fileName = s.nextLine();
        System.out.println("Part 1: \nThe result of running the HASH on the initialization sequence is: " + part1(fileName));
    }


    public static int part1(String fileName) {
        int result = 0;

        try {
            File input = new File(fileName);
            Scanner reader = new Scanner(input);

            while (reader.hasNextLine()) {
                String[] line = reader.nextLine().split(",");
                for (int i = 0; i < line.length; i++) {
                    int x = 0;
                    char[] chars = line[i].toCharArray();
                    for (int j = 0; j < chars.length; j++) {
                        x += (int) chars[j];
                        x *= 17;
                        x = (x % 256);
                    }

                    System.out.println(x);

                    result += x;
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }


        return result;
    }
}
