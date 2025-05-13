package com.portail.etudiant.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

@Entity(
        tableName = "exam_result",
        primaryKeys = {"exam_id", "user_id"},
        foreignKeys = {
                @ForeignKey(
                        entity = Exam.class,
                        parentColumns = "exam_id",
                        childColumns = "exam_id",
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = User.class,
                        parentColumns = "user_id",
                        childColumns = "user_id",
                        onDelete = ForeignKey.CASCADE
                )
        }
)
public class ExamResult {
    @ColumnInfo(name = "exam_id", index = true) @NonNull
    private Long examId;

    @ColumnInfo(name = "user_id", index = true) @NonNull
    private Long userId;
    private String feedback;

    private Float score;

    @Ignore
    public ExamResult(Long examId, Long userId, String feedback, Float score) {
        this.examId = examId;
        this.userId = userId;
        this.feedback = feedback;
        this.score = score;
    }

    public ExamResult() {
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }
}
