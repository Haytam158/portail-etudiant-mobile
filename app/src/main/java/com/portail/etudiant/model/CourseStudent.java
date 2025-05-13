package com.portail.etudiant.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

@Entity(
        tableName = "course_student",
        primaryKeys = {"course_id", "user_id"},
        foreignKeys = {
                @ForeignKey(
                        entity = Course.class,
                        parentColumns = "course_id",
                        childColumns = "course_id",
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
public class CourseStudent {


    @ColumnInfo(name = "course_id", index = true) @NonNull
    private Long courseId;

    @ColumnInfo(name = "user_id", index = true) @NonNull
    private Long userId;
    private Float grades;

    @Ignore
    public CourseStudent(Long courseId, Long userId, Float grades) {
        this.courseId = courseId;
        this.userId = userId;
        this.grades = grades;
    }

    public CourseStudent() {
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Float getGrades() {
        return grades;
    }

    public void setGrades(Float grades) {
        this.grades = grades;
    }


}
