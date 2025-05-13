package com.portail.etudiant.model;

import com.portail.etudiant.model.enums.ERole;


public class Student extends User{


    public Student(Long userID, String cin, String email, String password, String username) {

        super(userID, cin, email, password, username);
        super.setRole(ERole.Student);

    }

    public Student() {
        super.setRole(ERole.Student);
    }

    public Student(User user) {
        super(user.getUserID(), user.getCin(), user.getEmail(), user.getPassword(), user.getUsername());
        super.setRole(ERole.Student);

    }


}
