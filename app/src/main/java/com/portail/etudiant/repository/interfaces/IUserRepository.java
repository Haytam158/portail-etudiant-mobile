package com.portail.etudiant.repository.interfaces;

import com.portail.etudiant.model.User;

import java.util.List;

public interface IUserRepository {
     Long addUser(User user);
     List<User> getAllUsers();
     Integer updateUser(User user);
     Integer deleteUser(User user);
     User getUserById(Long id);
     User getUserByCIN(String CIN);

     Integer getStudentsCount();
     Integer getTeachersCount();

     Integer deleteUserByCin(String cin);


}
