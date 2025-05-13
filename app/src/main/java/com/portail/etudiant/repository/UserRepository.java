package com.portail.etudiant.repository;

import com.portail.etudiant.data.sqlite.dao.interfaces.UserDao;
import com.portail.etudiant.model.User;
import com.portail.etudiant.repository.interfaces.IUserRepository;

import java.util.Collections;
import java.util.List;

public class UserRepository implements IUserRepository {

    private UserDao userDao;

    public UserRepository() {}

    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Long addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public Integer updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public Integer deleteUser(User user) {
        return userDao.deleteUser(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByCIN(String CIN) {
        return userDao.getUserByCIN(CIN);
    }

    @Override
    public Integer getStudentsCount() {
        return userDao.getStudentsCount();
    }

    @Override
    public Integer getTeachersCount() {
        return userDao.getTeachersCount();
    }

    @Override
    public Integer deleteUserByCin(String cin) {
        return userDao.deleteUserByCin(cin);
    }

}
