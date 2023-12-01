package com.javar0ne.aoc.day1;

import com.javar0ne.aoc.base.DaySolution;
import com.javar0ne.aoc.base.FirstQuestion;
import com.javar0ne.aoc.base.SecondQuestion;

public class Day1Solution extends DaySolution<FirstQuestion, SecondQuestion> {
    public Day1Solution() {
        super(1);
    }

    @Override
    protected FirstQuestion getFirstQuestionSolver() {
        return new Day1First(day);
    }

    @Override
    protected SecondQuestion getSecondQuestionSolver() {
        return new Day1Second(day);
    }
}
