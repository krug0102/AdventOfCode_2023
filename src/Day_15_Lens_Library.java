import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

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
        System.out.println("Part 2: \nThe total focusing power is: " + part2(fileName));
    }


    public static int part1(String fileName) {
        int result = 0;

        try {
            File input = new File(fileName);
            Scanner reader = new Scanner(input);

            while (reader.hasNextLine()) {
                String[] line = reader.nextLine().split(",");
                for (int i = 0; i < line.length; i++) {
                    result += HASH(line[i]);
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }


        return result;
    }


    public static int part2(String fileName) {
        int result = 0;
        HashMap<Integer, ArrayList<Lens>> initSequence = new HashMap();
        try{
            File inputFile = new File(fileName);
            Scanner reader = new Scanner (inputFile);

            while (reader.hasNextLine()) {
                String[] line = reader.nextLine().split(",");
                for (int i = 0; i < line.length; i++) {
                    String[] b = line[i].split("[-=]");
                    int hash = HASH(b[0]);
                    if (b.length == 2) {
                        // TODO: Need to replace the same lens name in the ArrayList
                        Lens newLens = new Lens(b[0], Integer.parseInt(b[1]));
                        if (!initSequence.containsKey(hash)) {
                            ArrayList<Lens> l = new ArrayList<>();
                            l.add(newLens);
                            initSequence.put(hash, l);
                        } else {
                            int x = replaceLens(newLens, initSequence.get(hash));
                            if (x != -1) {
                                initSequence.get(hash).set(x, newLens);
                            } else {
                                initSequence.get(hash).add(newLens);
                            }
                        }
                    } else {
                        if (initSequence.containsKey(hash)) {
                            for (int k = 0; k < initSequence.get(hash).size(); k++) {
                                if (initSequence.get(hash).get(k).name.equals(b[0])) {
                                    initSequence.get(hash).remove(k);
                                }
                            }
                        }
                    }
                }
            }


        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        for (Map.Entry<Integer, ArrayList<Lens>> entry : initSequence.entrySet()) {
            System.out.println(entry.getKey());
            ArrayList<Lens> l = entry.getValue();
            for (int i = 0; i < l.size(); i++) {
                System.out.println(entry.getValue().get(i));
                result += (entry.getKey() + 1) * (i+1) * (l.get(i).focalLength);
            }
        }

        return result;
    }


    public static int replaceLens(Lens newLens, ArrayList<Lens> l) {
        int index = -1;
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).name.equals(newLens.name)) {
                index = i;
                break;
            }
        }

        return index;
    }



    public static int HASH(String s) {
        int x = 0;
        char[] chars = s.toCharArray();
        for (int j = 0; j < chars.length; j++) {
            x += (int) chars[j];
            x *= 17;
            x = (x % 256);
        }

        return x;
    }

    public static class Lens {
        String name;
        int focalLength;

        public Lens() {}

        public Lens(String name, int focalLength) {
            this.name = name;
            this.focalLength = focalLength;
        }

        public String toString() {
            return "[" + this.name + " " + this.focalLength + "]";
        }
    }
}
