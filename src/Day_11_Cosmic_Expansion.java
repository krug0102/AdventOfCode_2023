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

        System.out.println("Part 2: \nThe sum of the shortest paths between every pair of galaxies is: " + part2(fileName));
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

//        for (int i = 0; i < galaxies.size(); i++) {
//            System.out.println(Arrays.toString(galaxies.get(i)));
//        }

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

//        System.out.println("Empty Rows: " + emptyRows);
//        System.out.println("Empty Columns: " + emptyColumns);
//
//        for (int i = 0; i < galaxies.size(); i++) {
//            System.out.println(Arrays.toString(galaxies.get(i)));
//        }



        for (int i = 0; i < galaxies.size(); i++) {
            Integer[] galaxy = galaxies.get(i);
            for (int j = i + 1; j < galaxies.size(); j++) {
                Integer[] targetGalaxy = galaxies.get(j);
                int a = Math.max(targetGalaxy[0], galaxy[0]) - Math.min(targetGalaxy[0], galaxy[0]);
                int b = Math.max(targetGalaxy[1], galaxy[1]) - Math.min(targetGalaxy[1], galaxy[1]);
                if (a == 0) {
                    sum += b;
                } else if (b == 0) {
                    sum += a;
                } else {
                    sum += (a + b);
                }

            }
        }

        return sum;
    }



    public static long part2(String fileName) {
        long sum = 0;
        long row = 0;
        long columns = 0;
        long offset = 1000000;

        ArrayList<Long[]> galaxies = new ArrayList<>();
        ArrayList<Long> emptyRows = new ArrayList<>();
        ArrayList<Long> emptyColumns = new ArrayList<>();

        try {
            File input = new File(fileName);
            Scanner reader = new Scanner(input);

            while (reader.hasNextLine()) {
                String s = reader.nextLine();
                char[] arr = s.toCharArray();

                if (columns == 0) {
                    columns = arr.length;
                }

                if (s.contains("#")) {
                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i] == '#') {
                            Long[] galaxy = new Long[2];
                            galaxy[0] = row;  // Row
                            galaxy[1] = (long) i;   // Column
                            galaxies.add(galaxy);
                        }
                        row++;
                    }
                } else {
                    emptyRows.add(row);
                }
            }


        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        for (int i = 0; i < columns; i++) {
            boolean empty = true;
            for (int j = 0; j < galaxies.size(); j++) {
                if (galaxies.get(j)[1] == '#') {
                    empty = false;
                    break;
                }
            }

            if (empty) {
                emptyColumns.add((long) i);
            }
        }

        for (int i = 0; i < galaxies.size(); i++) {
            System.out.println(Arrays.toString(galaxies.get(i)));
        }

        for (int i = 0; i < galaxies.size(); i++) {
            long additionalRows = 0;
            long additionalColumns = 0;
            Long[] galaxy = galaxies.get(i);
            for (int r = 0; r < emptyRows.size(); r++) {
                if (emptyRows.get(r) < galaxy[0]) {
                    additionalRows += offset;
                }
            }
            for (int column = 0; column < emptyColumns.size(); column++) {
                if (emptyColumns.get(column) < galaxy[1]) {
                    additionalColumns += offset;
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
            Long[] galaxy = galaxies.get(i);
            for (int j = i + 1; j < galaxies.size(); j++) {
                Long[] targetGalaxy = galaxies.get(j);
                long a = Math.max(targetGalaxy[0], galaxy[0]) - Math.min(targetGalaxy[0], galaxy[0]);
                long b = Math.max(targetGalaxy[1], galaxy[1]) - Math.min(targetGalaxy[1], galaxy[1]);
                if (a == 0) {
                    sum += b;
                } else if (b == 0) {
                    sum += a;
                } else {
                    sum += (a + b);
                }

            }
        }

        return sum;
    }

}
