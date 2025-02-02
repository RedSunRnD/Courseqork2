package com.example.coursework.controller;

import com.example.coursework.domain.Question;
import com.example.coursework.services.ExaminerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exam/get")
public class ExamController {
    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/{amount}")
    public List<Question> getQuestions(@PathVariable int amount) {
        try {
            return examinerService.getQuestions(amount);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}