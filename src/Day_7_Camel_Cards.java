import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day_7_Camel_Cards {
    static Pattern p = Pattern.compile("([A-Z0-9]{5}) (\\d+)");

    public static void main(String[] args) {
        prompt();
    }

    public static void prompt() {
        System.out.println("Please provide the hands and corresponding bids.");
        Scanner s = new Scanner(System.in);

        String fileName = s.nextLine();
        System.out.println("Part 1: \nThe total winnings of the provided hands is: " + part1(fileName));

        System.out.println("Part 2: \nThe total winnings of the provided hands is: " + part2(fileName));
    }


    public static int part1(String fileName) {
        int result = 0;
        ArrayList<CamelCardHand> hands = new ArrayList<>();

        try {
            File CamelCardHands = new File(fileName);
            Scanner reader = new Scanner(CamelCardHands);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                Matcher m = p.matcher(line);
                if (m.find()) {
                    char[] cards = m.group(1).toCharArray();
                    int bid = Integer.parseInt(m.group(2));
                    int handType = handType(cards);

                    hands.add(new CamelCardHand(cards, bid, handType));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        hands.sort(new HandComparator());

        for (int i = 0; i < hands.size(); i++) {
            result = result + ((i+1) * (hands.get(i).bid));
        }


        return result;
    }

    public static int handType(char[] hand) {
        int handType = 0;
        HashMap<Character, Integer> cardCount = new HashMap<>();
        for (int i = 0; i < hand.length; i++) {
            char c = hand[i];
            if (!cardCount.containsKey(c)) {
                cardCount.put(c, 1);
            } else {
                Integer x = cardCount.get(c);
                cardCount.replace(c, x + 1);
            }
        }

        if (cardCount.size() == 1) {
            handType = 1;
        }
        // Full House and Four of a Kind have the same number of entries
        // Full House will have 3 of one and 2 of another
        // Four of a Kind will have 4 of one and 1 of another
        if (cardCount.size() == 2) {
            int max = 0;
            for (Map.Entry<Character, Integer> e : cardCount.entrySet()) {
                if (e.getValue() > max) {
                    max = e.getValue();
                }
            }
            if (max == 4) { // four of a kind
                handType = 2;
            } else {  // full house
                handType = 3;
            }
        }
        // Three of a kind and two pair have the same number of entries
        // Three of a Kind will have 3 of one card, with the other two being different
        // Two Pair will have 2 of one card, 2 of another, and 1 other card
        if (cardCount.size() == 3) {
            int max = 0;
            for (Map.Entry<Character, Integer> e : cardCount.entrySet()) {
                if (e.getValue() > max) {
                    max = e.getValue();
                }
            }
            if (max == 3) {  // three of a kind
                handType = 4;
            } else {  // two pair
                handType = 5;
            }
        }
        if (cardCount.size() == 4) {
            handType = 6;
        }
        if (cardCount.size() == 5) {
            handType = 7;
        }

        return handType;
    }

    public static int part2(String fileName) {
        int result = 0;
        ArrayList<JokerHand> hands = new ArrayList<>();

        try {
            File CamelCardHands = new File(fileName);
            Scanner reader = new Scanner(CamelCardHands);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                Matcher m = p.matcher(line);
                if (m.find()) {
                    JokerHand j = new JokerHand();
                    j.cards = m.group(1).toCharArray();
                    j.bid = Integer.parseInt(m.group(2));

                    handTypeJoker(j);

                    hands.add(j);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        hands.sort(new JokerHandComparator());

        for (int i = 0; i < hands.size(); i++) {
            result = result + ((i+1) * (hands.get(i).bid));
        }


        return result;
    }

    public static void handTypeJoker(JokerHand j) {
        char highestCard = 0;
        char mostFrequentCard = 0;
        HashMap<Character, Integer> cardCount = new HashMap<>();
        char[] hand = j.cards;
        for (int i = 0; i < hand.length; i++) {
            char c = hand[i];
            if (!cardCount.containsKey(c)) {
                cardCount.put(c, 1);
            } else {
                int x = cardCount.get(c);
                cardCount.put(c, x+1);
            }

            if (highestCard == 0) {
                highestCard = c;
            } else {
                if (cardValueJoker(highestCard) < cardValueJoker(c)) {
                    highestCard = c;
                }
            }
        }

        if (cardCount.containsKey('J')) {
            char[] jokerHand = new char[5];

            for (Map.Entry<Character, Integer> e : cardCount.entrySet()) {
                if (mostFrequentCard == 0) {
                    mostFrequentCard = e.getKey();
                } else {
                    if (e.getValue() > cardCount.get(mostFrequentCard)) {
                        mostFrequentCard = e.getKey();
                    }
                }
            }

            // TODO: Can take this logic to fill the jokerHand into its own function that takes
            // the original and the character to replace 'J' with.
            if (highestCard == mostFrequentCard) {
                for (int i = 0; i < jokerHand.length; i++) {
                    char c = hand[i];
                    if (c == 'J') {
                        jokerHand[i] = highestCard;
                    } else {
                        jokerHand[i] = c;
                    }
                }
                j.handType = handType(jokerHand);
                j.jokerHand = jokerHand;
            } else {

                //TODO: Gets the right answer for the test case, but answer is too low on read input.
                // This is due to not considering all possible combinations for the "hand" with joker replacement
                if (cardCount.get(highestCard) > cardCount.get(mostFrequentCard)) {
                    for (int i = 0; i < jokerHand.length; i++) {
                        char c = hand[i];
                        if (c == 'J') {
                            jokerHand[i] = highestCard;
                        }
                    }
                    j.handType = handType(jokerHand);
                    j.jokerHand = jokerHand;
                }
                if (cardCount.get(highestCard) < cardCount.get(mostFrequentCard)) {
                    for (int i = 0; i < jokerHand.length; i++) {
                        char c = hand[i];
                        if (c == 'J') {
                            jokerHand[i] = mostFrequentCard;
                        }
                    }
                    j.handType = handType(jokerHand);
                    j.jokerHand = jokerHand;
                }
            }
        }
    }

    //TODO: Will have to update the compare method
    public static int cardValueJoker(char c) {
        int result = 0;
        switch (c) {
            case 'A':
                result = 13;
                break;
            case 'K':
                result = 12;
                break;
            case 'Q':
                result = 11;
                break;
            case 'T':
                result = 10;
                break;
            case '9':
                result = 9;
                break;
            case '8':
                result = 8;
                break;
            case '7':
                result = 7;
                break;
            case '6':
                result = 6;
                break;
            case '5':
                result = 5;
                break;
            case '4':
                result = 4;
                break;
            case '3':
                result = 3;
                break;
            case '2':
                result = 2;
                break;
            case 'J':
                result = 1;
                break;
        }
        return result;
    }


    public static class JokerHand {
        int handType;
        int bid;
        char[] cards;
        char[] jokerHand;

        public JokerHand() {}

        public JokerHand(char[] cards, int bid) {
            this.cards = cards;
            this.bid = bid;
        }

        public JokerHand(int handType, char[] jokerHand) {
            this.handType = handType;
            this.jokerHand = jokerHand;
        }
    }

    public static class JokerHandComparator implements Comparator<JokerHand> {

        /*
            TODO: The 'if' statements are weird, since I have made it so that the "higher" the 'handType',
             the worse the hand, and I want the worse hands to come first in the sorted list
         */
        @Override
        public int compare(JokerHand o1, JokerHand o2) {
            if (o1.handType > o2.handType) {
                return 1;
            }
            if (o1.handType < o2.handType) {
                return -1;
            } else {
                for (int i = 0; i < o1.cards.length; i++) {
                    char o1Card = o1.cards[i];
                    char o2Card = o2.cards[i];
                    if (cardValueJoker(o1Card) > cardValueJoker(o2Card)) {
                        return 1;
                    }
                    if (cardValueJoker(o1Card) < cardValueJoker(o2Card)) {
                        return -1;
                    }
                }
            }
            return 0;
        }
    }


    public static class CamelCardHand {
        char[] cards;
        int bid;
        int handType;



        public CamelCardHand() {}

        public CamelCardHand(char[] cards, int bid, int handType) {
            this.cards = cards;
            this.bid = bid;
            this.handType = handType;
        }


        public String toString() {
            String cards = new String(this.cards);
            return "Hand: " + cards + " Bid: " + this.bid + " Hand Type: " + this.handType;
        }
    }

    public static class HandComparator implements Comparator<CamelCardHand> {

        /*
            TODO: The 'if' statements are weird, since I have made it so that the "higher" the 'handType',
             the worse the hand, and I want the worse hands to come first in the sorted list
         */
        @Override
        public int compare(CamelCardHand o1, CamelCardHand o2) {
            if (o1.handType < o2.handType) {
                return 1;
            }
            if (o1.handType > o2.handType) {
                return -1;
            } else {
                for (int i = 0; i < o1.cards.length; i++) {
                    char o1Card = o1.cards[i];
                    char o2Card = o2.cards[i];
                    if (calculateCardStrength(o1Card) < calculateCardStrength(o2Card)) {
                        return 1;
                    }
                    if (calculateCardStrength(o1Card) > calculateCardStrength(o2Card)) {
                        return -1;
                    }
                }
            }
            return 0;
        }


        public int calculateCardStrength(char card) {
            int result = 0;
            switch (card) {
                case 'A':
                    result = 1;
                    break;
                case 'K':
                    result = 2;
                    break;
                case 'Q':
                    result = 3;
                    break;
                case 'T':
                    result = 4;
                    break;
                case '9':
                    result = 5;
                    break;
                case '8':
                    result = 6;
                    break;
                case '7':
                    result = 7;
                    break;
                case '6':
                    result = 8;
                    break;
                case '5':
                    result = 9;
                    break;
                case '4':
                    result = 10;
                    break;
                case '3':
                    result = 11;
                    break;
                case '2':
                    result = 12;
                    break;
                case 'J':
                    result = 13;
                    break;
            }
            return result;
        }

    }
}
