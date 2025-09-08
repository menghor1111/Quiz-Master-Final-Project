package model.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Answer {

    private Integer answerId;
    private Integer questionId;
    private String optionKey;
    private String answerText;
    private boolean isCorrect;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Answer(Integer questionId, String optionKey, String answerText,
                  boolean isCorrect, LocalDateTime createAt, LocalDateTime updateAt) {
        this.questionId = questionId;
        this.optionKey = optionKey;
        this.answerText = answerText;
        this.isCorrect = isCorrect;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Answer(String optionKey, String answerText, boolean isCorrect)
    {
        this.optionKey = optionKey;
        this.answerText = answerText;
        this.isCorrect = isCorrect;
    }
}
