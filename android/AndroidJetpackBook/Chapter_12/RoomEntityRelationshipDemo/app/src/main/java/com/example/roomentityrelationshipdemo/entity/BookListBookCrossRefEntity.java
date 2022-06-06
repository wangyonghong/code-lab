package com.example.roomentityrelationshipdemo.entity;

import androidx.room.Entity;

@Entity(primaryKeys = {"bookListId", "bookId"})
public class BookListBookCrossRefEntity {

    public int bookListId;
    public int bookId;

}
