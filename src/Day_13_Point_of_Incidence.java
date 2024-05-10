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

        try {
            File inputFile = new File(fileName);
            Scanner reader = new Scanner(inputFile);

            ArrayList<String> pattern = new ArrayList<>();
            ArrayList<Integer> verticalSymmetries = new ArrayList<>();

            while (reader.hasNextLine()) {
                System.out.println(reader.hasNextLine());
                String s = reader.nextLine();
                char[] line = s.toCharArray();
                if (line.length != 0) {
                    // check for vertical symmetry
                    pattern.add(s);

                } else {
                    int LOS = 0;
                    // check for horizontal symmetry
                    for (int i = 0; i < pattern.size(); i++) {
                        for (int j = pattern.size() - 1; j > i; j--) {
                            if (pattern.get(i).equals(pattern.get(j))) {
                                LOS = Math.max(LOS, j-i);
                            } else {
                            }
                        }

//                            if (LOS % 2 == 0) {
//                                result += ((LOS / 2) * 100);
//                            } else {
//                                result += (((LOS / 2) + 1) * 100);
//                            }
                    }

                    // check for vertical symmetry
                    for (int i = 0; i < pattern.size(); i++) {
                        char[] l = pattern.get(i).toCharArray();
                        for (int k = 0; k < l.length; k++) {
                            for (int j = l.length - 1; j > i; j--) {
                                if (l[k] != l[j]) {

                                }
                            }
                        }


                    }


                    pattern = new ArrayList<>();
                }
            }


            result = pattern.size();

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }


        return result;
    }

    /*
        For row 1, iterate through a for loop
            for (int i = 0; i < row.length; i++) {
                if (row[i] == row[(row.length - 1) - i] {
                    do something
                }
            }
     */

    public static class Tuple {
        int a;
        int b;

        public Tuple() {}

        public Tuple(int a, int b) {
            this.a = a;
            this.b = b;
        }





    }


}
