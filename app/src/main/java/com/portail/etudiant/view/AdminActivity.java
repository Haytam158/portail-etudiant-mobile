package com.portail.etudiant.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigationrail.NavigationRailView;
import com.portail.etudiant.R;
import com.portail.etudiant.view.Fragments.CoursesFragment;
import com.portail.etudiant.view.Fragments.DashboardFragment;
import com.portail.etudiant.view.Fragments.UsersFragment;
import com.portail.etudiant.viewmodel.CourseViewModel;
import com.portail.etudiant.viewmodel.UserViewModel;

public class AdminActivity extends AppCompatActivity {






    private boolean isExpended = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.admin_layout);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new DashboardFragment())
                    .commit();
        }





        NavigationRailView navigationRailView = findViewById(R.id.navigationRailView);

        //TextView selectionTv = findViewById(R.id.selectionTxtView);

        updateNavigationRailState(navigationRailView, isExpended);

        int userId = getIntent().getIntExtra("userId", -1);
        if (userId != -1) {
            //TODO: continue
        }


        navigationRailView.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            int id = item.getItemId();

            if (id == R.id.action_dashboard) {
                fragment = new DashboardFragment();
            } else if (id == R.id.action_users) {
                fragment = new UsersFragment();
            } else if (id == R.id.action_courses) {
                fragment = new CoursesFragment();
            } else if (id == R.id.action_logout) {
                Intent intent = new Intent(AdminActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                return true;
            }

            if (fragment != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
            }

            return true;
        });

        navigationRailView.getHeaderView().findViewById(R.id.menuButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isExpended = !isExpended;
                updateNavigationRailState(navigationRailView, isExpended);
            }
        });

    }

    private void updateNavigationRailState(NavigationRailView navigationRailView, Boolean isExpended){
        if (isExpended){
            navigationRailView.setLabelVisibilityMode(NavigationRailView.LABEL_VISIBILITY_LABELED);
            navigationRailView.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
        }else {
            navigationRailView.setLabelVisibilityMode(NavigationRailView.LABEL_VISIBILITY_UNLABELED);
            navigationRailView.getLayoutParams().width = getResources().getDimensionPixelSize(R.dimen.navigation_rail_collapsed_width);
        }

        navigationRailView.requestLayout();
    }


}
