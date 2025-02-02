package com.example.coursework.controller;

import com.example.coursework.services.ExaminerService;
import com.example.coursework.services.JavaQuestionService;
import com.example.coursework.domain.Question;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/exam")
public class ExamController {
    private final ExaminerService examinerService;
    private final JavaQuestionService javaQuestionService;

    public ExamController(ExaminerService examinerService, JavaQuestionService javaQuestionService) {
        this.examinerService = examinerService;
        this.javaQuestionService = javaQuestionService;
    }

    @GetMapping("/get/{amount}")
    public Collection<Question> getRandomQuestions(@PathVariable int amount) {
        return examinerService.getQuestions(amount);
    }

    @PostMapping("/java/add")
    public Question addQuestion(@RequestParam String question, @RequestParam String answer) {
        return javaQuestionService.add(question, answer);
    }

    @DeleteMapping("/java/remove")
    public Question removeQuestion(@RequestParam String question, @RequestParam String answer) {
        return javaQuestionService.remove(new Question(question, answer));
    }

    @GetMapping("/java/find")
    public Collection<Question> findAllQuestions() {
        return javaQuestionService.getAll();
    }
}