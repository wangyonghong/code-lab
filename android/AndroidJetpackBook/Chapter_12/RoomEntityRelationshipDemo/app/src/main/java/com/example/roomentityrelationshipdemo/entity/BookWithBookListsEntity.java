package com.example.roomentityrelationshipdemo.entity;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class BookWithBookListsEntity {

    @Embedded
    public BookEntity bookEntity;

    @Relation(parentColumn = "bookId", entityColumn = "bookListId", associateBy = @Junction(BookListBookCrossRefEntity.class))
    public List<BookListEntity> bookListEntities;

}
