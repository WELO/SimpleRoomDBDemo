package com.example.test.simpleroomdbdemo.domain.repository.converter;

import com.google.gson.Gson;

import com.example.test.simpleroomdbdemo.domain.model.Account;

import android.arch.persistence.room.TypeConverter;

public class AccountConverter {

    @TypeConverter
    public static Account toFakeAccount(String value) {
        return new Gson().fromJson(value, Account.class);
    }

    @TypeConverter
    public static String FakeAccounttoString(Account fakeAccount) {
        return new Gson().toJsonTree(fakeAccount).getAsJsonObject().toString();
    }

}
