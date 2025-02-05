package com.example.coursework.services;

import com.example.coursework.domain.Question;
import java.util.Collection;
public interface QuestionService {
    void addQuestion(String question, String answer);
    void addQuestion(Question question);
    void removeQuestion(Question question);
    Collection<Question> getAllQuestions();
    Question getRandomQuestion();
}