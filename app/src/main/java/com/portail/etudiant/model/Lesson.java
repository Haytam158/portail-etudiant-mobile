package com.portail.etudiant.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(
        foreignKeys = @ForeignKey(
                entity = Module.class,
                parentColumns = "module_id",
                childColumns = "module_id",
                onDelete = ForeignKey.CASCADE
        )
)
public class Lesson {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "lesson_id") @NonNull
    private Long lessonId;
    private String contentPath;

    @ColumnInfo(name = "module_id", index = true)
    private Long moduleId;


    @Ignore
    public Lesson(Long lessonId, String contentPath,Long moduleId) {
        this.lessonId = lessonId;
        this.contentPath = contentPath;
        this.moduleId = moduleId;
    }

    public Lesson() {
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public String getContentPath() {
        return contentPath;
    }

    public void setContentPath(String contentPath) {
        this.contentPath = contentPath;
    }

}
