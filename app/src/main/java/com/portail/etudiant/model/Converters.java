package com.portail.etudiant.model;

import androidx.room.TypeConverter;

import com.portail.etudiant.model.enums.ERole;

import java.util.Date;

public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static String roleToString(ERole role) {
        return role == null ? null : role.name();
    }

    @TypeConverter
    public static ERole stringToRole(String role) {
        return role == null ? null : ERole.valueOf(role);
    }

}