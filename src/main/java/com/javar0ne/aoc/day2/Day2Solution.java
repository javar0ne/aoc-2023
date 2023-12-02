package com.javar0ne.aoc.day2;

import com.javar0ne.aoc.base.DaySolution;
import com.javar0ne.aoc.base.FirstQuestion;
import com.javar0ne.aoc.base.SecondQuestion;

public class Day2Solution extends DaySolution<FirstQuestion, SecondQuestion> {
    public Day2Solution() {
        super(2);
    }

    @Override
    protected FirstQuestion getFirstQuestionSolver() {
        return new Day2First(day);
    }

    @Override
    protected SecondQuestion getSecondQuestionSolver() {
        return new Day2Second(day);
    }
}
