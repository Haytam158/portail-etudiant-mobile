package com.portail.etudiant.data.sqlite.database;


import android.database.Cursor;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.portail.etudiant.data.sqlite.dao.interfaces.CourseDao;
import com.portail.etudiant.data.sqlite.dao.interfaces.UserDao;
import com.portail.etudiant.model.Certificate;
import com.portail.etudiant.model.Converters;
import com.portail.etudiant.model.Course;
import com.portail.etudiant.model.CourseStudent;
import com.portail.etudiant.model.Exam;
import com.portail.etudiant.model.ExamResult;
import com.portail.etudiant.model.Lesson;
import com.portail.etudiant.model.Module;
import com.portail.etudiant.model.Option;
import com.portail.etudiant.model.Question;
import com.portail.etudiant.model.TeacherCourse;
import com.portail.etudiant.model.User;

@Database(
        entities = {
                Certificate.class,
                Course.class,
                CourseStudent.class,
                Exam.class,
                ExamResult.class,
                Lesson.class,
                Module.class,
                Option.class,
                Question.class,
                TeacherCourse.class,
                User.class
        },
        version = 1,
        exportSchema = true
)

@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

        public abstract UserDao userDao();

        public abstract CourseDao courseDao();
        public static String dumpDatabaseSchema(SupportSQLiteDatabase db) {
                StringBuilder schema = new StringBuilder();

                // Using query() method instead of rawQuery
                Cursor cursor = db.query("SELECT name, sql FROM sqlite_master WHERE type = 'table'", null);

                while (cursor.moveToNext()) {
                        String tableName = cursor.getString(0);
                        String createTableSql = cursor.getString(1);

                        schema.append("-- Table: ").append(tableName).append("\n");
                        schema.append(createTableSql).append("\n\n");
                }

                cursor.close();
                return schema.toString();
        }

        public SupportSQLiteDatabase getWritableDb() {
                return this.getOpenHelper().getWritableDatabase();
        }


}
