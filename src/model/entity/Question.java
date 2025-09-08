package model.entity;

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
    private String createAt;
    private String updateAt;

    public Question(Integer categoryId, Integer quizTypeId, Integer creatorId,
                    String questionText, String createAt, String updateAt) {
        this.categoryId = categoryId;
        this.quizTypeId = quizTypeId;
        this.creatorId = creatorId;
        this.questionText = questionText;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
    public Question(Integer categoryId, Integer quizTypeId,Integer creatorId,String questionText)
    {
        this.categoryId = categoryId;
        this.quizTypeId = quizTypeId;
        this.creatorId = creatorId;
        this.questionText = questionText;
    }
}
