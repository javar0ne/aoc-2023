package com.javar0ne.aoc;

import com.javar0ne.aoc.day1.Day1Solution;

public class Main {
    public static void main(String[] args) {
        Day1Solution day1 = new Day1Solution();

        day1.solveSecond()
            .ifPresent(System.out::println);
    }
}
