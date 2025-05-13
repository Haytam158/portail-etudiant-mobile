package com.portail.etudiant.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;


@Entity
public class Course {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "course_id") @NonNull
    private Long courseId;
    private String description;

    @Ignore
    private List<Module> modules;
    private String title;

    public Course(Long courseId, String description, List<Module> modules, String title) {
        this.courseId = courseId;
        this.description = description;
        this.modules = modules;
        this.title = title;
    }

    public Course() {
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
