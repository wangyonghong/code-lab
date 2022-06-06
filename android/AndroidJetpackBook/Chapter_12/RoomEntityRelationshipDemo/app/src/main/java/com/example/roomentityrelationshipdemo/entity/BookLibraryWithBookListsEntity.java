package com.example.roomentityrelationshipdemo.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class BookLibraryWithBookListsEntity {

    @Embedded
    public BookLibraryEntity bookLibraryEntity;

    @Relation(parentColumn = "libraryId", entityColumn = "bookLibraryId")
    public List<BookListEntity> bookListEntities;
}
