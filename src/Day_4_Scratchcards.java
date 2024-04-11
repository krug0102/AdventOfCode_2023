import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.*;
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

        System.out.println("The total number of scratch cards is " + part2(fileName));
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
    public static int part2(String fileName) {
        int totalCards = 0;
        HashMap<Integer, ScratchCard> originalScratchCards = new HashMap<>();

        try {
            File input = new File(fileName);
            Scanner reader = new Scanner(input);

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

                int cardNumber = Integer.parseInt(line[0].replaceAll("[^0-9]", ""));

                ScratchCard card = new ScratchCard(cardNumber);

                card.matches = matchingNumbers(winningNumbers, myNumbers);

                originalScratchCards.put(cardNumber, card);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        // loop through the scratchcards
        for (Map.Entry<Integer, ScratchCard> entry : originalScratchCards.entrySet()) {
            ScratchCard s = entry.getValue();
            // for each scratchcard, we look at the 'matches' number of following cards and update their
            // number of copies
            for (int i = s.cardNumber + 1; i <= s.cardNumber + s.matches; i++) {
                ScratchCard copy = originalScratchCards.get(i);
                copy.copies += s.copies;
                originalScratchCards.put(i, copy);
            }
        }

        // The total number of cards is the sum of all copies
        for (Map.Entry<Integer, ScratchCard> entry : originalScratchCards.entrySet()) {
            ScratchCard s = entry.getValue();
            totalCards += s.copies;
        }

        return totalCards;
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

    public static int matchingNumbers(int[] winningNumbers, int[] myNumbers) {
        int matches = 0;
        for (int i = 0; i < winningNumbers.length; i++) {
            for (int j = 0; j < myNumbers.length; j++) {
                if (winningNumbers[i] == myNumbers[j]) {
                    matches++;
                }
            }
        }

        return matches;
    }



        public static class ScratchCard {
            int cardNumber;
            int matches;

            int copies;
            public ScratchCard() {}

            public ScratchCard(int cardNumber) {
                this.cardNumber = cardNumber;
                this.copies = 1;
            }

            public String toString() {
                return "Card: " + cardNumber + " has " + matches + " matche(s) and " + copies + " copies";
            }


        }
}
