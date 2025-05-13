package com.portail.etudiant.data.sqlite.dao.interfaces;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface CourseDao {

    @Query("SELECT count(*) from Course")
    Integer getCoursesCount();
}
