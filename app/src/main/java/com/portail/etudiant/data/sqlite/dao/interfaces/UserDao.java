package com.portail.etudiant.data.sqlite.dao.interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.portail.etudiant.model.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    Long addUser(User user);

    @Query("SELECT * FROM User WHERE role!='Admin'")
    List<User> getAllUsers(); // Retrieve all users

    @Update
    Integer updateUser(User user); // Update an existing user

    @Delete
    Integer deleteUser(User user); // Delete a user

    @Query("SELECT * FROM User WHERE user_id = :id")
    User getUserById(Long id);

    @Query("SELECT * FROM User WHERE cin = :CIN")
    User getUserByCIN(String CIN);

    @Query("SELECT count(*) from User where role = 'Student'")
    Integer getStudentsCount();

    @Query("SELECT count(*) from User where role = 'Teacher'")
    Integer getTeachersCount();

    @Query("Delete from User where cin = :cin")
    Integer deleteUserByCin(String cin);

}
