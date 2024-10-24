package com.redactedprofile;

import java.util.Map;

public class Registry {
    public static Map<String, Map<String, Class<? extends IAOCDay>>> days = Map.ofEntries(
            Map.entry("2015", Map.ofEntries(
                    Map.entry("1", com.redactedprofile.Y2015.days.Day1.class),
                    Map.entry("2", com.redactedprofile.Y2015.days.Day2.class),
                    Map.entry("3", com.redactedprofile.Y2015.days.Day3.class),
                    Map.entry("4", com.redactedprofile.Y2015.days.Day4.class),
                    Map.entry("6", com.redactedprofile.Y2015.days.Day6.class)
            )),
            Map.entry("2023", Map.ofEntries(
                    Map.entry("1", com.redactedprofile.Y2023.days.Day1.class)
            )),
            Map.entry("2024", Map.ofEntries(
                    Map.entry("1", com.redactedprofile.Y2024.days.Day1.class)
            ))
    );
}
