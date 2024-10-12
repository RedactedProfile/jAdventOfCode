package com.redactedprofile;

import com.redactedprofile.Y2024.days.Day1;

import java.util.Map;

public class Registry {
    public static Map<String, Map<String, AOCDay>> days = Map.ofEntries(
            Map.entry("2024", Map.ofEntries(
                    Map.entry("1", new Day1())
            ))
    );
}
