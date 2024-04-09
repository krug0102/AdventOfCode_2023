import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.MatchResult;

public class Day_4_Scratchcards {

    public static void main(String[] args) {
        prompt();
    }

    public static void prompt() {
        System.out.println("Please provide the scratchcards.");
        Scanner s = new Scanner(System.in);
        String fileName = s.nextLine();
        System.out.println("The scratchcards are worth " + part1(fileName) + " points");
    }

    public static int part1(String fileName) {
        int sum = 0;
        try {
            File inputFile = new File(fileName);
            Scanner reader = new Scanner(inputFile);
            while (reader.hasNextLine()) {
                String[] line = reader.nextLine().split(":");
                String[] numbers = line[1].split("\\|");
                int[] winningNumbers = Pattern.compile("(\\d+)")
                        .matcher(numbers[0])
                        .results()
                        .map(MatchResult::group)
                        .mapToInt(Integer::parseInt)
                        .toArray();

                int[] myNumbers = Pattern.compile("(\\d+)")
                        .matcher(numbers[1])
                        .results()
                        .map(MatchResult::group)
                        .mapToInt(Integer::parseInt)
                        .toArray();

                sum = sum + cardPoints(winningNumbers, myNumbers);
            }
        } catch(FileNotFoundException e) {
            System.out.println(e);
        }


        return sum;
    }


    // Read all cards into a HashMap of ScratchCards
    // Then, build a tree of ScratchCards, starting from 1, and count the leaf nodes
    public static int part2(String fileName) {
        int totalCardsWon = 0;



        return totalCardsWon;
    }

        public static int cardPoints(int[] winningNumbers, int[] myNumbers) {
            int points = 0;
            for (int i = 0; i < winningNumbers.length; i++) {
                for (int j = 0; j < myNumbers.length; j++) {
                    if (winningNumbers[i] == myNumbers[j]) {
                        if (points == 0) {
                            points = 1;
                        } else {
                            points = points * 2;
                        }
                    }
                }
            }

        return points;
        }


        public static class ScratchCard {
            int cardNumber;
            int matchingNumbers;

            public ScratchCard() {}

            public ScratchCard(int cardNumber) {
                this.cardNumber = cardNumber;
            }

        }
}
