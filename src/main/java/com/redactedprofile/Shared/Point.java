package com.redactedprofile.Shared;

public class Point {
    public int x = 0, y = 0;
    public void reset() { x = y = 0; }
    public String encode() { return x + "," + y; }
}