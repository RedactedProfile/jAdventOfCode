package com.redactedprofile.Y2015.days;

import com.redactedprofile.AOCDay;

public class Day5 extends AOCDay {

    private enum CLASSIFICATION { NAUGHTY, NICE }

    private CLASSIFICATION evaluateNiceRequirements(String string) {
        var out = CLASSIFICATION.NAUGHTY;



        return out;
    }

    private boolean hasOneLetter(Character[] needle, String haystack) {
        for (int i = 0; i < haystack.length(); i++) {
            if(haystack.charAt(i) == needle[0]) {}
        }
        return false;
    }

    public void assertTests() {
        String strTest1 = "ugknbfddgicrmopn",
                strTest2 = "aaa",
                strTest3 = "jchzalrnumimnmhp",
                strTest4 = "haegwjzuvuyypxyu",
                strTest5 = "dvszwmarrgswjxmb";
        CLASSIFICATION expectedTest1 = CLASSIFICATION.NICE,
                expectedTest2 = CLASSIFICATION.NICE,
                expectedTest3 = CLASSIFICATION.NAUGHTY,
                expectedTest4 = CLASSIFICATION.NAUGHTY,
                expectedTest5 = CLASSIFICATION.NAUGHTY;

        if(!evaluateNiceRequirements(strTest1).equals(expectedTest1))
            throw new AssertionError("Test 1 Failed: Expected " + expectedTest1 + " but got " + evaluateNiceRequirements(strTest1));
        if(!evaluateNiceRequirements(strTest2).equals(expectedTest2))
            throw new AssertionError("Test 2 Failed: Expected " + expectedTest2 + " but got " + evaluateNiceRequirements(strTest2));
        if(!evaluateNiceRequirements(strTest3).equals(expectedTest3))
            throw new AssertionError("Test 3 Failed: Expected " + expectedTest3 + " but got " + evaluateNiceRequirements(strTest3));
        if(!evaluateNiceRequirements(strTest4).equals(expectedTest4))
            throw new AssertionError("Test 4 Failed: Expected " + expectedTest4 + " but got " + evaluateNiceRequirements(strTest4));
        if(!evaluateNiceRequirements(strTest5).equals(expectedTest5))
            throw new AssertionError("Test 5 Failed: Expected " + expectedTest5 + " but got " + evaluateNiceRequirements(strTest5));

    }

}
