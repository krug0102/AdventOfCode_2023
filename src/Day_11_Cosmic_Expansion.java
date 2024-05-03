import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day_11_Cosmic_Expansion {


    public static void main(String[] args) {
        prompt();
    }

    public static void prompt() {
        System.out.println("Please provide the compiled image.");
        Scanner s = new Scanner(System.in);
        String fileName = s.nextLine();
        System.out.println("Part 1: \nThe sum of the shortest paths between every pair of galaxies is: " + part1(fileName));
    }


    public static int part1(String fileName) {
        int sum = 0;

        ArrayList<char[]> universe = new ArrayList<>();
        ArrayList<Integer[]> galaxies = new ArrayList<>();
        ArrayList<Integer> emptyRows = new ArrayList<>();
        ArrayList<Integer> emptyColumns = new ArrayList<>();

        try {
            File input = new File(fileName);
            Scanner reader = new Scanner(input);

            while (reader.hasNextLine()) {
                String s = reader.nextLine();
                char[] arr = s.toCharArray();
                universe.add(arr);

                if (s.contains("#")) {
                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i] == '#') {
                             Integer[] galaxy = new Integer[2];
                             galaxy[0] = universe.size() - 1;  // Row
                             galaxy[1] = i;                    // Column
                             galaxies.add(galaxy);
                        }
                    }
                } else {
                    emptyRows.add(universe.size() - 1);
                }
            }


        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        for (int column = 0; column < universe.get(0).length; column++) {
            boolean empty = true;
            for (int row = 0; row < universe.size(); row++) {
                char c = universe.get(row)[column];
                if (c == '#') {
                    empty = false;
                    break;
                }
            }
            if (empty) {
                emptyColumns.add(column);
            }
        }

        for (int i = 0; i < galaxies.size(); i++) {
            System.out.println(Arrays.toString(galaxies.get(i)));
        }

        for (int i = 0; i < galaxies.size(); i++) {
            int additionalRows = 0;
            int additionalColumns = 0;
            Integer[] galaxy = galaxies.get(i);
            for (int row = 0; row < emptyRows.size(); row++) {
                if (emptyRows.get(row) < galaxy[0]) {
                    additionalRows++;
                }
            }
            for (int column = 0; column < emptyColumns.size(); column++) {
                if (emptyColumns.get(column) < galaxy[1]) {
                    additionalColumns++;
                }
            }
            galaxy[0] += additionalRows;
            galaxy[1] += additionalColumns;
        }

        System.out.println("Empty Rows: " + emptyRows);
        System.out.println("Empty Columns: " + emptyColumns);

        for (int i = 0; i < galaxies.size(); i++) {
            System.out.println(Arrays.toString(galaxies.get(i)));
        }



        for (int i = 0; i < galaxies.size(); i++) {
            Integer[] galaxy = galaxies.get(i);
            for (int j = i + 1; j < galaxies.size(); j++) {
                Integer[] targetGalaxy = galaxies.get(j);
                if (galaxy[0].equals(targetGalaxy[0])) {
                    sum += (targetGalaxy[1] - galaxy[1]);
                } else if (galaxy[1].equals(targetGalaxy[1])) {
                    sum += (targetGalaxy[0] - galaxy[0]);
                } else {
                    int a = targetGalaxy[0] - galaxy[0];
                    int b = targetGalaxy[1] - galaxy[1];
                    sum += (a + b);
                }

            }
        }

        return sum;
    }



    public static int calculateHypotenuse(int a, int b) {
        return 0;
    }
}
