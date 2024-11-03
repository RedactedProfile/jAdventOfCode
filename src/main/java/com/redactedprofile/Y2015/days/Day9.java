package com.redactedprofile.Y2015.days;

import com.redactedprofile.AOCDay;

import javax.swing.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day9 extends AOCDay {

    private static final int NAV_FROM = 0;
    private static final int NAV_TO = 1;
    private static final int NAV_DISTANCE = 2;

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

    String pickNextRoute(List<String> endpoints, Set<String> chosen) {
        while(true) {

        }
    }

    boolean generateRoute(
            ArrayList<ArrayList<String>> routes,            // global container for all generates routes
            ArrayList<String> routeContainer,               // active route being built
            ArrayList<String> endpoints)                          // all available endpoints
    {
        Collections.shuffle(endpoints);
        // Pick the next string
        boolean foundInRoute = false;
        for(String endpoint : endpoints) {
            if(!routeContainer.contains(endpoint)) {
                foundInRoute = true;
                routeContainer.add(endpoint);

                generateRoute(routes, routeContainer, endpoints);
                break;
            }
        }

        if(!foundInRoute) { // this means we reached the end and there's no more to choose
            // now check to see if we've already got this one in the global registry
            if(!routes.contains(routeContainer)) {
                routes.add(routeContainer);
                return true;
            }
        }

        return false; // let us loop again
    }

    ArrayList<ArrayList<String>> collectRoutes(Set<String> endpoints) {
        int factor = calculateFactor(endpoints.size());

        List<String> endpointList = endpoints.stream().toList();
        ArrayList<String> endpointArray = new ArrayList<>(endpointList);
        ArrayList<ArrayList<String>> routes = new ArrayList<>(); // reserve this much memory so we don't need to resize the array around heap so much, which is costly

        for (int i = 0; i < factor; i++) {

            ArrayList<String> routeContainer = new ArrayList<>(endpointList.size());
            while(!generateRoute(routes, routeContainer, endpointArray)) {
                // the functions doing all the work
                System.out.println(routeContainer);
            }


            System.out.println(routeContainer);

//            Set<String> route = new HashSet<>(endpoints.size());
//            for(int j = 0; j < endpointList.size(); j++) {
//                if(!route.contains(endpointList.get(j))) {
//                    route.add(endpointList.get(j));
//                }
//            }
//            for (String endpoint : endpoints) {
//                route.add(endpoint);
//            }

//            System.out.println("Route: " + route);
//
//            routes.add(route);
        }

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

        List<List<String>> navs = List.of(tok1, tok2, tok3);
        Set<String> endpoints = collectEndpoints(navs);
        if(endpoints.size() != 3) throw new AssertionError("Assertion 4 failed: endpoints.size() != 3: " + endpoints.size());

        var routes = collectRoutes(endpoints);
    }
}
