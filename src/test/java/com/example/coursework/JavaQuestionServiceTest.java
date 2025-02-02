package com.example.coursework;

import com.example.coursework.domain.Question;
import com.example.coursework.services.JavaQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {

    @InjectMocks
    private JavaQuestionService javaQuestionService;

    @BeforeEach
    public void setUp() {
        // Очищаем список вопросов перед каждым тестом
        javaQuestionService.getAll().clear();
    }

    @Test
    public void testAddQuestion() {
        Question question = javaQuestionService.add("What is Java?", "A programming language");

        assertNotNull(question);
        assertEquals("What is Java?", question.getQuestion());
        assertEquals("A programming language", question.getAnswer());
        assertTrue(javaQuestionService.getAll().contains(question));
    }

    @Test
    public void testRemoveQuestion() {
        Question question = new Question("What is Java?", "A programming language");
        javaQuestionService.add(question.getQuestion(), question.getAnswer());

        Question removedQuestion = javaQuestionService.remove(question);

        assertNotNull(removedQuestion);
        assertEquals(question, removedQuestion);
        assertFalse(javaQuestionService.getAll().contains(question));
    }

    @Test
    public void testGetAllQuestions() {
        Question question1 = javaQuestionService.add("Q1", "A1");
        Question question2 = javaQuestionService.add("Q2", "A2");

        Collection<Question> allQuestions = javaQuestionService.getAll();

        assertEquals(2, allQuestions.size());
        assertTrue(allQuestions.contains(question1));
        assertTrue(allQuestions.contains(question2));
    }

    @Test
    public void testGetRandomQuestion() {
        Question question1 = javaQuestionService.add("Q1", "A1");
        Question question2 = javaQuestionService.add("Q2", "A2");

        Question randomQuestion = javaQuestionService.getRandomQuestion();

        assertNotNull(randomQuestion);
        assertTrue(javaQuestionService.getAll().contains(randomQuestion));
    }

    @Test
    public void testGetRandomQuestion_WhenNoQuestions() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            javaQuestionService.getRandomQuestion();
        });
        assertEquals("Нет доступных вопросов", exception.getMessage());
    }
}