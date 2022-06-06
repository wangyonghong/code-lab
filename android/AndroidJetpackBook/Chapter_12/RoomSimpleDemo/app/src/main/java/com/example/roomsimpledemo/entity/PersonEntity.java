package com.example.roomsimpledemo.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "person_info")
public class PersonEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public int age;
    public int gender;

    public static final int GENDER_MALE = 0;
    public static final int GENDER_FEMALE = 1;

    public PersonEntity(String name, int age, int gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}
