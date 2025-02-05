package com.example.coursework.services;

import com.example.coursework.domain.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;

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
        Question q1 = new Question("Вопрос 1", "Ответ 1");
        Question q2 = new Question("Вопрос 2", "Ответ 2");

        when(mockQuestionService.getAllQuestions()).thenReturn(Arrays.asList(q1, q2));

        Collection<Question> questions = examinerService.getQuestions(1);
        assertNotNull(questions);
        assertEquals(1, questions.size());

        verify(mockQuestionService, times(1)).getAllQuestions();
    }

    @Test
    public void testGetQuestionsWithInvalidAmount() {
        when(mockQuestionService.getAllQuestions()).thenReturn(Arrays.asList(new Question("Вопрос 1", "Ответ 1")));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> examinerService.getQuestions(2));
        assertEquals("Доступных вопросов меньше запрашиваемого количества", exception.getMessage());
    }
}