package com.portail.etudiant.services.impl;

import com.portail.etudiant.model.User;
import com.portail.etudiant.repository.UserRepository;
import com.portail.etudiant.services.exceptions.ServiceException;
import com.portail.etudiant.services.interfaces.IUserService;
import com.portail.etudiant.services.utils.HashUtil;

import java.util.List;

public class UserService implements IUserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserService(){}

    @Override
    public Boolean addUser(User user) throws ServiceException {
        try {
            String hashedPassword = HashUtil.sha256(user.getPassword());
            user.setPassword(hashedPassword);
            Long id = userRepository.addUser(user);
            return id > 0;
        } catch (Exception e) {
            throw new ServiceException("Failed to add user" + e);
        }
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        try {
            return userRepository.getAllUsers();
        } catch (Exception e) {
            throw new ServiceException("Failed to retrieve users" + e);
        }
    }

    @Override
    public Boolean updateUser(User user) throws ServiceException {
        try {
            return userRepository.updateUser(user) > 0;
        } catch (Exception e) {
            throw new ServiceException("Failed to update user" + e);
        }
    }

    @Override
    public Boolean deleteUser(User user) throws ServiceException {
        try {
            return userRepository.deleteUser(user) > 0;
        } catch (Exception e) {
            throw new ServiceException("Failed to delete user" + e);
        }
    }

    @Override
    public User AuthentificateUser(String CIN, String password) throws ServiceException {
        try {
            User user = userRepository.getUserByCIN(CIN);
            String pass = HashUtil.sha256(password);
            if (user == null || !pass.equals(user.getPassword()) )
                throw new ServiceException("CIN or password is incorrect");
            else{
                System.out.println("Service Authentication successful");
                return user;
            }
        } catch (Exception e) {
            throw new ServiceException("Failed to authenticate user" + e);
        }
    }

    @Override
    public Integer getStudentsCount() {
        try {
            return userRepository.getStudentsCount();
        }catch(Exception e){
            throw  new ServiceException("Failed to Count Students " + e);
        }
    }

    @Override
    public Integer getTeachersCount() {
        try {
            return userRepository.getTeachersCount();
        }catch(Exception e){
            throw  new ServiceException("Failed to Count Teachers " + e);
        }
    }

    @Override
    public Boolean deleteUserByCin(String Cin) throws ServiceException {
        try {
            return userRepository.deleteUserByCin(Cin) > 0;
        } catch (Exception e) {
            throw new ServiceException("Failed to delete user " + e);
        }
    }

}
