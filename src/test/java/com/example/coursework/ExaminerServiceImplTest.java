package com.example.coursework.services;

import com.example.coursework.domain.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExaminerServiceImplTest {

    private QuestionService mockQuestionService;
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    public void setUp() {
        mockQuestionService = Mockito.mock(QuestionService.class);
        examinerService = new ExaminerServiceImpl(mockQuestionService);
    }

    @Test
    public void testGetQuestions() {
        JavaQuestionService mockQuestionService = Mockito.mock(JavaQuestionService.class);
        ExaminerServiceImpl examinerService = new ExaminerServiceImpl(mockQuestionService);
        List<Question> mockQuestions = Arrays.asList(
                new Question("Вопрос 1", "Ответ 1"),
                new Question("Вопрос 2", "Ответ 2"),
                new Question("Вопрос 3", "Ответ 3")
        );
        Mockito.when(mockQuestionService.getAllQuestions()).thenReturn(mockQuestions);
        Collection<Question> result = examinerService.getQuestions(2);
        assertEquals(2, result.size());
        assertTrue(mockQuestions.containsAll(result));
    }
}