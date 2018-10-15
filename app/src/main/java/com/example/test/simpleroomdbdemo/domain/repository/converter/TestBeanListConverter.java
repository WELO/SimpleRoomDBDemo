package com.example.test.simpleroomdbdemo.domain.repository.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.example.test.simpleroomdbdemo.domain.model.Account;
import com.example.test.simpleroomdbdemo.domain.model.TestBean;

import android.arch.persistence.room.TypeConverter;

import java.lang.reflect.Type;
import java.util.List;

public class TestBeanListConverter {

    @TypeConverter
    public static List<TestBean> fromString(String value) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<TestBean>>() {
        }.getType();
        return gson.fromJson(value, type);
    }


    @TypeConverter
    public static String fromTestBeanList(List<TestBean> testBeanList) {
        Gson gson = new Gson();
        return gson.toJson(testBeanList);
    }

}
