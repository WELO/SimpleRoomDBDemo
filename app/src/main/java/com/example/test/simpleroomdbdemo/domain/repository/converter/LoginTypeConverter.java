package com.example.test.simpleroomdbdemo.domain.repository.converter;

import android.arch.persistence.room.TypeConverter;

public class LoginTypeConverter {
    public enum LoginType {
        gemtek, facebook, twitter, google
    }

    @TypeConverter
    public static LoginType StringToLoginType(int val) {
        return LoginType.values()[val];
    }

    @TypeConverter
    public static int LoginTypeToString(LoginType loginType) {
        return loginType.ordinal();
    }
}
