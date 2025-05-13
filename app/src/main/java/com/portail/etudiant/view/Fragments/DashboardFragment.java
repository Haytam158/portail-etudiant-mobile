package com.portail.etudiant.view.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.portail.etudiant.R;
import com.portail.etudiant.viewmodel.CourseViewModel;
import com.portail.etudiant.viewmodel.UserViewModel;

public class DashboardFragment extends Fragment {

    private UserViewModel userViewModel;
    private CourseViewModel courseViewModel;

    private TextView studentsCountTextView;
    private TextView teachersCountTextView;
    private TextView coursesCountTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);


        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        // Bind the TextViews
        studentsCountTextView = rootView.findViewById(R.id.students_count);
        teachersCountTextView = rootView.findViewById(R.id.teachers_count);
        coursesCountTextView = rootView.findViewById(R.id.courses_count);

        // Observe LiveData for the counts and update the UI when the data changes
        userViewModel.getStudentsCountLiveData().observe(getViewLifecycleOwner(), count -> {
            if (count != null) {
                studentsCountTextView.setText(String.valueOf(count));
            }
        });

        userViewModel.getTeachersCountLiveData().observe(getViewLifecycleOwner(), count -> {
            if (count != null) {
                teachersCountTextView.setText(String.valueOf(count));
            }
        });

        courseViewModel.getCoursesCountLiveData().observe(getViewLifecycleOwner(), count -> {
            if (count != null) {
                coursesCountTextView.setText(String.valueOf(count));
            }
        });

        // Trigger data loading for counts
        loadCounts();

        return rootView;
    }

    private void loadCounts() {
        userViewModel.loadStudentsCount(); // Load students count
        userViewModel.loadTeachersCount(); // Load teachers count
        courseViewModel.loadCoursesCount(); // Load courses count
    }

}
