package com.portail.etudiant.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(
        foreignKeys = @ForeignKey(
                entity = Question.class,
                parentColumns = "question_id",
                childColumns = "question_id",
                onDelete = ForeignKey.CASCADE
        )
)
public class Option {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "option_id") @NonNull
    private Long optionId;

    private Integer displayOrder;
    private Boolean isCorrect;

    private String text;

    @ColumnInfo(name = "question_id", index = true)
    private Long questionId;

    @Ignore
    public Option(Long optionId, Integer displayOrder, Boolean isCorrect, String text, Long questionId) {
        this.optionId = optionId;
        this.displayOrder = displayOrder;
        this.isCorrect = isCorrect;
        this.text = text;
        this.questionId = questionId;
    }

    public Option(){}


    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public void setIsCorrect(Boolean correct) {
        isCorrect = correct;
    }

//    public Boolean getCorrect() {
//        return isCorrect;
//    }
//
//    public void setCorrect(Boolean correct) {
//        isCorrect = correct;
//    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
