package com.example.coursework;

import com.example.coursework.domain.Question;
import com.example.coursework.services.ExaminerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExaminerServiceImplTest {

    @Mock
    private CourseworkApplication.QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetQuestionsWithValidAmount() {
        int amount = 3;
        Set<Question> mockQuestions = new HashSet<>(Arrays.asList(
                new Question("Вопрос 1", "Ответ 1"),
                new Question("Вопрос 2", "Ответ 2"),
                new Question("Вопрос 3", "Ответ 3")
        ));
        when(questionService.getAll()).thenReturn(mockQuestions);

        Question q1 = new Question("Вопрос 1", "Ответ 1");
        Question q2 = new Question("Вопрос 2", "Ответ 2");
        Question q3 = new Question("Вопрос 3", "Ответ 3");

        when(questionService.getRandomQuestion()).thenReturn(q1, q2, q3);

        Collection<Question> result = examinerService.getQuestions(amount);

        assertNotNull(result);
        assertEquals(amount, result.size());
        assertTrue(result.containsAll(Arrays.asList(q1, q2, q3)));
        verify(questionService, times(amount)).getRandomQuestion();
    }

    @Test
    void testGetQuestionsWhenAmountExceedsAvailableQuestions() {
        // Arrange
        int amount = 5;
        Set<Question> mockQuestions = new HashSet<>(Arrays.asList(
                new Question("Вопрос 1", "Ответ 1"),
                new Question("Вопрос 2", "Ответ 2"),
                new Question("Вопрос 3", "Ответ 3")
        ));
        when(questionService.getAll()).thenReturn(mockQuestions);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            examinerService.getQuestions(amount);
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Количество запрашиваемых вопросов больше, чем количество доступных", exception.getReason());
    }

    @Test
    void testGetQuestionsWhenNoQuestionsAvailable() {
        int amount = 1;
        when(questionService.getAll()).thenReturn(new HashSet<>());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            examinerService.getQuestions(amount);
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Количество запрашиваемых вопросов больше, чем количество доступных", exception.getReason());
    }

    @Test
    void testGetQuestionsWithZeroAmount() {
        int amount = 0;
        when(questionService.getAll()).thenReturn(new HashSet<>());

        Collection<Question> result = examinerService.getQuestions(amount);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(questionService, never()).getRandomQuestion();
    }
}