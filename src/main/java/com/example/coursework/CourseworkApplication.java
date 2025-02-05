package com.example.coursework;

import com.example.coursework.domain.Question;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collection;

@SpringBootApplication
public class CourseworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseworkApplication.class, args);
	}

    public static interface QuestionService {
        Question add(String question, String answer);
        Question remove(Question question);
        Collection<Question> getAll();
        Question getRandomQuestion();
    }
}
