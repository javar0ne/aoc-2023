package com.javar0ne.aoc.day3;

import com.javar0ne.aoc.base.DaySolution;
import com.javar0ne.aoc.base.FirstQuestion;
import com.javar0ne.aoc.base.SecondQuestion;

public class Day3Solution extends DaySolution<FirstQuestion, SecondQuestion> {
    public Day3Solution() {
        super(3);
    }

    @Override
    protected FirstQuestion getFirstQuestionSolver() {
        return new Day3First(day);
    }

    @Override
    protected SecondQuestion getSecondQuestionSolver() {
        return new Day3Second(day);
    }
}
