package com.javar0ne.aoc.base;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class QuestionSolver {
    private final Integer day;
    private final EQuestionType questionType;

    protected QuestionSolver(Integer day, EQuestionType questionType) {
        this.day = day;
        this.questionType = questionType;
    }


    protected List<String> getInput() {
        List<String>  content = new ArrayList<>();
        String filePath = "input/day" +
            day +
            "/" +
            questionType.getText() +
            ".txt";

        try {
            Path file = Paths.get(ClassLoader.getSystemResource(filePath).toURI());
            content.addAll(Files.readAllLines(file));
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(String.format("Exception while reading file %s", filePath));
        }

        return content;
    }

    protected abstract Optional<?> solve();
}
