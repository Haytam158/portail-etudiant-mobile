package com.portail.etudiant.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

@Entity(
        tableName = "teacher_course",
        primaryKeys = {"user_id", "course_id"},
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
public class TeacherCourse {
    @ColumnInfo(name = "course_id", index = true) @NonNull
    private Long courseId;

    @ColumnInfo(name = "user_id", index = true) @NonNull
    private Long userId;

    private Boolean canEditCourse;
    private Boolean canGradeExams;

    @Ignore
    public TeacherCourse(Long courseId, Long userId, Boolean canEditCourse, Boolean canGradeExams) {
        this.courseId = courseId;
        this.userId = userId;
        this.canEditCourse = canEditCourse;
        this.canGradeExams = canGradeExams;
    }
    public TeacherCourse(){}

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

    public Boolean getCanEditCourse() {
        return canEditCourse;
    }

    public void setCanEditCourse(Boolean canEditCourse) {
        this.canEditCourse = canEditCourse;
    }

    public Boolean getCanGradeExams() {
        return canGradeExams;
    }

    public void setCanGradeExams(Boolean canGradeExams) {
        this.canGradeExams = canGradeExams;
    }
}
