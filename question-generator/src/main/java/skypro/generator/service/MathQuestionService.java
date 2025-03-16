package skypro.generator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import skypro.generator.domain.Question;
import skypro.generator.repository.QuestionRepository;

import java.util.*;

@Service
public class MathQuestionService implements QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public MathQuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        return questionRepository.add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        return questionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
        return questionRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        Collection<Question> allQuestions = questionRepository.getAll();
        if (allQuestions.isEmpty()) {
            throw new NoSuchElementException("No questions available");
        }
        return new ArrayList<>(allQuestions).get(new Random().nextInt(allQuestions.size()));
    }
}

