package model.service.quiz;

import exception.QuizException;
import model.dto.quiz.QuizRequest;
import model.dto.quiz.QuizResponse;

import java.util.List;

public interface QuizService {
    boolean createQuiz(QuizRequest request) throws QuizException;
    List<QuizResponse> getAllQuizzes() throws QuizException;
}
