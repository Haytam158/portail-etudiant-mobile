package com.portail.etudiant;

import android.content.Context;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.portail.etudiant.data.sqlite.database.AppDatabase;
// You will need to create the corresponding DAO
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class AppDatabaseTest {

    private AppDatabase db;
//    private UserDao userDao;  // Example DAO for User entity

    @Before
    public void createDb() {
        // Create an in-memory Room database
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries() // Allow queries on the main thread for testing
                .build();

        // Initialize DAO
        //userDao = db.userDao();
    }

    @Test
    public void testDatabaseCreation() {
        // Test if the database is correctly created
        assertNotNull(db);
    }
/*
    @Test
    public void testInsertUser() {
        // Test if inserting a User into the database works
        User user = new User(1, "John Doe", "john.doe@example.com");
        userDao.insert(user);

        // Verify that the user was inserted (you can query and check)
        User insertedUser = userDao.getUserById(1);  // Assuming getUserById() exists in UserDao
        assertNotNull(insertedUser);
    }
*/
    @After
    public void closeDb() {
        db.close();  // Close the database after the test
    }
}
