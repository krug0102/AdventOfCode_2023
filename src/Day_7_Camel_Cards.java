import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day_7_Camel_Cards {

    Character[] cards = new Character[] {'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2'};

    static Pattern p = Pattern.compile("([A-Z0-9]{5}) (\\d+)");

    public static void main(String[] args) {
        prompt();
    }

    public static void prompt() {
        System.out.println("Please provide the hands and corresponding bids.");
        Scanner s = new Scanner(System.in);

        String fileName = s.nextLine();
        System.out.println("The total winnings of the provided hands is: " + totalWinnings(fileName));
    }


    public static int totalWinnings(String fileName) {
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

                    hands.add(new CamelCardHand(cards, bid));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        for (CamelCardHand hand : hands) {
            System.out.println(hand.toString());
        }

        //hands.sort(new HandComparator());

        for (int i = 0; i < hands.size(); i++) {
            result = result + ((hands.size() - i) * (hands.get(i).bid));
        }


        return result;
    }


    public static class CamelCardHand {
        char[] cards;
        int bid;


        public CamelCardHand() {}

        public CamelCardHand(char[] cards, int bid) {
            this.cards = cards;
            this.bid = bid;
        }

        public String toString() {
            String cards = new String(this.cards);
            return "Hand: " + cards + " Bid: " + this.bid;
        }
    }

    public static class HandComparator implements Comparator<CamelCardHand> {

        @Override
        public int compare(CamelCardHand o1, CamelCardHand o2) {
            for (int i = 0; i < o1.cards.length; i++) {

            }
            return 0;
        }
    }
}
