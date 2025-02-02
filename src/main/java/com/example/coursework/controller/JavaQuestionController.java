package com.example.coursework.controller;

import com.example.coursework.domain.Question;
import com.example.coursework.services.JavaQuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    private final JavaQuestionService javaQuestionService;

    public JavaQuestionController(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @PostMapping("/add")
    public String addQuestion(@RequestParam String question, @RequestParam String answer) {
        javaQuestionService.addQuestion(new Question(question, answer));
        return "Question added successfully";
    }

    @PostMapping("/remove")
    public String removeQuestion(@RequestParam String question, @RequestParam String answer) {
        javaQuestionService.removeQuestion(new Question(question, answer));
        return "Question removed successfully";
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return javaQuestionService.getAllQuestions();
    }
}