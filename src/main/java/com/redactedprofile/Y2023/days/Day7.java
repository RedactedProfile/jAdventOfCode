package com.redactedprofile.Y2023.days;

import com.redactedprofile.AOCDay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 extends AOCDay {



    Map<String, Integer> Cards = Map.ofEntries(
            Map.entry("2", 1),
            Map.entry("3", 2),
            Map.entry("4", 3),
            Map.entry("5", 4),
            Map.entry("6", 5),
            Map.entry("7", 6),
            Map.entry("8", 7),
            Map.entry("9", 8),
            Map.entry("T", 9),
            Map.entry("J", 10),
            Map.entry("Q", 11),
            Map.entry("K", 12),
            Map.entry("A", 13)
    );

    Map<String, Integer> HandStrength = Map.ofEntries(
            Map.entry("HighCard", 1),
            Map.entry("OnePair", 2),
            Map.entry("TwoPair", 3),
            Map.entry("ThreeKind", 4),
            Map.entry("FullHouse", 5),
            Map.entry("FourKind", 6),
            Map.entry("FiveKind", 7)
    );

    private class Hand {
        public String[] cards = new String[5];
        public int bet = 0;

        public Hand() {
            // Create empty hand with nothing in it
        }

        public Hand(List<Object> input) {
            parseInput(input);
        }

        public void parseInput(List<Object> input) {
            assert input.size() == 5;
            cards[0] = (String) input.get(0).toString();
            cards[1] = (String) input.get(1).toString();
            cards[2] = (String) input.get(2).toString();
            cards[3] = (String) input.get(3).toString();
            cards[4] = (String) input.get(4).toString();

            List<Object> rawBet = input.subList(5, input.size());

            StringBuilder sb = new StringBuilder();
            for(Object o : rawBet) {
                sb.append(o);
            }

            bet = Integer.parseInt(sb.toString());
        }
    }

    private List<Object> tokenize(String line) {
        Pattern pattern = Pattern.compile("[(A-Z0-9)+]|\\d+");
        Matcher matcher = pattern.matcher(line);
        List<String> tokens = new ArrayList<>();
        while(matcher.find()) {
            tokens.add(matcher.group());
        }
        List<Object> parsedTokens = new ArrayList<>();
        for (String token : tokens) {
            if(token.matches("\\d+")) {
                parsedTokens.add(Integer.parseInt(token));
            } else {
                parsedTokens.add(token);
            }
        }

        return parsedTokens;
    }

    private void evaluateHand(Hand hand) {

    }

    @Override
    public void assertTests() {
        String str1 = "32T3K 765",
                str2 = "T55J5 684",
                str3 = "KK677 28",
                str4 = "KTJJT 220",
                str5 = "QQQJA 483";

        List<Object> tokened1 = tokenize(str1),
                     tokened2 = tokenize(str2),
                     tokened3 = tokenize(str3),
                     tokened4 = tokenize(str4),
                     tokened5 = tokenize(str5);

        System.out.println(tokened4);

        Hand hand1 = new Hand(tokened1),
             hand2 = new Hand(tokened2),
             hand3 = new Hand(tokened3),
             hand4 = new Hand(tokened4),
             hand5 = new Hand(tokened5);

        assert hand1.cards.length == 5 && hand2.cards.length == 5 && hand3.cards.length == 5;
        assert hand1.cards[0].equals("3");

        System.out.println(hand1.bet);

    }
}
