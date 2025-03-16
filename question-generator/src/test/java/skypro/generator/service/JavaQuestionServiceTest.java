package skypro.generator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import skypro.generator.domain.Question;
import skypro.generator.repository.QuestionRepository;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JavaQuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;
    private JavaQuestionService javaQuestionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        javaQuestionService = new JavaQuestionService(questionRepository);
    }

    @Test
    void shouldAddQuestion() {
        Question question = new Question("What is Java?", "A programming language");

        when(questionRepository.add(question)).thenReturn(question);

        Question result = javaQuestionService.add(question);

        assertEquals(question, result);
        verify(questionRepository, times(1)).add(question);
    }

    @Test
    void shouldRemoveQuestion() {
        Question question = new Question("What is Java?", "A programming language");

        when(questionRepository.remove(question)).thenReturn(question);

        Question result = javaQuestionService.remove(question);

        assertEquals(question, result);
        verify(questionRepository, times(1)).remove(question);
    }

    @Test
    void shouldReturnAllQuestions() {
        List<Question> questions = List.of(
                new Question("What is Java?", "A programming language"),
                new Question("What is Spring?", "A Java framework")
        );

        when(questionRepository.getAll()).thenReturn(questions);

        assertEquals(questions, javaQuestionService.getAll());
        verify(questionRepository, times(1)).getAll();
    }
}

