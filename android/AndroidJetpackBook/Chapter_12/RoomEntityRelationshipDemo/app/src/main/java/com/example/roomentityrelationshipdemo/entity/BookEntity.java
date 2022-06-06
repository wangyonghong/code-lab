package com.example.roomentityrelationshipdemo.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "book")
public class BookEntity {

    @PrimaryKey(autoGenerate = true)
    public int bookId;
    public String name;
    public String author;

    public BookEntity(String name, String author) {
        this.name = name;
        this.author = author;
    }
}
