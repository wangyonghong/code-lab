package com.example.roomentityrelationshipdemo.entity;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class BookListWithBooksEntity {

    @Embedded
    public BookListEntity bookListEntity;

    @Relation(parentColumn = "bookListId", entityColumn = "bookId", associateBy = @Junction(BookListBookCrossRefEntity.class))
    public List<BookEntity> bookEntities;

}
