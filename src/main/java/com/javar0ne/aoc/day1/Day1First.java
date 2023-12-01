package com.javar0ne.aoc.day1;

import com.javar0ne.aoc.base.EQuestionType;
import com.javar0ne.aoc.base.FirstQuestion;
import com.javar0ne.aoc.base.QuestionSolver;

import java.util.Optional;

public class Day1First extends FirstQuestion {

    public Day1First(Integer day) {
        super(day);
    }

    @Override
    public Optional<?> solve() {
        int calibrationsSum = getInput()
            .stream()
            .map(calibration -> {
                StringBuilder number = new StringBuilder();

                for (char character : calibration.toCharArray()) {
                    if (Character.isDigit(character)) {
                        number.append(character);
                    }
                }

                return number.toString();
            })
            .mapToInt(number -> {
                String s = number.charAt(0) + "";

                if(number.length() > 1) {
                    s += number.charAt(number.length()-1);
                } else {
                    s += number.charAt(0);
                }

                return Integer.parseInt(s);
            })
            .sum();

        return Optional.of(calibrationsSum);
    }
}
