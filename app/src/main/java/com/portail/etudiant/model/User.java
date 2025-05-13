package com.portail.etudiant.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

import com.portail.etudiant.model.enums.ERole;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id") @NonNull
    private Long userID;

    private String cin;
    private String email;
    private String password;
    private String username;

    private ERole role;


    @Ignore
    public User(Long userID, String cin, String email, String password, String username) {
        this.userID = userID;
        this.cin = cin;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    @Ignore
    public User(Long userID, String cin, String email, String password, String username, ERole role) {
        this.userID = userID;
        this.cin = cin;
        this.email = email;
        this.password = password;
        this.username = username;
        this.role = role;
    }



    public User() {
    }


    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
