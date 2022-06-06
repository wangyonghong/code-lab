package com.example.roomentityrelationshipdemo.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class BookLibraryWithBookListsAndBooksEntity {

    @Embedded
    public BookLibraryEntity bookLibraryEntity;

    @Relation(entity = BookListEntity.class, parentColumn = "libraryId", entityColumn = "bookLibraryId")
    public List<BookListWithBooksEntity> bookListWithBooksEntities;
}
