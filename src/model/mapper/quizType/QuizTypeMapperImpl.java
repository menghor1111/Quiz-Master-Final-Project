package model.mapper.quizType;

import model.dto.quizType.QuizTypeResponse;
import model.entity.QuizType;

public class QuizTypeMapperImpl implements QuizTypeMapper {


    @Override
    public QuizTypeResponse toQuizTypeResponse(QuizType quizType) {
        return new QuizTypeResponse(
                quizType.getQuizTypeId(),
                quizType.getQuizName()
        );
    }
}
