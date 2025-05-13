package com.portail.etudiant.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(
        foreignKeys = @ForeignKey(
                entity = Course.class,
                parentColumns = "course_id",
                childColumns = "course_id",
                onDelete = ForeignKey.CASCADE
        )
)
public class Module {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "module_id") @NonNull
    private Long moduleId;
    @Ignore
    private List<Lesson> lessons;

    @ColumnInfo(name = "course_id", index = true)
    private Long courseId;

    @Ignore
    public Module(Long moduleId, List<Lesson> lessons, Long courseId) {
        this.moduleId = moduleId;
        this.lessons = lessons;
        this.courseId = courseId;
    }

    public Module() {
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
