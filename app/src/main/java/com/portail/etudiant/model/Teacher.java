package com.portail.etudiant.model;

import com.portail.etudiant.model.enums.ERole;


public class Teacher extends User{




    public Teacher(Long userID, String cin, String email, String password, String username) {
        super(userID, cin, email, password, username);
        super.setRole(ERole.Teacher);
    }

    public Teacher() {
        super.setRole(ERole.Teacher);
    }


    public Teacher(User user) {
        super(user.getUserID(), user.getCin(), user.getEmail(), user.getPassword(), user.getUsername());
        super.setRole(ERole.Teacher);
    }

}
