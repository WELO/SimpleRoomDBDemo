package com.example.test.simpleroomdbdemo.domain.repository;

import com.example.test.simpleroomdbdemo.domain.repository.entity.UserInfo;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@android.arch.persistence.room.Database(entities = {UserInfo.class}, version = 1, exportSchema = false)

public abstract class DbRepository extends RoomDatabase{
    
    public abstract UserDao userDao();

    private static DbRepository instance;

    public static void init(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), DbRepository.class, "database")
                    .fallbackToDestructiveMigration()//當DB架構被更新時，刪除原有的資料
                    .build();
        }
    }

    public DbRepository() {
    }

    public UserDao getUserDao() {
        return getInstance().userDao();
    }

    public static DbRepository getInstance() {
        if (instance == null) {
            throw new RuntimeException("Database not init!!");
        }
        return instance;
    }

    public Flowable<List<UserInfo>> getUserList() {
        return getUserDao().getUserList();
    }

    public Single<UserInfo> getUserInfoByUid(long uid) {
        return getUserDao().getUserInfoByUid(uid);
    }

    public Completable addUserInfo(@NonNull UserInfo info) {
        return Completable.fromAction(() -> getUserDao().addUserInfo(info));
    }

    public Completable deleteUser(@NonNull UserInfo info) {
        return Completable.fromAction(() -> getUserDao().deleteUser(info));
    }

    public Completable updateUserInfo(@NonNull UserInfo info) {
        return Completable.fromAction(() -> getUserDao().updateUserInfo(info));
    }

    public Single<Integer> count() {
        return getUserDao().count();
    }

    public Completable delete() {
        return Completable.fromAction(() -> getUserDao().delete());
    }

}