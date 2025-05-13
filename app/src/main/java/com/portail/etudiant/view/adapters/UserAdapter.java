package com.portail.etudiant.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.portail.etudiant.R;
import com.portail.etudiant.model.Converters;
import com.portail.etudiant.model.User;
import com.portail.etudiant.model.enums.ERole;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{
    public interface OnUserActionListener {
        void onEdit(User user);
        void onDelete(User user);
    }

    private List<User> userList;
    private final OnUserActionListener listener;

    public UserAdapter(List<User> userList, OnUserActionListener listener) {
        this.userList = userList;
        this.listener = listener;
    }

    public void setUserList(List<User> users) {
        this.userList = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_items, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        User user = userList.get(position);
        holder.userName.setText(user.getUsername()); // Replace with your actual method
        String strRole = convertRoleToFrench(user.getRole());
        holder.role.setText(strRole);
        holder.cin.setText(user.getCin());
        holder.email.setText(user.getEmail());

        holder.editBtn.setOnClickListener(v -> listener.onEdit(user));
        holder.deleteBtn.setOnClickListener(v -> listener.onDelete(user));
    }

    @Override
    public int getItemCount() {
        return userList != null ? userList.size() : 0;
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView userName, role, cin, email;
        Button editBtn, deleteBtn;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            role = itemView.findViewById(R.id.role);
            cin = itemView.findViewById(R.id.cin);
            email = itemView.findViewById(R.id.email);
            editBtn = itemView.findViewById(R.id.editBtn);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
        }
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

    private String convertRoleToFrench(ERole role) {
        switch (role) {
            case Student:
                return "Étudiant";
            case Teacher:
                return "Enseignant";
            case Admin:
                return "Administrateur";
            default:
                return "Étudiant"; // Default role if not matched (you can handle differently if needed)
        }
    }




}
