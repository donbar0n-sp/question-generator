package skypro.generator.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import skypro.generator.domain.Question;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final JavaQuestionService JavaQuestionService;
    private final MathQuestionService MathQuestionService;
    private final Random random = new Random();

    public ExaminerServiceImpl(skypro.generator.service.JavaQuestionService javaQuestionService, skypro.generator.service.MathQuestionService mathQuestionService) {
        JavaQuestionService = javaQuestionService;
        MathQuestionService = mathQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        List<Question> allQuestions = new ArrayList<>();
        allQuestions.addAll(JavaQuestionService.getAll());
        allQuestions.addAll(MathQuestionService.getAll());

        if (amount > allQuestions.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough questions available.");
        }

        Set<Question> selectedQuestions = new HashSet<>();
        while (selectedQuestions.size() < amount) {
            Question randomQuestion = allQuestions.get(random.nextInt(allQuestions.size()));
            selectedQuestions.add(randomQuestion);
        }

        return selectedQuestions;
    }
}
