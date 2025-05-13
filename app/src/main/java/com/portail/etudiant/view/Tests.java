package com.portail.etudiant.view;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.portail.etudiant.R;
import com.portail.etudiant.data.sqlite.dao.interfaces.UserDao;
import com.portail.etudiant.data.sqlite.database.AppDatabase;
import com.portail.etudiant.model.User;
import com.portail.etudiant.model.enums.ERole;

public class Tests extends AppCompatActivity {

    private AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "test-database").build();
        //student-db

        // Initialize Room database
        db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "test-database"  // This name is what will show in the Database Inspector
        ).build();

        // Start a background thread to perform an operation and trigger schema creation
        new Thread(new Runnable() {
            @Override
            public void run() {
                db.getOpenHelper().getWritableDatabase();  // Just opening the database triggers the creation of tables

                UserDao userDao = db.userDao();

                if (userDao.getAllUsers().isEmpty()) {
                    userDao.addUser(new User(1L, "BB123456", "email1@example.com", "pass1", "user1", ERole.Student));
                    userDao.addUser(new User(2L, "CC123456", "email2@example.com", "pass2", "user2", ERole.Teacher));
                }else{
                    User temp = new User();
                    temp.setUserID(2L);
                    userDao.deleteUser(temp);
                }

                for (User user : userDao.getAllUsers()) {
                    Log.d("DB_TEST", "User: " + user.getUsername() + ", Role: " + user.getRole());
                }


            }
        }).start();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
