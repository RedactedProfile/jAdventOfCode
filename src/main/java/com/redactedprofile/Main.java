package com.redactedprofile;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArgumentParser argumentParser = ArgumentParsers.newFor("aco2023").build()
                .defaultHelp(true)
                .description("AOC2023 Runner");
        argumentParser.addArgument("-v", "--version");
        argumentParser.addArgument("-d", "--day");
        argumentParser.addArgument("-p", "--part");

        Namespace ns = null;
        try {
            ns = argumentParser.parseArgs(args);
        } catch (ArgumentParserException e) {
            argumentParser.handleError(e);
            System.exit(1);
        }

        if (ns.getString("day") != null) {
            State.Day = ns.getString("day");
        }
        if (ns.getString("part") != null) {
            State.Part = ns.getString("part");
        }

        Scanner scanner = new Scanner(System.in);
        if(State.Day.isBlank()) {
            System.out.println("Which day? ");
            State.Day = scanner.nextLine().trim();
        }
        if(State.Part.isBlank()) {
            System.out.println("Which part? ");
            State.Part = scanner.nextLine().trim();
        }

        System.out.printf("We're executing day %s and part %s", State.Day, State.Part);
    }
}