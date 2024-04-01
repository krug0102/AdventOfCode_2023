import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day_8_Haunted_Wasteland {

    static Pattern p = Pattern.compile("([A-Z]{3}) = \\(([A-Z]{3}), ([A-Z]{3})\\)");
    static HashMap<String, Node> nodes = new HashMap<>();
    static ArrayList<Character> directions = new ArrayList<>();

    public static void main(String[] args) {
        prompt();
    }

    public static void prompt() {
        System.out.println("Please provide the desert maps.");
        Scanner s = new Scanner(System.in);
        String fileName = s.nextLine();
        System.out.println("Starting from AAA, it takes " + part1(fileName) + " steps to reach ZZZ.");
    }

    public static int part1(String fileName) {
        boolean firstLine = true;

        try {
            File input = new File(fileName);
            Scanner reader = new Scanner(input);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (firstLine) {
                    char[] x = line.toCharArray();
                    for (char c : x) {
                        directions.add(c);
                    }
                    firstLine = false;
                }

                Matcher m = p.matcher(line);
                while (m.find()) {
                    if (!nodes.containsKey(m.group(1))) {
                        nodes.put(m.group(1), new Node(m.group(1), m.group(2), m.group(3)));
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }



        Node n = nodes.get("AAA");
        int steps = 0;

        while (!n.equals(nodes.get("ZZZ"))) {
            if (directions.get(steps % directions.size()) == 'L') {
                n = nodes.get(n.leftChild);
            }
            if (directions.get(steps % directions.size()) == 'R') {
                n = nodes.get(n.rightChild);
            }
            steps++;
        }


        return steps;
    }


    public static class Node {
        String name;

        String leftChild;
        String rightChild;


        public Node() {}

        public Node(String name) {
            this.name = name;
        }

        public Node(String name, String leftChild, String rightChild) {
            this.name = name;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }



}
