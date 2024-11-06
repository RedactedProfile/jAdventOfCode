package com.redactedprofile.Y2015.days;

import com.redactedprofile.AOCDay;
import com.redactedprofile.Shared.Algorithms.Factorial;
import com.redactedprofile.Shared.Algorithms.Heaps;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day9 extends AOCDay {

    private static final int NAV_FROM = 0;
    private static final int NAV_TO = 1;
    private static final int NAV_DISTANCE = 2;


    @Override
    public String getPuzzleInputFilePath() {
        return "2015/09.txt";
    }

    @Override
    public void easy() {



        System.out.println("Final floor: ");
    }





    Pattern instructionPattern = Pattern.compile(
            "([a-zA-Z]+)|([0-9]+)|(?: to )|(?: = )");

    private List<String> tokenize(String line) {
        Matcher matcher = instructionPattern.matcher(line);
        List<String> tokens = new ArrayList<>();
        while(matcher.find()) {
            if (matcher.group(1) != null || matcher.group(2) != null) {
                tokens.add(matcher.group());
            }
        }

        return tokens;
    }
    private void tokenize(String line, Consumer<List<String>> action) {
        action.accept(tokenize(line));
    }

    /**
     * Generate all possible permutations of the endpoints
     * @param endpoints
     * @return
     */
    List<List<String>> collectRoutes(Set<String> endpoints) {
        List<String> endpointList = endpoints.stream().toList();
        return Heaps.generatePermutation(endpointList);
    }

    /**
     * Get a list of unique values
     * @param navs
     * @return
     */
    Set<String> collectEndpoints(List<List<String>> navs) {
        Set<String> endpoints = new HashSet<>();

        for (List<String> nav : navs) {
            endpoints.addAll(List.of(nav.get(NAV_FROM), nav.get(NAV_TO)));
        }

        return endpoints;
    }


    @Override
    public void assertTests() {
        String strLoc1 = "London to Dublin = 464",
               strLoc2 = "London to Belfast = 518",
               strLoc3 = "Dublin to Belfast = 141";

        List<String> tok1 = tokenize(strLoc1),
                     tok2 = tokenize(strLoc2),
                     tok3 = tokenize(strLoc3);

        if(!tok1.get(NAV_FROM).equals("London")) throw new AssertionError("Assertion 1 failed: tok1[NAV_FROM] != London: " + tok1.get(NAV_FROM));
        if(!tok2.get(NAV_TO).equals("Belfast")) throw new AssertionError("Assertion 2 failed: tok2[NAV_TO] != Belfast: " + tok2.get(NAV_TO));
        if(!tok3.get(NAV_DISTANCE).equals("141")) throw new AssertionError("Assertion 3 failed: tok2[NAV_DIST] != 141: " + tok3.get(NAV_DISTANCE));

        List<List<String>> navs = List.of(tok1, tok2, tok3);
        Set<String> endpoints = collectEndpoints(navs);
        if(endpoints.size() != 3) throw new AssertionError("Assertion 4 failed: endpoints.size() != 3: " + endpoints.size());

        var routes = collectRoutes(endpoints);

        int factorial = Factorial.fromInt(endpoints.size());
        if(routes.size() != factorial) throw new AssertionError("Assertion 5 failed: routes.size() != "+factorial+": " + routes.size());


    }
}
