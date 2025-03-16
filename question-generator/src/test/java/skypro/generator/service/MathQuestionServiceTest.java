package skypro.generator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import skypro.generator.domain.Question;
import skypro.generator.repository.QuestionRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MathQuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    private MathQuestionService mathQuestionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mathQuestionService = new MathQuestionService(questionRepository);
    }

    @Test
    void shouldAddQuestion() {
        Question question = new Question("What is 2 + 2?", "4");

        when(questionRepository.add(question)).thenReturn(question);

        Question result = mathQuestionService.add(question);

        assertEquals(question, result);
        verify(questionRepository, times(1)).add(question);
    }

    @Test
    void shouldRemoveQuestion() {
        Question question = new Question("What is 2 + 2?", "4");

        when(questionRepository.remove(question)).thenReturn(question);

        Question result = mathQuestionService.remove(question);

        assertEquals(question, result);
        verify(questionRepository, times(1)).remove(question);
    }

    @Test
    void shouldReturnAllQuestions() {
        List<Question> questions = List.of(
                new Question("What is 2 + 2?", "4"),
                new Question("What is 5 * 6?", "30")
        );

        when(questionRepository.getAll()).thenReturn(questions);

        assertEquals(questions, mathQuestionService.getAll());
        verify(questionRepository, times(1)).getAll();
    }
}

