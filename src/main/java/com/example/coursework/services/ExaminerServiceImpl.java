package com.example.coursework.services;

import com.example.coursework.CourseworkApplication;
import com.example.coursework.domain.Question;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final CourseworkApplication.QuestionService questionService;

    public ExaminerServiceImpl(CourseworkApplication.QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > questionService.getAll().size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Количество запрашиваемых вопросов больше, чем количество доступных");
        }

        Set<Question> uniqueQuestions = new HashSet<>();
        while (uniqueQuestions.size() < amount) {
            Question randomQuestion = questionService.getRandomQuestion();
            uniqueQuestions.add(randomQuestion);
        }

        return uniqueQuestions;
    }
}