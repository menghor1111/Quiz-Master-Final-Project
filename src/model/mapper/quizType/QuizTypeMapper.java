package model.mapper.quizType;

import model.dto.quizType.QuizTypeResponse;
import model.entity.QuizType;

public interface QuizTypeMapper {
    QuizTypeResponse toQuizTypeResponse(QuizType quizType);

}
