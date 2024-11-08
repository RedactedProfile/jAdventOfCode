package com.redactedprofile.Y2015.days;

import com.redactedprofile.AOCDay;
import com.redactedprofile.Shared.Algorithms.Factorial;
import com.redactedprofile.Shared.Algorithms.Heaps;

import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day9 extends AOCDay {

    private static final int NAV_FROM = 0;
    private static final int NAV_TO = 1;
    private static final int NAV_DISTANCE = 2;

    private record RouteDistance(String from, String to, int distance) {}


    @Override
    public String getPuzzleInputFilePath() {
        return "2015/09.txt";
    }

    /**
     * This puzzle is all about finding the shortest path between various locations
     */
    @Override
    public void easy() {
        // Collect navigation endpoints from input and create a distance map out of them
        List<List<String>> navs = new ArrayList<>();
        getInputLinesByLine(line -> tokenize(line, (navs::add)));
        List<RouteDistance> routeDistanceMap = generateRouteDistanceMap(navs);

        // Out of the distance maps we create a unique list of all possible endpoints
        Set<String> endpoints = collectEndpoints(routeDistanceMap);

        // So that we can generate all possible permutations of those endpoints into travel-able routes
        List<List<String>> routes = collectRoutes(endpoints);

        // With all the routes generated, and the map of distances between every two locations, we can now tally up each route
        Map<List<String>, Integer> routeDistances = calculateRouteDistances(routes, routeDistanceMap);

        // And then we can find the route with the smalled distance, which is what the puzzle calls for
        RouteWithDistance shortestRoute = findShortestRoute(routeDistances);

        System.out.println("The route with the shortest distance is " + shortestRoute.distance + " by navigating through " + shortestRoute.route);
    }


    /**
     * This puzzle is all about finding the longest path between various locations
     */
    @Override
    public void hard() {
        // Collect navigation endpoints from input and create a distance map out of them
        List<List<String>> navs = new ArrayList<>();
        getInputLinesByLine(line -> tokenize(line, (navs::add)));
        List<RouteDistance> routeDistanceMap = generateRouteDistanceMap(navs);

        // Out of the distance maps we create a unique list of all possible endpoints
        Set<String> endpoints = collectEndpoints(routeDistanceMap);

        // So that we can generate all possible permutations of those endpoints into travel-able routes
        List<List<String>> routes = collectRoutes(endpoints);

        // With all the routes generated, and the map of distances between every two locations, we can now tally up each route
        Map<List<String>, Integer> routeDistances = calculateRouteDistances(routes, routeDistanceMap);

        // And then we can find the route with the smalled distance, which is what the puzzle calls for
        RouteWithDistance longestRoute = findLongestRoute(routeDistances);

        System.out.println("The route with the longest distance is " + longestRoute.distance + " by navigating through " + longestRoute.route);
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

    private List<RouteDistance> generateRouteDistanceMap(List<List<String>> navs) {
        List<RouteDistance> routeDistances = new ArrayList<>(navs.size());
        for(List<String> nav : navs) {
            routeDistances.add(new RouteDistance(nav.get(NAV_FROM), nav.get(NAV_TO), Integer.parseInt(nav.get(NAV_DISTANCE))));
        }
        return routeDistances;
    }

    /**
     * Generate all possible permutations of the endpoints
     * @param endpoints
     * @return
     */
    List<List<String>> collectRoutes(Set<String> endpoints) {
        List<List<Object>> routeObjects = Heaps.generatePermutation(Arrays.asList(endpoints.toArray()));
        ArrayList<List<String>> routes = new ArrayList<>(routeObjects.size());
        for (Object _route : routeObjects) {
            routes.add((List<String>) _route);
        }
        return routes;
    }

    /**
     * Get a list of unique values
     * @param navs
     * @return
     */
    Set<String> collectEndpoints(List<RouteDistance> navs) {
        Set<String> endpoints = new HashSet<>();

        for (RouteDistance nav : navs) {
            endpoints.addAll(List.of(nav.from, nav.to));
        }

        return endpoints;
    }

    private Integer calculateRouteDistance(List<String> route, List<RouteDistance> routeDistanceMap) {
        int distance = 0;
        // Step through each endpoint, getting the distance between the current and the next (if there is one)
        for(int i = 0; i < route.size() - 1; i++) {
            String from  = route.get(i),
                   to = route.get(i+1);

            // look up the distance map
            for(RouteDistance nav : routeDistanceMap) {
                // Bi-directional lookup as they'd be the same distance
                if(nav.from.equals(from) && nav.to.equals(to) || nav.from.equals(to) && nav.to.equals(from)) {
                    distance += nav.distance;
                    break;
                }
            }
        }

        return distance;
    }

    private Map<List<String>, Integer> calculateRouteDistances(List<List<String>> routes, List<RouteDistance> routeDistanceMap) {
        Map<List<String>, Integer> routeDistances = new HashMap<>(routes.size());

        for(List<String> route : routes) {
            routeDistances.put(route, calculateRouteDistance(route, routeDistanceMap));
        }

        return routeDistances;
    }

    private record RouteWithDistance(String route, int distance) {}
    private RouteWithDistance findExtremeRoute(Map<List<String>, Integer> routes, boolean reverse) {
        RouteWithDistance shortestRoute = new RouteWithDistance(null, 0);

        // Going to try this PriorityQueue thing, no idea how good it is
        PriorityQueue<Map.Entry<List<String>, Integer>> pq =
                !reverse ? new PriorityQueue<>(routes.size(), Map.Entry.comparingByValue()) :
                           new PriorityQueue<>(routes.size(), Collections.reverseOrder(Map.Entry.comparingByValue()));

        pq.addAll(routes.entrySet()); // By adding all the elements here, it runs an internal comparison set here ^^^^
        Map.Entry<List<String>, Integer> entry = pq.poll();  // Polling takes, in this instance, the smallest -value-. We should while over it polling for each entry.
        shortestRoute = new RouteWithDistance(entry.getKey().toString(), entry.getValue());

        return shortestRoute;
    }

    private RouteWithDistance findShortestRoute(Map<List<String>, Integer> routes) {
        return findExtremeRoute(routes, false);
    }

    private RouteWithDistance findLongestRoute(Map<List<String>, Integer> routes) {
        return findExtremeRoute(routes, true);
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
        List<RouteDistance> routeDistanceMap = generateRouteDistanceMap(navs);

        Set<String> endpoints = collectEndpoints(routeDistanceMap);
        if(endpoints.size() != 3) throw new AssertionError("Assertion 4 failed: endpoints.size() != 3: " + endpoints.size());

        var routes = collectRoutes(endpoints);

        int factorial = Factorial.fromInt(endpoints.size());
        if(routes.size() != factorial) throw new AssertionError("Assertion 5 failed: routes.size() != "+factorial+": " + routes.size());

        Map<List<String>, Integer> routeDistances = calculateRouteDistances(routes, routeDistanceMap);
        if(routeDistances.size() != 6) throw new AssertionError("Assertion 6 failed: routeDistances.size() != 6: " + routeDistances.size());

        RouteWithDistance shortestRoute = findShortestRoute(routeDistances);
        if(shortestRoute.distance != 605) throw new AssertionError("Assertion 7 failed: shortestRoute.distance != 605: " + shortestRoute.distance);

        RouteWithDistance longestRoute = findLongestRoute(routeDistances);
        if(longestRoute.distance != 982) throw new AssertionError("Assertion 8 failed: longestRoute.distance != 982: " + longestRoute.distance);
    }


}
