package com.portail.etudiant.model;

import androidx.room.Ignore;

import com.portail.etudiant.model.enums.ERole;


public class Admin extends User {
    @Ignore
    public Admin(Long userID, String cin, String email, String password, String username) {
        super(userID, cin, email, password, username);
        super.setRole(ERole.Admin);

    }

    @Ignore
    public Admin() {
        super.setRole(ERole.Admin);
    }

    @Ignore
    public Admin(User user) {
        super(user.getUserID(), user.getCin(), user.getEmail(), user.getPassword(), user.getUsername());
        super.setRole(ERole.Admin);

    }
}
