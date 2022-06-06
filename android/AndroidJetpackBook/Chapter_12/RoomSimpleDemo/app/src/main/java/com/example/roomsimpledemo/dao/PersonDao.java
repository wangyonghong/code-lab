package com.example.roomsimpledemo.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.roomsimpledemo.entity.PersonEntity;

import java.util.List;

@Dao
public interface PersonDao {

    @Query("SELECT * FROM person_info")
    List<PersonEntity> getAll();

    @Query("SELECT * FROM person_info WHERE id IN (:userIds)")
    List<PersonEntity> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM person_info WHERE age BETWEEN :minAge AND :maxAge")
    List<PersonEntity> findByAge(String minAge, int maxAge);

    @Insert
    void insertAll(PersonEntity... personEntities);

    @Delete
    void delete(PersonEntity personEntities);

}
