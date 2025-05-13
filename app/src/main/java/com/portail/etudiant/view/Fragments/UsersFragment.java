package com.portail.etudiant.view.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.portail.etudiant.R;
import com.portail.etudiant.model.Converters;
import com.portail.etudiant.model.User;
import com.portail.etudiant.model.enums.ERole;
import com.portail.etudiant.view.adapters.UserAdapter;
import com.portail.etudiant.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.Arrays;

public class UsersFragment extends Fragment {
    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private UserViewModel userViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);

        recyclerView = view.findViewById(R.id.usersRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        adapter = new UserAdapter(new ArrayList<>(), new UserAdapter.OnUserActionListener() {
            @Override
            public void onEdit(User user) {

                LayoutInflater inflater = LayoutInflater.from(getContext());
                View dialogView = inflater.inflate(R.layout.edit_user_dialog, null);


                EditText usernameEdit = dialogView.findViewById(R.id.editTextUsername);
                EditText cinEdit = dialogView.findViewById(R.id.editTextCin);
                EditText emailEdit = dialogView.findViewById(R.id.editTextEmail);
                Spinner roleSpinner = dialogView.findViewById(R.id.spinnerRole);


                usernameEdit.setText(user.getUsername());
                cinEdit.setText(user.getCin());
                emailEdit.setText(user.getEmail());


                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(),
                        android.R.layout.simple_spinner_item,
                        Arrays.asList("Étudiant", "Enseignant", "Administrateur"));
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                roleSpinner.setAdapter(spinnerAdapter);


                String roleStr = Converters.roleToString(user.getRole());
                int position = spinnerAdapter.getPosition(roleStr);
                roleSpinner.setSelection(position);

                // Show the dialog
                new AlertDialog.Builder(getContext())
                        .setTitle("Modifier l'utilisateur")
                        .setView(dialogView)
                        .setPositiveButton("Modifier", (dialog, which) -> {
                            // Get the updated values from the dialog
                            String username = usernameEdit.getText().toString().trim();
                            String cin = cinEdit.getText().toString().trim();
                            String email = emailEdit.getText().toString().trim();
                            String selectedRoleStr = roleSpinner.getSelectedItem().toString(); // Get selected role

                            // Make sure all fields are filled out
                            if (!username.isEmpty() && !cin.isEmpty() && !email.isEmpty()) {
                                // Update the user object with the new data
                                user.setUsername(username);
                                user.setCin(cin);
                                user.setEmail(email);

                                ERole role = Converters.stringToRole(convertRoleToEnglish(selectedRoleStr));
                                user.setRole(role); // Convert the selected role back to enum

                                // Call ViewModel method to update the user
                                userViewModel.updateUser(user);
                                userViewModel.loadAllUsers();  // Reload users
                            } else {
                                Toast.makeText(getContext(), "Tous les champs sont requis.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Annuler", null)
                        .show();
                }

            @Override
            public void onDelete(User user) {
                userViewModel.deleteUserByCin(user.getCin());
            }
        });

        recyclerView.setAdapter(adapter);

        FloatingActionButton fabAddUser = view.findViewById(R.id.fab_add_user);
        fabAddUser.setOnClickListener(v -> showAddUserDialog());

        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        userViewModel.getUsersLiveData().observe(getViewLifecycleOwner(), users -> {
            adapter.setUserList(users);
        });

        userViewModel.loadAllUsers(); // initial

        return view;
    }

    private void showAddUserDialog() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialogView = inflater.inflate(R.layout.add_user_dialog, null);

        EditText usernameEdit = dialogView.findViewById(R.id.editTextUsername);
        EditText cinEdit = dialogView.findViewById(R.id.editTextCin);
        EditText emailEdit = dialogView.findViewById(R.id.editTextEmail);
        EditText passwordEdit = dialogView.findViewById(R.id.editTextPassword);
        Spinner roleSpinner = dialogView.findViewById(R.id.spinnerRole);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                Arrays.asList("Étudiant", "Enseignant", "Administrateur")
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSpinner.setAdapter(spinnerAdapter);

        new AlertDialog.Builder(requireContext())
                .setTitle("Add New User")
                .setView(dialogView)
                .setPositiveButton("Ajouter", (dialog, which) -> {
                    String username = usernameEdit.getText().toString().trim();
                    String cin = cinEdit.getText().toString().trim();
                    String email = emailEdit.getText().toString().trim();
                    String password = passwordEdit.getText().toString().trim();
                    String roleStr = roleSpinner.getSelectedItem().toString();

                    if (!username.isEmpty() && !cin.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                        User newUser = new User();
                        newUser.setCin(cin);
                        newUser.setUsername(username);
                        newUser.setEmail(email);
                        newUser.setPassword(password);
                        ERole role;
//                        switch (roleStr) {
//                            case "Étudiant":
//                                role = ERole.Student;
//                                break;
//                            case "Enseignant":
//                                role = ERole.Teacher;
//                                break;
//                            case "Administrateur":
//                                role = ERole.Admin;
//                                break;
//                            default:
//                                role = ERole.Student;
//                        }
                        role =Converters.stringToRole(convertRoleToEnglish(roleStr));

                        newUser.setRole(role);
                        userViewModel.addUser(newUser);
                        userViewModel.loadAllUsers();
                    } else {
                        Toast.makeText(getContext(), "Tous les champs sont obligatoires.", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Annuler", null)
                .show();
    }

    private String convertRoleToEnglish(String roleStrFrench) {
        switch (roleStrFrench) {
            case "Étudiant":
                return "Student";
            case "Enseignant":
                return "Teacher";
            case "Administrateur":
                return "Admin";
            default:
                return "Student"; // Default role if not matched (you can handle differently if needed)
        }
    }

}
