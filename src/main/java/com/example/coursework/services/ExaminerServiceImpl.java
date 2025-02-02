package com.example.coursework.services;

import com.example.coursework.domain.Question;

import java.util.*;

public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public List<Question> getQuestions(int amount) throws IllegalArgumentException {
        List<Question> allQuestions = questionService.getAllQuestions();
        if (amount > allQuestions.size()) {
            throw new IllegalArgumentException("Requested more questions than available");
        }

        Set<Question> uniqueQuestions = new HashSet<>();
        Random random = new Random();

        while (uniqueQuestions.size() < amount) {
            Question randomQuestion = allQuestions.get(random.nextInt(allQuestions.size()));
            uniqueQuestions.add(randomQuestion);
        }

        return new ArrayList<>(uniqueQuestions);
    }
}