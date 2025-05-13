package com.portail.etudiant.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;
@Entity(
        tableName = "certificate",
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
public class Certificate {
    @ColumnInfo(name = "course_id", index = true)    @NonNull
    private Long courseId;

    @ColumnInfo(name = "user_id", index = true) @NonNull
    private Long userId;
    private Date issueDate;

    private String certificatePath;

    @Ignore
    public Certificate(Long courseId, Long userId, Date issueDate, String certificatePath) {
        this.courseId = courseId;
        this.userId = userId;
        this.issueDate = issueDate;
        this.certificatePath = certificatePath;
    }

    public Certificate() {
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

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getCertificatePath() {
        return certificatePath;
    }

    public void setCertificatePath(String certificatePath) {
        this.certificatePath = certificatePath;
    }
}
