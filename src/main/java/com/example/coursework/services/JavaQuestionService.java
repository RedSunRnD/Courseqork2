package com.example.coursework.services;

import com.example.coursework.domain.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JavaQuestionService implements QuestionService {
    private final List<Question> questions = new ArrayList<>();

    @Override
    public void addQuestion(String questionText, String answer) {
        Question newQuestion = new Question(questionText, answer);
        if (!questions.contains(newQuestion)) {
            questions.add(newQuestion);
        }
    }

    @Override
    public void addQuestion(Question question) {
        questions.add(question);
    }

    @Override
    public void removeQuestion(Question question) {
        questions.remove(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        return new ArrayList<>(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            throw new IllegalStateException("Нет доступных вопросов");
        }
        Random random = new Random();
        return questions.get(random.nextInt(questions.size()));
    }
}