package com.redactedprofile.Y2023.days;

import com.redactedprofile.AOCDay;

import java.util.HashMap;
import java.util.Map;

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

    @Override
    public void assertTests() {
        String str1 = "";
    }
}
