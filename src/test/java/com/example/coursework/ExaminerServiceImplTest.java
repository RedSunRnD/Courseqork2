package com.example.coursework;

import com.example.coursework.domain.Question;
import com.example.coursework.services.ExaminerService;
import com.example.coursework.services.ExaminerServiceImpl;
import com.example.coursework.services.JavaQuestionService;
import com.example.coursework.services.QuestionService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExaminerServiceImplTest {
    private final QuestionService mockQuestionService = new JavaQuestionService();
    private final ExaminerService examinerService = new ExaminerServiceImpl(mockQuestionService);

    @Test
    public void testGetQuestions() {
        mockQuestionService.addQuestion(new Question("Вопрос 1", "Ответ 2"));
        mockQuestionService.addQuestion(new Question("Вопрос 2", "Ответ 2"));

        List<Question> questions = examinerService.getQuestions(2);
        assertEquals(2, questions.size());
        assertTrue(mockQuestionService.getAllQuestions().containsAll(questions));
    }

    @Test
    public void testGetQuestionsThrowsException() {
        mockQuestionService.addQuestion(new Question("Вопрос 1", "Ответ 1"));
        assertThrows(IllegalArgumentException.class, () -> examinerService.getQuestions(3));
    }
}