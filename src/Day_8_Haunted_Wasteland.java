import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day_8_Haunted_Wasteland {

    static Pattern p = Pattern.compile("([A-Z0-9]{3}) = \\(([A-Z0-9]{3}), ([A-Z0-9]{3})\\)");

    public static void main(String[] args) {
        prompt();
    }

    public static void prompt() {
        System.out.println("Please provide the desert maps.");
        Scanner s = new Scanner(System.in);
        String fileName = s.nextLine();
        System.out.println("Starting from AAA, it takes " + part1(fileName) + " steps to reach ZZZ.");

        System.out.println("Starting from all nodes that end with 'A', it takes " + part2(fileName) + " steps to reach nodes that end with 'Z'.");
    }

    public static int part1(String fileName) {
        boolean firstLine = true;
        HashMap<String, Node> nodes = new HashMap<>();
        ArrayList<Character> directions = new ArrayList<>();

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


    public static int part2(String fileName) {
        boolean firstLine = true;
        HashMap<String, Node> nodes = new HashMap<>();
        ArrayList<Character> directions = new ArrayList<>();
        ArrayList<Node> startingNodes = new ArrayList<>();

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
                        Node n = new Node(m.group(1), m.group(2), m.group(3));
                        nodes.put(m.group(1), n);
                        if (m.group(1).matches("[A-Z0-9]{2}A")) {
                            startingNodes.add(n);
                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        int steps = 0;

        while (!allNodesEndZ(startingNodes)) {
            if (directions.get(steps % directions.size()) == 'L') {
                startingNodes.replaceAll(node -> nodes.get(node.leftChild));
            }
            if (directions.get(steps % directions.size()) == 'R') {
                startingNodes.replaceAll(node -> nodes.get(node.rightChild));
            }
            steps++;
        }

        return steps;
    }

    public static boolean allNodesEndZ(ArrayList<Node> startingNodes) {
        for (int i = 0; i < startingNodes.size(); i++) {
            if (!startingNodes.get(i).name.matches("[A-Z0-9]{2}Z")) {
                return false;
            }
        }

        return true;
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


        public String toString() {
            return this.name;
        }
    }



}
