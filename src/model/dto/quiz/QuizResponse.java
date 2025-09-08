package model.dto.quiz;

import java.util.List;

public record QuizResponse(
        int questionId,
        String categoryName,
        String quizTypeName,
        String creatorName,
        String questionText,
        List<AnswerResponse> answers
) {
}
