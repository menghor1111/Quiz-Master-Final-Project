package model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Question {
    private Integer questionId;
    private Integer categoryId;
    private Integer quizTypeId;
    private Integer creatorId;
    private String questionText;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;


    public Question(Integer categoryId, Integer quizTypeId, Integer creatorId,
                    String questionText, LocalDateTime createAt, LocalDateTime updateAt) {
        this.categoryId = categoryId;
        this.quizTypeId = quizTypeId;
        this.creatorId = creatorId;
        this.questionText = questionText;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}
