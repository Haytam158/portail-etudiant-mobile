package com.portail.etudiant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import com.portail.etudiant.data.sqlite.dao.interfaces.UserDao;
import com.portail.etudiant.data.sqlite.database.AppDatabase;
import com.portail.etudiant.model.User;
import com.portail.etudiant.model.enums.ERole;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UserDaoTest {

    private AppDatabase db;  // Room database instance
    private UserDao userDao; // Room UserDao instance

    // Set up the in-memory database for testing
    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();


        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries()
                .build();

        userDao = db.userDao();
    }

    // Clean up the database after each test
    @After
    public void tearDown() {
        db.close();  // Close the database after tests
    }

//    @Test
//    public void testAddAndGetUser() {
//        // Create a new user instance
//        User newUser = new User(1L,"BB123456", "test@example.com", "securepass", "tester", ERole.Student);
//
//        // Insert the user into the database
//        long userId = userDao.addUser(newUser);
//        assertTrue("User should be added", userId > 0);  // Ensure the user ID is valid (i.e., the user was inserted)
//
//        // Retrieve the user by ID
//        User retrievedUser = userDao.getUserById(userId);
//        assertNotNull("User should be retrieved", retrievedUser);
//
//        // Verify that the retrieved user matches the inserted user
//        assertEquals("Username should match", "tester", retrievedUser.getUsername());
//        assertEquals("Role should match", ERole.Student, retrievedUser.getRole());
//    }

    @Test
    public void testGetAllUsers() {

        User user1 = new User(2L,"BB123456", "email1@example.com", "password1", "user1", ERole.Student);
        User user2 = new User(3L,"CC123456", "email2@example.com", "password2", "user2", ERole.Teacher);

        userDao.addUser(user1);
        userDao.addUser(user2);

        List<User> allUsers = userDao.getAllUsers();

        assertEquals("There should be 3 users", 3, allUsers.size());
    }
}
