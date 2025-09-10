package model.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Result {

    private int resultId;
    private int playerId;
    private Integer CategoryId;
    private Integer score;
    private Integer totalQuestions;
    private Integer correctAnswers;
    private Double average;
    private Integer timeTaken;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Result(Integer playerId, Integer categoryId, Integer score, Integer totalQuestions,
                  Integer correctAnswers, Double average, Integer timeTaken, LocalDateTime createAt, LocalDateTime updateAt) {
        this.playerId = playerId;
        CategoryId = categoryId;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.correctAnswers = correctAnswers;
        this.average = average;
        this.timeTaken = timeTaken;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}
