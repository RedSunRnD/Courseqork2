package com.example.coursework.services;

import com.example.coursework.CourseworkApplication;
import com.example.coursework.domain.Question;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class JavaQuestionService implements CourseworkApplication.QuestionService {

    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        questions.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question remove(Question question) {
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            throw new IllegalStateException("Нет доступных вопросов");
        }
        List<Question> questionList = new ArrayList<>(questions);
        int randomIndex = random.nextInt(questionList.size());
        return questionList.get(randomIndex);
    }
}