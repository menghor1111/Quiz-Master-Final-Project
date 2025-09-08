package model.dto.quiz;

public record AnswerRequest(
        String optionKey,
        String answerText,
        boolean isCorrect
) {

}
