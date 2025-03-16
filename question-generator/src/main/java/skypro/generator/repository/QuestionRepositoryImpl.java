package skypro.generator.repository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import skypro.generator.domain.Question;

import java.util.HashSet;
import java.util.Set;
import java.util.Collection;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository {
    private final Set<Question> questions = new HashSet<>();

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.remove(question)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found: " + question);
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Set.copyOf(questions);
    }
}

