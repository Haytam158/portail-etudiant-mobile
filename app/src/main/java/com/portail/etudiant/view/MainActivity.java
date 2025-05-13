package com.portail.etudiant.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.portail.etudiant.R;
import com.portail.etudiant.data.sqlite.dao.interfaces.UserDao;
import com.portail.etudiant.data.sqlite.database.AppDatabase;
import com.portail.etudiant.model.User;
import com.portail.etudiant.model.enums.ERole;
import com.portail.etudiant.services.utils.HashUtil;
import com.portail.etudiant.viewmodel.UserViewModel;


public class MainActivity extends AppCompatActivity {
    private EditText userCINEditText;
    private EditText userPasswordEditText;
    private Button loginButton;
    private UserViewModel userViewModel;

    private TextView loginError;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI elements
        userCINEditText = findViewById(R.id.userCIN);
        userPasswordEditText = findViewById(R.id.userPassword);
        loginButton = findViewById(R.id.loginButton);

        loginError = findViewById(R.id.loginError);

        // ViewModel
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // Observe authentication result
        userViewModel.getAuthentificateUserResult().observe(this, user -> {
            if (user != null) {
                if(user.getRole() == ERole.Admin){
                    Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                    intent.putExtra("userId", user.getUserID());
                    startActivity(intent);
                }
            }
        });

        // Observe error message
        userViewModel.getAuthentificationErrorMessage().observe(this, errorMsg -> {
            if (errorMsg != null) {
                loginError.setText(errorMsg);
                loginError.setVisibility(View.VISIBLE);
            }
        });

        // Handle login click
        loginButton.setOnClickListener(v -> {
            String cin = userCINEditText.getText().toString().trim();
            String password = userPasswordEditText.getText().toString();

            if (cin.isEmpty() || password.isEmpty()) {
                loginError.setText("Veuillez remplir tous les champs");
                loginError.setVisibility(View.VISIBLE);
            } else {
                userViewModel.authentificateUser(cin, password);
            }
        });
    }

}