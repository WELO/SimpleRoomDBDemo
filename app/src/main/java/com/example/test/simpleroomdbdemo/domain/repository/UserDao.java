package com.example.test.simpleroomdbdemo.domain.repository;

import com.example.test.simpleroomdbdemo.domain.repository.entity.UserInfo;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

public interface UserDao {

    @Query("SELECT * FROM UserInfo")
    Flowable<List<UserInfo>> getUserList();

    @Query("SELECT * FROM userinfo WHERE uid = :uid")
    Single<UserInfo> getUserInfoByUid(long uid);

    @Delete
    void deleteUser(UserInfo info);

    @Insert(onConflict = REPLACE)
    void addUserInfo(UserInfo info);

    @Update(onConflict = REPLACE)
    void updateUserInfo(UserInfo info);

    @Query("SELECT COUNT(*) FROM userinfo")
    Single<Integer> count();

    @Query("DELETE FROM userinfo")
    void delete();
}
