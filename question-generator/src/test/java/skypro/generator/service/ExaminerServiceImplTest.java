package skypro.generator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import skypro.generator.domain.Question;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService javaQuestionService;

    @Mock
    private MathQuestionService mathQuestionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private final List<Question> javaQuestions = List.of(
            new Question("What is Java?", "A programming language"),
            new Question("What is Spring?", "A Java framework")
    );

    private final List<Question> mathQuestions = List.of(
            new Question("What is 2 + 2?", "4"),
            new Question("What is 5 * 6?", "30")
    );

    @BeforeEach
    void setUp() {
        when(javaQuestionService.getAll()).thenReturn(javaQuestions);
        when(mathQuestionService.getAll()).thenReturn(mathQuestions);
    }

    @Test
    void shouldReturnRequestedNumberOfQuestions() {
        Set<Question> questions = (Set<Question>) examinerService.getQuestions(2);

        assertEquals(2, questions.size());
        verify(javaQuestionService, times(1)).getAll();
        verify(mathQuestionService, times(1)).getAll();
    }

    @Test
    void shouldThrowExceptionWhenRequestingMoreThanAvailable() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> examinerService.getQuestions(10)
        );

        assertEquals("400 BAD_REQUEST \"Not enough questions available.\"", exception.getMessage());
        verify(javaQuestionService, times(1)).getAll();
        verify(mathQuestionService, times(1)).getAll();
    }
}
