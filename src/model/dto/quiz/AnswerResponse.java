package model.dto.quiz;

public record AnswerResponse(
        int answerId,
        String optionKey,
        String answerText,
        boolean isCorrect
) {
}
