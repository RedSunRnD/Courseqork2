package com.example.coursework.services;

import com.example.coursework.domain.Question;
import java.util.*;

public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) throws IllegalArgumentException {
        Collection<Question> allQuestions = questionService.getAllQuestions();
        if (amount > allQuestions.size()) {
            throw new IllegalArgumentException("Доступных вопросов меньше запрашиваемого количества");
        }
        List<Question> questionsList = new ArrayList<>(allQuestions);
        Set<Question> uniqueQuestions = new HashSet<>();
        Random random = new Random();
        while (uniqueQuestions.size() < amount) {
            Question randomQuestion = questionsList.get(random.nextInt(questionsList.size()));
            uniqueQuestions.add(randomQuestion);
        }
        return uniqueQuestions;
    }
}