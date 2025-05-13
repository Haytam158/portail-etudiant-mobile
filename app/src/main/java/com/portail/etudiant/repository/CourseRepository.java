package com.portail.etudiant.repository;

import com.portail.etudiant.data.sqlite.dao.interfaces.CourseDao;
import com.portail.etudiant.repository.interfaces.ICourseRepository;

public class CourseRepository implements ICourseRepository {

    private CourseDao courseDao;

    public CourseRepository(){

    }

    public CourseRepository(CourseDao courseDao){
        this.courseDao = courseDao;
    }

    @Override
    public Integer getCoursesCount() {
        return courseDao.getCoursesCount();
    }
}
