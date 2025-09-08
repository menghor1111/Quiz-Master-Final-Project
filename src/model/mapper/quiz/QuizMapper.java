package model.mapper.quiz;

import model.dto.quiz.AnswerRequest;
import model.dto.quiz.AnswerResponse;
import model.dto.quiz.QuizRequest;
import model.dto.quiz.QuizResponse;
import model.entity.Answer;
import model.entity.Question;

import java.util.List;

public interface QuizMapper {
    Question toQuestionEntity(QuizRequest request);
    Answer toAnswerEntity(AnswerRequest request);
    AnswerResponse toAnswerResponse(Answer answer);
    QuizResponse toQuizResponse(Question question,String categoryName,String quizTypeName, String creatorName, List<AnswerResponse> answers);

}
