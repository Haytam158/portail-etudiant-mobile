package com.portail.etudiant.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(
        foreignKeys = @ForeignKey(
                entity = Course.class,
                parentColumns = "course_id",
                childColumns = "course_id",
                onDelete = ForeignKey.CASCADE
        )
)
public class Exam {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "exam_id") @NonNull
    private Long examId;

    @ColumnInfo(name = "course_id", index = true)
    private Long courseId;

    private Integer totalpoints;


    @Ignore
    public Exam(Long examId, Integer totalpoints, Long courseId) {
        this.examId = examId;
        this.totalpoints = totalpoints;
        this.courseId = courseId;
    }

    public Exam(){}


    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Integer getTotalpoints() {
        return totalpoints;
    }

    public void setTotalpoints(Integer totalpoints) {
        this.totalpoints = totalpoints;
    }
}
