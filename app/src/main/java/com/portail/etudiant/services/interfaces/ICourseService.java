package com.portail.etudiant.services.interfaces;

import com.portail.etudiant.services.exceptions.ServiceException;

public interface ICourseService {

    Integer getCoursesCount() throws ServiceException;
}
