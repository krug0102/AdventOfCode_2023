import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day_13_Point_of_Incidence {

    public static void main(String[] args) {
        prompt();
    }


    public static void prompt() {
        System.out.println("Please provide the notes: ");
        Scanner s = new Scanner(System.in);
        String fileName = s.nextLine();

        System.out.println("Part 1: \nThe summary of the notes is: " + part1(fileName));
    }

    public static int part1(String fileName) {
        int result = 0;
        ArrayList<String> pattern = new ArrayList<>();

        try {
            File inputFile = new File(fileName);
            Scanner reader = new Scanner(inputFile);

            while (reader.hasNextLine()) {
                String s = reader.nextLine();
                char[] line = s.toCharArray();
                int verticalSymmetry = -1;

                if (line.length != 0) {
                    // check for vertical symmetry
                    // TODO: Do this after reading all lines into the pattern
                    System.out.println(line);
                    for (int i = 0; i < line.length; i++) {
                        char[] x = new char[line.length - i];
                        for (int j = 0; j < x.length; j++) {
                            x[j] = line[j+i];
                        }
                        if (isVerticallySymmetric(x)) {
                            verticalSymmetry = (x.length/2) + i;
                            break;
                        }
                    }
                    
                    System.out.println(verticalSymmetry);

                    pattern.add(s);
                } else {
                    for (int i = 0; i < pattern.size(); i++) {
                        System.out.println(pattern.get(i));
                    }
                    pattern = new ArrayList<>();
                }
            }

            for (int i = 0; i < pattern.size(); i++) {
                System.out.println(pattern.get(i));
            }

            result = pattern.size();

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }


        return result;
    }

    /*
     * int i = 0;
                    int end = line.length - 1;
                    while (i < end) {  // TODO: Infinite loop when i isn't incremented
                        if (line[i] == line[end]) {
                            i++;
                            end--;
                        } else {
                            i++;
                            end = line.length - 1;
                        }
                    }

                    System.out.println(i);
     */


    public static boolean isVerticallySymmetric(char[] arr) {
        for (int i = 0; i < arr.length/2; i++) {
            if (arr[i] != arr[(arr.length - 1) - i]) {
                return false;
            }
        }

        return true;
    }
}
