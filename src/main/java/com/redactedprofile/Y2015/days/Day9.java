package com.redactedprofile.Y2015.days;

import com.redactedprofile.AOCDay;

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

    int calculateFactor(int input) {
        int factor = input;
        for(int i = input - 1; i > 0; i--) {
            factor *= i;
        }

        return factor;
    }

    List<List<String>> generateRoutes(
            List<String> endpoints
    ) {
        // implementation of Heap's Algorithm
        List<List<String>> routes = new ArrayList<>();
        String[] array = endpoints.toArray(new String[0]);
        generateRoutes(array, array.length, routes);
        return routes;
    }

    void generateRoutes(
            String[] array,
            int size,
            List<List<String>> routes
    ) {
        if(size == 1) {
            List<String> permutation = new ArrayList<>();
            for(String s : array) {
                permutation.add(s);
            }
            routes.add(permutation);
            return;
        }

        for(int i = 0; i < size; i++) {
            generateRoutes(array, size - 1, routes);
            if(size % 2 == 1) {
                swap(array, 0, size - 1);
            } else {
                swap(array, i, size - 1);
            }
        }
    }

    private void swap(String[] array, int i, int j) {
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    List<List<String>> collectRoutes(Set<String> endpoints) {
//        int factor = calculateFactor(endpoints.size());

        List<String> endpointList = endpoints.stream().toList();
        List<List<String>> routes = generateRoutes(endpointList);

        return routes;
    }

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

//        List<List<String>> navs = List.of(tok1, tok2, tok3);
        List<List<String>> navs = List.of(List.of("Vancouver", "Toronto"), List.of("Montreal", "Victoria"), List.of("Edmonton", "Calgary"), List.of("Prince George", "Saskatoon"));
        Set<String> endpoints = collectEndpoints(navs);
//        if(endpoints.size() != 3) throw new AssertionError("Assertion 4 failed: endpoints.size() != 3: " + endpoints.size());

        var routes = collectRoutes(endpoints);

//        System.out.println(routes);
        System.out.println("Factorial of: " + calculateFactor(endpoints.size()));
        System.out.println("Generated routes: " + routes.size());
    }
}
