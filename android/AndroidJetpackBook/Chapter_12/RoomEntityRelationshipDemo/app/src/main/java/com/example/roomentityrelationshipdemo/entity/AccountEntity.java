package com.example.roomentityrelationshipdemo.entity;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "account_info")
public class AccountEntity {

    @PrimaryKey(autoGenerate = true)
    public int accountId;
    public String name;
    public int age;
    public int gender;

    @Embedded
    public MobileEntity mobileEntity;

    public AccountEntity(String name, int age, int gender, MobileEntity mobileEntity) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.mobileEntity = mobileEntity;
    }
}
