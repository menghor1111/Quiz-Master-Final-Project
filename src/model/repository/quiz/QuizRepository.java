package model.repository.quiz;

import model.dto.quiz.QuizResponse;
import model.entity.Answer;
import model.entity.Question;

import java.util.List;

public interface QuizRepository {

    int insertQuestion(Question question);
    boolean insertAnswer(Answer answer);
    boolean existsByQuestionText(String questionText);
    boolean existsAnswer(Answer answer);
    Question findByQuestionText(String questionText);
    List<QuizResponse> findAllQuizzes(Integer categoryId, Integer quizTypeId);
}
