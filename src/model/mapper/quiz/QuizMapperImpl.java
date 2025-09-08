package model.mapper.quiz;

import model.dto.quiz.AnswerRequest;
import model.dto.quiz.AnswerResponse;
import model.dto.quiz.QuizRequest;
import model.dto.quiz.QuizResponse;
import model.entity.Answer;
import model.entity.Question;

import java.util.List;

public class QuizMapperImpl implements QuizMapper{
    @Override
    public Question toQuestionEntity(QuizRequest request) {
        return new Question(
                   request.categoryId(),
                   request.quizTypeId(),
                   request.creatorId(),
                   request.questionText()
        );
    }

    @Override
    public Answer toAnswerEntity(AnswerRequest request) {
        return new Answer(
                request.optionKey(),
                request.answerText(),
                request.isCorrect()
        );
    }

    @Override
    public AnswerResponse toAnswerResponse(Answer answer) {
        return new AnswerResponse(
                answer.getAnswerId(),
                answer.getOptionKey(),
                answer.getAnswerText(),
                answer.isCorrect()
        );
    }



    @Override
    public QuizResponse toQuizResponse(Question question,String categoryName,String quizTypeName, String creatorName, List<AnswerResponse> answers) {
        return new QuizResponse(
                question.getQuestionId(),
                categoryName,
                quizTypeName,
                creatorName,
                question.getQuestionText(),
                answers
        );
    }
}
