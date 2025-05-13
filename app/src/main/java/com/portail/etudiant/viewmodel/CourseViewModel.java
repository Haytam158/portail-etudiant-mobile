package com.portail.etudiant.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.portail.etudiant.data.sqlite.database.AppDatabase;
import com.portail.etudiant.repository.CourseRepository;
import com.portail.etudiant.services.exceptions.ServiceException;
import com.portail.etudiant.services.impl.CourseService;

public class CourseViewModel extends AndroidViewModel {

    private final CourseService courseService;

    private final MutableLiveData<Integer> coursesCountLiveData = new MutableLiveData<>();
    public CourseViewModel(@NonNull Application application) {
        super(application);
        AppDatabase db = Room.databaseBuilder(application, AppDatabase.class, "student-db").build();
        CourseRepository courseRepository = new CourseRepository(db.courseDao());
        courseService = new CourseService(courseRepository);
    }

    public LiveData<Integer> getCoursesCountLiveData() {
        return coursesCountLiveData;
    }

    public void loadCoursesCount() {
        new Thread(() -> {
            try {
                Integer count = courseService.getCoursesCount();
                coursesCountLiveData.postValue(count);
            } catch (ServiceException e) {
                e.printStackTrace();
                coursesCountLiveData.postValue(0);
            }
        }).start();
    }

}
