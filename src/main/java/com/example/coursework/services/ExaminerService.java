package com.example.coursework.services;

import com.example.coursework.domain.Question;

import java.util.List;

public interface ExaminerService {
    List<Question> getQuestions(int amount) throws IllegalArgumentException;
}