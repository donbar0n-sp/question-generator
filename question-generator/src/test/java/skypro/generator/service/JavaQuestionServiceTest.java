package skypro.generator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import skypro.generator.domain.Question;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {
    private JavaQuestionService javaQuestionService;

    @BeforeEach
    void setUp() {
        javaQuestionService = new JavaQuestionService();
    }

    @Test
    void shouldAddQuestion() {
        Question question = new Question("What is OOP?", "Object-Oriented Programming");
        javaQuestionService.add(question);

        assertTrue(javaQuestionService.getAll().contains(question));
    }

    @Test
    void shouldRemoveQuestion() {
        Question question = new Question("What is OOP?", "Object-Oriented Programming");
        javaQuestionService.add(question);
        javaQuestionService.remove(question);

        assertFalse(javaQuestionService.getAll().contains(question));
    }

    @Test
    void shouldReturnRandomQuestion() {
        Question question1 = new Question("What is Java?", "A programming language");
        Question question2 = new Question("What is Spring?", "A Java framework");

        javaQuestionService.add(question1);
        javaQuestionService.add(question2);

        assertNotNull(javaQuestionService.getRandomQuestion());
    }
}

