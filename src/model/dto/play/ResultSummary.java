package model.dto.play;

public record ResultSummary(
        int totalQuestions,
        int correctAnswers,
        int totalScore,
        double average,
        long elapsedSeconds
) {}