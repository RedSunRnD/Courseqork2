package com.example.coursework;

import com.example.coursework.domain.Question;
import com.example.coursework.services.JavaQuestionService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JavaQuestionServiceTest {
    private final JavaQuestionService service = new JavaQuestionService();

    @Test
    public void testAddAndRemoveQuestion() {
        Question q1 = new Question("Вопрос 1", "Ответ 1");
        service.addQuestion(q1);
        assertEquals(1, service.getAllQuestions().size());

        service.removeQuestion(q1);
        assertTrue(service.getAllQuestions().isEmpty());
    }

    @Test
    public void testGetRandomQuestion() {
        Question q1 = new Question("Вопрос 1", "Ответ 1");
        Question q2 = new Question("Вопрос 2", "Ответ 2");
        service.addQuestion(q1);
        service.addQuestion(q2);

        Question randomQuestion = service.getRandomQuestion();
        assertNotNull(randomQuestion);
        assertTrue(service.getAllQuestions().contains(randomQuestion));
    }
}