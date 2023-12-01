package com.javar0ne.aoc;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public abstract class QuestionSolver {
    private final Integer day;
    private final EQuestionType questionType;

    protected QuestionSolver(Integer day, EQuestionType questionType) {
        this.day = day;
        this.questionType = questionType;
    }


    protected List<String> getInput() {
        String filePath = "classpath:input/day" +
            day +
            "/" +
            questionType.getText();

        Path path = Paths.get(filePath);
        List<String> content = Files.readAllLines(path);
    }

    protected abstract Optional<?> solve();
}
