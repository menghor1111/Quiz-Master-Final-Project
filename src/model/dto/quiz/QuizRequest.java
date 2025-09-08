package model.dto.quiz;

import model.entity.Answer;

import java.util.List;

public record QuizRequest(
        int categoryId,
        int quizTypeId,
        int creatorId,
        String questionText,
        List<AnswerRequest> answers
) {
}
