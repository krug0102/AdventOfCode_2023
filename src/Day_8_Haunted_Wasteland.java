import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day_8_Haunted_Wasteland {

    static Pattern p = Pattern.compile("([A-Z]{3}) = \\(([A-Z]{3}), ([A-Z]{3})\\)");
    static HashMap<String, Node> nodes = new HashMap<>();
    static ArrayList<Character> directions = new ArrayList<>();


    //TODO: This problem lends itself to being recursive, but I can't figure it out right now.
    public static void main(String[] args) {
        prompt();
    }

    public static void prompt() {
        System.out.println("Please provide the desert maps.");
        Scanner s = new Scanner(System.in);
        System.out.println("Starting from AAA, it takes " + part1(s.nextLine()) + " steps to reach ZZZ.");
    }

    public static int part1(String fileName) {
        int stepCount;
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


        stepCount = calculateSteps(nodes.get("AAA"), 0);

        return stepCount;
    }

    public static int calculateSteps(Node rootNode, int direction){
        int result = 0;
        System.out.println(direction);
        if (rootNode.name.equals("ZZZ")) {
            return 0;
        } else {
            if (directions.get(direction % directions.size()) == 'L') {
                result = 1 + calculateSteps(nodes.get(rootNode.leftChild), direction+1 % directions.size());
            } else if (directions.get(direction % directions.size()) == 'R') {
                result = 1 + calculateSteps(nodes.get(rootNode.rightChild), direction+1 % directions.size());
            }
        }
        return result;
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
