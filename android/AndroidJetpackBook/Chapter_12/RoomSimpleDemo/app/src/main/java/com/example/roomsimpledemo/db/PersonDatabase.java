package com.example.roomsimpledemo.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.roomsimpledemo.dao.PersonDao;
import com.example.roomsimpledemo.entity.PersonEntity;

@Database(entities = {PersonEntity.class}, version = 1)
public abstract class PersonDatabase extends RoomDatabase {
    public abstract PersonDao personDao();
}
