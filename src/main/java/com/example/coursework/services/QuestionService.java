package com.example.coursework.services;

import com.example.coursework.domain.Question;

import java.util.List;

public interface QuestionService {
    void addQuestion(Question question);
    void removeQuestion(Question question);
    List<Question> getAllQuestions();
}