package com.portail.etudiant.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.portail.etudiant.data.sqlite.database.AppDatabase;
import com.portail.etudiant.model.User;
import com.portail.etudiant.repository.UserRepository;
import com.portail.etudiant.services.impl.UserService;
import com.portail.etudiant.services.exceptions.ServiceException;

import java.util.Collections;
import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private final UserService userService;

    private final MutableLiveData<List<User>> usersLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> addUserResult = new MutableLiveData<>();

    private final MutableLiveData<User> AuthentificateUserResult = new MutableLiveData<>();

    private final MutableLiveData<String> AuthentificationErrorMessage = new MutableLiveData<>();

    private final MutableLiveData<Integer> studentsCountLiveData = new MutableLiveData<>();

    private final MutableLiveData<Integer> teachersCountLiveData = new MutableLiveData<>();

    private final MutableLiveData<Boolean> deleteUserByCinResult = new MutableLiveData<>();

    private final MutableLiveData<Boolean> updateUserResult = new MutableLiveData<>();


    public UserViewModel(@NonNull Application application) {
        super(application);
        AppDatabase db = Room.databaseBuilder(application, AppDatabase.class, "student-db").build();
        UserRepository repo = new UserRepository(db.userDao());
        userService = new UserService(repo);
    }



    public LiveData<List<User>> getUsersLiveData() {
        return usersLiveData;
    }

    public LiveData<Boolean> getAddUserResult() {
        return addUserResult;
    }

    public LiveData<User> getAuthentificateUserResult(){
        return AuthentificateUserResult;
    }

    public LiveData<String> getAuthentificationErrorMessage(){
        return AuthentificationErrorMessage;
    }

    public LiveData<Integer> getStudentsCountLiveData() {
        return studentsCountLiveData;
    }

    public LiveData<Integer> getTeachersCountLiveData() {
        return teachersCountLiveData;
    }

    public LiveData<Boolean> getDeleteUserByCinResult(){
        return deleteUserByCinResult;
    }

    public LiveData<Boolean> getUpdateUserResult(){
        return updateUserResult;
    }

    public void loadAllUsers() {
        new Thread(() -> {
            try {
                List<User> users = userService.getAllUsers();
                usersLiveData.postValue(users);
            } catch (ServiceException e) {
                e.printStackTrace();
                usersLiveData.postValue(Collections.emptyList());
            }
        }).start();
    }

    public void addUser(User user) {
        new Thread(() -> {
            try {
                boolean success = userService.addUser(user);
                addUserResult.postValue(success);
                loadAllUsers();
            } catch (ServiceException e) {
                e.printStackTrace();
                addUserResult.postValue(false);
            }
        }).start();
    }

    public void authentificateUser(String CIN, String password){
        new Thread(() ->{
            try{
                System.out.println("login attempt");
                User tempUser = userService.AuthentificateUser(CIN,password);
                AuthentificateUserResult.postValue(tempUser);
            }catch(ServiceException e){
                e.printStackTrace();
                AuthentificateUserResult.postValue(null);
                AuthentificationErrorMessage.postValue("le CIN ou le mot de passe est incorrecte");
            }
        }).start();
    }

    public void loadStudentsCount() {
        new Thread(() -> {
            try {
                Integer count = userService.getStudentsCount();
                studentsCountLiveData.postValue(count);
            } catch (ServiceException e) {
                e.printStackTrace();
                studentsCountLiveData.postValue(0);
            }
        }).start();
    }

    public void loadTeachersCount() {
        new Thread(() -> {
            try {
                Integer count = userService.getTeachersCount();
                teachersCountLiveData.postValue(count);
            } catch (ServiceException e) {
                e.printStackTrace();
                teachersCountLiveData.postValue(0);
            }
        }).start();
    }

    public void deleteUserByCin(String cin) {
        new Thread(() -> {
            try {
                if (userService.deleteUserByCin(cin)) {
                    deleteUserByCinResult.postValue(true);
                    loadAllUsers();
                } else {
                    deleteUserByCinResult.postValue(false);
                }
            } catch (ServiceException e) {
                deleteUserByCinResult.postValue(false);
                e.printStackTrace();
            }
        }).start();
    }

    public void updateUser(User user){
        new Thread(() -> {
            try{
                if(userService.updateUser(user)){
                    updateUserResult.postValue(true);
                    loadAllUsers();
                }else {
                    updateUserResult.postValue(false);
                }
            }catch (ServiceException e){
                updateUserResult.postValue(false);
                e.printStackTrace();
            }
        }).start();
    }



}
