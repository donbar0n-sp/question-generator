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
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private final List<Question> sampleQuestions = List.of(
            new Question("What is Java?", "A programming language"),
            new Question("What is Spring?", "A Java framework"),
            new Question("What is an interface?", "A contract for classes")
    );

    @BeforeEach
    void setUp() {
        // Remove MockitoAnnotations.openMocks(this) since @ExtendWith(MockitoExtension.class) is enough
    }

    @Test
    void shouldReturnRequestedNumberOfQuestions() {
        when(questionService.getAll()).thenReturn(sampleQuestions);

        Set<Question> questions = (Set<Question>) examinerService.getQuestions(2);

        assertEquals(2, questions.size());
        verify(questionService, times(1)).getAll();
    }

    @Test
    void shouldThrowExceptionWhenRequestingMoreThanAvailable() {
        when(questionService.getAll()).thenReturn(sampleQuestions);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> examinerService.getQuestions(10)
        );

        assertEquals("400 BAD_REQUEST \"Not enough questions available.\"", exception.getMessage());
        verify(questionService, times(1)).getAll();
    }
}
