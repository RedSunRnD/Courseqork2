package com.example.coursework.services;

import com.example.coursework.domain.Question;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JavaQuestionServiceTest {

    @Test
    public void testAddAndGetRandomQuestion() {
        JavaQuestionService service = new JavaQuestionService();
        Question q1 = new Question("Вопрос 1", "Ответ 2");
        Question q2 = new Question("Вопрос 2", "Ответ 2");

        service.addQuestion(q1);
        service.addQuestion(q2);

        Question randomQuestion = service.getRandomQuestion();
        assertNotNull(randomQuestion);
        assertTrue(randomQuestion.equals(q1) || randomQuestion.equals(q2));
    }

    @Test
    public void testRemoveQuestion() {
        JavaQuestionService service = new JavaQuestionService();
        Question q1 = new Question("Вопрос 1", "Ответ 1");

        service.addQuestion(q1);
        service.removeQuestion(q1);

        assertEquals(0, service.getAllQuestions().size());
    }

    @Test
    public void testGetRandomQuestionWithEmptyList() {
        JavaQuestionService service = new JavaQuestionService();
        Exception exception = assertThrows(IllegalStateException.class, () -> service.getRandomQuestion());
        assertEquals("Нет доступных вопросов", exception.getMessage());
    }
}