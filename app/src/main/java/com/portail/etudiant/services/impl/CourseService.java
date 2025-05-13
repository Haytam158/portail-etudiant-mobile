package com.portail.etudiant.services.impl;

import com.portail.etudiant.repository.CourseRepository;
import com.portail.etudiant.repository.interfaces.ICourseRepository;
import com.portail.etudiant.services.exceptions.ServiceException;
import com.portail.etudiant.services.interfaces.ICourseService;

public class CourseService implements ICourseService {

    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public CourseService(){

    }


    @Override
    public Integer getCoursesCount() throws ServiceException {
        try {
            return courseRepository.getCoursesCount();
        }catch(Exception e){
            throw  new ServiceException("Failed to Count Courses " + e);
        }
    }
}
