package com.portail.etudiant.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(
        foreignKeys = @ForeignKey(
                entity = Exam.class,
                parentColumns = "exam_id",
                childColumns = "exam_id",
                onDelete = ForeignKey.CASCADE
        )
)
public class Question {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "question_id") @NonNull
    private Long questionId;
    private Integer points;
    private String questiontitle;

    @ColumnInfo(name = "exam_id", index = true)
    private Long examId;

    @Ignore
    public Question(Long questionId, int points, String questiontitle, Long examId) {
        this.questionId = questionId;
        this.points = points;
        this.questiontitle = questiontitle;
        this.examId = examId;
    }

    public Question(){}

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Long getExamId() {
        return examId;
    }

    public Integer getPoints() {
        return points;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestiontitle() {
        return questiontitle;
    }

    public void setQuestiontitle(String questiontitle) {
        this.questiontitle = questiontitle;
    }
}
