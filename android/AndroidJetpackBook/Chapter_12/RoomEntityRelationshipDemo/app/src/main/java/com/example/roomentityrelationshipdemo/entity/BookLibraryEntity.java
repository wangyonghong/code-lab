package com.example.roomentityrelationshipdemo.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "book_library")
public class BookLibraryEntity {

    @PrimaryKey(autoGenerate = true)
    public int libraryId;
    public String name;

    public BookLibraryEntity(String name) {
        this.name = name;
    }
}
