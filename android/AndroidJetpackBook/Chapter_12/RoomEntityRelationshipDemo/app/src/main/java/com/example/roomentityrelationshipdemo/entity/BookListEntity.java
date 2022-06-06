package com.example.roomentityrelationshipdemo.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "book_list")
public class BookListEntity {

    @PrimaryKey(autoGenerate = true)
    public int bookListId;
    public long bookLibraryId;
    public String name;

    public BookListEntity(String name, long bookLibraryId, int bookListId) {
        this.name = name;
        this.bookLibraryId = bookLibraryId;
        this.bookListId = bookListId;
    }
}
