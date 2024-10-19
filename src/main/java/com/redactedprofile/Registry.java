package com.redactedprofile;

import java.util.Map;

public class Registry {
    public static Map<String, Map<String, IAOCDay>> days = Map.ofEntries(
            Map.entry("2015", Map.ofEntries(
                    Map.entry("1", new com.redactedprofile.Y2015.days.Day1())
            )),
            Map.entry("2023", Map.ofEntries(
                    Map.entry("1", new com.redactedprofile.Y2023.days.Day1())
            )),
            Map.entry("2024", Map.ofEntries(
                    Map.entry("1", new com.redactedprofile.Y2024.days.Day1())
            ))
    );
}
