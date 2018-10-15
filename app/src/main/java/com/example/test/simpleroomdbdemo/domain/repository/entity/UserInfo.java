package com.example.test.simpleroomdbdemo.domain.repository.entity;

import com.example.test.simpleroomdbdemo.domain.model.Account;
import com.example.test.simpleroomdbdemo.domain.model.TestBean;
import com.example.test.simpleroomdbdemo.domain.repository.converter.AccountConverter;
import com.example.test.simpleroomdbdemo.domain.repository.converter.LoginTypeConverter;
import com.example.test.simpleroomdbdemo.domain.repository.converter.TestBeanListConverter;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.ArrayList;
import java.util.List;

@Entity
public class UserInfo {

    @PrimaryKey
    public int uid=-1;

    @TypeConverters({AccountConverter.class})
    public Account account=Account.newBuilder().setName("test_name").setPassword("test_pwd").build();

    @TypeConverters({LoginTypeConverter.class})
    public LoginTypeConverter.LoginType loginType = LoginTypeConverter.LoginType.gemtek;

    @TypeConverters({TestBeanListConverter.class})
    public List<TestBean> gatewayList = new ArrayList<>();
}
