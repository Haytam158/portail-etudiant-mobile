package com.portail.etudiant.services.interfaces;



import com.portail.etudiant.model.User;
import com.portail.etudiant.services.exceptions.ServiceException;

import java.util.List;

public interface IUserService {
    Boolean addUser(User user) throws ServiceException;

    List<User> getAllUsers() throws ServiceException;

    Boolean updateUser(User user) throws ServiceException;

    Boolean deleteUser(User user) throws ServiceException;

    User AuthentificateUser(String CIN, String password) throws ServiceException;

    Integer getStudentsCount();

    Integer getTeachersCount();

    Boolean deleteUserByCin(String Cin) throws ServiceException;

}
